package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record NumeroCarta(String numero) {
    public NumeroCarta {
        if (numero == null) {
            throw new ValidazioneException(
                NumeroCarta.class.getSimpleName(),
                CodiceErrore.NUMERO_CARTA_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
    }
}
