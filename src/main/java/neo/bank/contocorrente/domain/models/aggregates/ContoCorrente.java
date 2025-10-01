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
import neo.bank.contocorrente.domain.models.events.ContoCorrenteAperto;
import neo.bank.contocorrente.domain.models.events.EventPayload;
import neo.bank.contocorrente.domain.models.events.SoglieBonificoImpostate;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.DataApertura;
import neo.bank.contocorrente.domain.models.vo.DataChiusura;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;


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
    private double saldo;
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
        cc.events(new ContoCorrenteAperto(idConto, idCliente, coordinateBancarie, soglieBonifico, dataApertura, saldo));
        return cc;
    }

    public void impostaSoglieBonifico(IdCliente idClienteRichiedente, SoglieBonifico nuoveSoglieBonifico) {
        verificaAccessoCliente(idClienteRichiedente);
        verificaContoChiuso();
        events(new SoglieBonificoImpostate(nuoveSoglieBonifico));
    }

    private void apply(ContoCorrenteAperto event) {
        this.clientiAssociati.add(event.idCliente());
        this.coordinateBancarie = event.coordinateBancarie();
        this.dataApertura = event.dataApertura();
        this.idContoCorrente = event.idContoCorrente();
        this.saldo = event.saldo();
        this.soglieBonifico = event.soglieBonifico();
    }

    private void apply(SoglieBonificoImpostate event) {
        soglieBonifico = event.soglieBonifico();
    }

    @Override
    public void apply(EventPayload event) {
        switch (event) {
            case ContoCorrenteAperto ev -> apply((ContoCorrenteAperto) ev);
            case SoglieBonificoImpostate ev -> apply((SoglieBonificoImpostate) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }

     private void verificaAccessoCliente(IdCliente idCliente) {
        if( !clientiAssociati.stream().anyMatch(c -> c.id().equals(idCliente.id()))){
            throw new BusinessRuleException(String.format("Accesso al conto non autorizzato per il cliente [%s]", idCliente.id()));
        }
    }

    public void verificaContoChiuso() {
        if(dataChiusura != null) {
            throw new BusinessRuleException("Chiusura del conto fallita: risulta gia chiuso");
        }
    }
}


