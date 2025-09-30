package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record Bic(String codice) {


    public Bic(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Bic.class.getSimpleName(), CodiceErrore.CODICE_SWIFT_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
