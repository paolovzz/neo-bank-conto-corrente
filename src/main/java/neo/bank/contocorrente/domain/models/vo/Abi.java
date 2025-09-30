package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record Abi(String codice) {


    public Abi(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Abi.class.getSimpleName(), CodiceErrore.CODICE_ABI_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
