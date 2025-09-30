package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record Iban(String codice) {

    public Iban(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Iban.class.getSimpleName(), CodiceErrore.IBAN_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
