package neo.bank.contocorrente.domain.models.aggregates;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.exceptions.BusinessRuleException;
import neo.bank.contocorrente.domain.models.events.ContoCorrenteAperto;
import neo.bank.contocorrente.domain.models.events.EventPayload;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.DataApertura;
import neo.bank.contocorrente.domain.models.vo.DataChiusura;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;
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
    private Map<IdCliente, Boolean> clientiAssociati = new HashMap<>();

    public static ContoCorrente apri(GeneratoreIdService generatoreIdService, GeneratoreCoordinateBancarieService generatoreCoordinateBancarie, IdCliente idCliente) {
        
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


    private void apply(ContoCorrenteAperto event) {
        this.clientiAssociati.put(event.idCliente(), true);
        this.coordinateBancarie = event.coordinateBancarie();
        this.dataApertura = event.dataApertura();
        this.idContoCorrente = event.idContoCorrente();
        this.saldo = event.saldo();
        this.soglieBonifico = event.soglieBonifico();
    }

    // private void apply(ContoCorrenteChiuso event) {
    //     this.dataChiusura = event.dataChiusura();
    // }
    
    // private void apply(RichiestaCointestazioneValidata event) {
    //     clientiAssociati.compute(event.IdCliente(), (key, val) -> false); 
    // }

    // private void apply(ClienteDissociato event) {
    //     this.clientiAssociati.remove(event.IdCliente());
    // }

    // private void apply(CointestazioneConfermata event) {
    //     clientiAssociati.compute(event.IdCliente(), (key, val) -> true); 
    // }

    // private void apply(CointestazioneRifiutata event) {
    //     clientiAssociati.remove(event.IdCliente()); 
    // }

    // private void apply(SoglieBonificoImpostate event) {
    //     soglieBonifico = event.soglieBonifico();
    // }

    @Override
    public void apply(EventPayload event) {
        switch (event) {
            case ContoCorrenteAperto ev -> apply((ContoCorrenteAperto) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }

     private void verificaAccessoCliente(IdCliente idCliente) {
        if( !clientiAssociati.keySet().stream().anyMatch(c -> c.id().equals(idCliente.id()))){
            throw new BusinessRuleException(String.format("Accesso al conto non autorizzato per il cliente [%s]", idCliente.id()));
        }
    }

    public void verificaContoChiuso() {
        if(dataChiusura != null) {
            throw new BusinessRuleException("Chiusura del conto fallita: risulta gia chiuso");
        }
    }
}


