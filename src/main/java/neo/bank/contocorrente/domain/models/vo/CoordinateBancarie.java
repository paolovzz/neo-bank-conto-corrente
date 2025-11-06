package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CoordinateBancarie {

    private NumeroConto numeroConto;
    private Iban iban;
    private Bic bic;
    private Cab cab;
    private Abi abi;
    public CoordinateBancarie(NumeroConto numeroConto, Iban iban, Bic bic, Cab cab, Abi abi) {
        this.numeroConto = numeroConto;
        this.iban = iban;
        this.bic = bic;
        this.cab = cab;
        this.abi = abi;
    }                                               

}
