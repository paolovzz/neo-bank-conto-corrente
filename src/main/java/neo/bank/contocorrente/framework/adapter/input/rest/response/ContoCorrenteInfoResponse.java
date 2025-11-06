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
        this.iban = cc.getCoordinateBancarie().getIban().getCodice();
        this.saldoDisponibile = cc.getSaldoDisponibile();
        this.saldoContabile = cc.getSaldoContabile();
        this.sogliaBonificiMensile = cc.getSogliaBonificoMensile();
        this.sogliaBonificiGiornaliera = cc.getSogliaBonificoGiornaliera();
        this.numeroConto = cc.getCoordinateBancarie().getNumeroConto().getNumero();
        this.bic = cc.getCoordinateBancarie().getBic().getCodice();
        this.cab = cc.getCoordinateBancarie().getCab().getCodice();
        this.abi = cc.getCoordinateBancarie().getAbi().getCodice();
    }

    

}
