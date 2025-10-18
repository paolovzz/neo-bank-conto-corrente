package neo.bank.contocorrente.framework.adapter.input.rest.response;

import lombok.Getter;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;

@Getter
public class ContoCorrenteInfoResponse {
   
    private String iban;
    private double saldoDisponibile;
    private double saldoContabile;
    private int sogliaBonificiMensile;
    private int sogliaBonificiGiornaliera;
    private String numeroConto;
    private String  bic;
    private String  cab;
   private String abi;

    public ContoCorrenteInfoResponse(ContoCorrente cc) {
        this.iban = cc.getCoordinateBancarie().iban().codice();
        this.saldoDisponibile = cc.getSaldoDisponibile();
        this.saldoContabile = cc.getSaldoContabile();
        this.sogliaBonificiMensile = cc.getSogliaBonificoMensile();
        this.sogliaBonificiGiornaliera = cc.getSogliaBonificoGiornaliera();
        this.numeroConto = cc.getCoordinateBancarie().numeroConto().numero();
        this.bic = cc.getCoordinateBancarie().bic().codice();
        this.cab = cc.getCoordinateBancarie().cab().codice();
        this.abi = cc.getCoordinateBancarie().abi().codice();
    }

    

}
