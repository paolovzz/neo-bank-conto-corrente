package neo.bank.contocorrente.domain.models.aggregates;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.exceptions.BusinessRuleException;
import neo.bank.contocorrente.domain.models.events.BonificoPredisposto;
import neo.bank.contocorrente.domain.models.events.ContoCorrenteAperto;
import neo.bank.contocorrente.domain.models.events.EventPayload;
import neo.bank.contocorrente.domain.models.events.SaldoContabileAggiornato;
import neo.bank.contocorrente.domain.models.events.SaldoDisponibileAggiornato;
import neo.bank.contocorrente.domain.models.events.SoglieBonificoImpostate;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.DataApertura;
import neo.bank.contocorrente.domain.models.vo.DataChiusura;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;
import neo.bank.contocorrente.domain.models.vo.SommaBonificiUscita;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;
import neo.bank.contocorrente.domain.services.TransazioniService;


@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContoCorrente extends AggregateRoot<ContoCorrente> implements Applier  {

    public static final String AGGREGATE_NAME = "CONTO_CORRENTE";
    private IdContoCorrente idContoCorrente;
    private CoordinateBancarie coordinateBancarie;
    private SoglieBonifico soglieBonifico;
    private DataApertura dataApertura;
    private double saldoContabile;
    private double saldoDisponibile;
    private DataChiusura dataChiusura;
    private List<IdCliente> clientiAssociati = new ArrayList<>();

    public static ContoCorrente apri(GeneratoreIdService generatoreIdService, GeneratoreCoordinateBancarieService generatoreCoordinateBancarie, AnagraficaClienteService anagraficaClienteService, IdCliente idCliente) {
        
        if(!anagraficaClienteService.richiediVerificaCliente(idCliente)) {
            throw new BusinessRuleException(String.format("Il cliente [%s] per cui e' stato richiesta la creazione del conto non esiste", idCliente.id()));
        }
        IdContoCorrente idConto =generatoreIdService.genera();
        CoordinateBancarie coordinateBancarie = generatoreCoordinateBancarie.genera();
        SoglieBonifico soglieBonifico = new SoglieBonifico(5000, 1500);
        DataApertura dataApertura = new DataApertura(LocalDateTime.now(ZoneOffset.UTC));
        double saldo = 0;
        ContoCorrente cc = new ContoCorrente();
        cc.idContoCorrente = idConto;
        cc.coordinateBancarie = coordinateBancarie;
        
        cc.events(new ContoCorrenteAperto(idConto, idCliente, coordinateBancarie, soglieBonifico, dataApertura, saldo, saldo));
        return cc;
    }

    public void impostaSoglieBonifico(IdCliente idClienteRichiedente, SoglieBonifico nuoveSoglieBonifico) {
        verificaAccessoCliente(idClienteRichiedente);
        verificaContoChiuso();
        events(new SoglieBonificoImpostate(nuoveSoglieBonifico));
    }

    public void predisponiBonifico(Iban ibanDestinatario, String causale, double importo, IdCliente idClientRichiedente, TransazioniService transazioniService) {
        
        verificaAccessoCliente(idClientRichiedente);
        verificaContoChiuso();
        double importAbs = Math.abs(importo);
        if(Math.abs(saldoDisponibile) < importAbs) {
          throw new BusinessRuleException(String.format("Importo [%s] non disponibile", importAbs));  
        }
        SommaBonificiUscita sommaBonificiUscita = transazioniService.richiediTotaleBonificiUscita(coordinateBancarie.iban());
        if(soglieBonifico.sogliaGiornaliera() < sommaBonificiUscita.getSommaOdierna() + importAbs) {
            throw new BusinessRuleException("Impossibile inviare il bonifico: raggiunto il limite giornaliero");  
        }
        if(soglieBonifico.sogliaMensile() < sommaBonificiUscita.getSommaMensile() + importAbs) {
            throw new BusinessRuleException("Impossibile inviare il bonifico: raggiunto il limite mensile");  
        }
        events(new BonificoPredisposto(coordinateBancarie.iban(), ibanDestinatario, importo * (-1), causale));
    }


    public void aggiornaSaldoContabile(double importo) {
        events(new SaldoContabileAggiornato(importo));
    }

    public void aggiornaSaldoDisponibile(double importo) {
        events(new SaldoDisponibileAggiornato(importo));
    }

    private void verificaAccessoCliente(IdCliente idCliente) {
        if( !clientiAssociati.stream().anyMatch(c -> c.id().equals(idCliente.id()))){
            throw new BusinessRuleException(String.format("Accesso al conto non autorizzato per il cliente [%s]", idCliente.id()));
        }
    }

    private void verificaContoChiuso() {
        if(dataChiusura != null) {
            throw new BusinessRuleException("Chiusura del conto fallita: risulta gia chiuso");
        }
    }

    private void apply(ContoCorrenteAperto event) {
        this.clientiAssociati.add(event.idCliente());
        this.coordinateBancarie = event.coordinateBancarie();
        this.dataApertura = event.dataApertura();
        this.idContoCorrente = event.idContoCorrente();
        this.saldoDisponibile = event.saldoDisponibile();
        this.saldoContabile = event.saldoDisponibile();
        this.soglieBonifico = event.soglieBonifico();
    }

    private void apply(SoglieBonificoImpostate event) {
        soglieBonifico = event.soglieBonifico();
    }

    private void apply(BonificoPredisposto event) {
        saldoDisponibile += event.importo();   
    }

    private void apply(SaldoContabileAggiornato event) {
        saldoContabile += event.importo();   
    }

    private void apply(SaldoDisponibileAggiornato event) {
        saldoDisponibile += event.importo();   
    }

    @Override
    public void apply(EventPayload event) {
        switch (event) {
            case ContoCorrenteAperto ev -> apply((ContoCorrenteAperto) ev);
            case SoglieBonificoImpostate ev -> apply((SoglieBonificoImpostate) ev);
            case BonificoPredisposto ev -> apply((BonificoPredisposto) ev);
            case SaldoContabileAggiornato ev -> apply((SaldoContabileAggiornato) ev);
            case SaldoDisponibileAggiornato ev -> apply((SaldoDisponibileAggiornato) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }
}


