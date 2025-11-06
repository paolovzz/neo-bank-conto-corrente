package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class Iban {

    private String codice;

    public Iban(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Iban.class.getSimpleName(),
                    CodiceErrore.IBAN_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
