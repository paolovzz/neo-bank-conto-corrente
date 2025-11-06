package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class Bic {

    private String codice;

    public Bic(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Bic.class.getSimpleName(), CodiceErrore.CODICE_SWIFT_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
