package neo.bank.contocorrente.domain.models.vo;


public record CoordinateBancarie(NumeroConto numeroConto, Iban iban, Bic bic, Cab cab, Abi abi) {


    public CoordinateBancarie(NumeroConto numeroConto, Iban iban, Bic bic, Cab cab, Abi abi) {
        this.numeroConto = numeroConto;
        this.iban = iban;
        this.bic = bic;
        this.cab = cab;
        this.abi = abi;
    }                                               

}
