package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record IdTransazione(String id) {
    public IdTransazione {
        if (id == null) {
            throw new ValidazioneException(
                IdTransazione.class.getSimpleName(),
                CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
    }
}
