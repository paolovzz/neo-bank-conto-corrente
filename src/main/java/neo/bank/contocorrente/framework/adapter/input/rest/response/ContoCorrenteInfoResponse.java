package neo.bank.contocorrente.framework.adapter.input.rest.response;

import lombok.Getter;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;

@Getter
public class ContoCorrenteInfoResponse {
   
    private String iban;
    private double saldoDisponibile;
    private double saldoContabile;

    public ContoCorrenteInfoResponse(ContoCorrente cc) {
        this.iban = cc.getCoordinateBancarie().iban().codice();
        this.saldoDisponibile = cc.getSaldoDisponibile();
        this.saldoContabile = cc.getSaldoContabile();
    }

    

}
