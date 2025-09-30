package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record SoglieBonifico (int sogliaMensile, int sogliaGiornaliera){


    public SoglieBonifico(int sogliaMensile, int sogliaGiornaliera) {
        if (sogliaMensile <= 0) {
            throw new ValidazioneException(Bic.class.getSimpleName(), CodiceErrore.SOGLIA_BONIFICO_MENSILE_MINORE_UGUALE_ZERO.getCodice());
        }
        if (sogliaGiornaliera <= 0) {
            throw new ValidazioneException(Bic.class.getSimpleName(), CodiceErrore.SOGLIA_BONIFICO_GIORNALIERA_MINORE_UGUALE_ZERO.getCodice());
        }
        if (sogliaMensile < sogliaGiornaliera) {
            throw new ValidazioneException(Bic.class.getSimpleName(), CodiceErrore.SOGLIE_BONIFICO_NON_VALIDE_NON_COERERENTI.getCodice());
        }
        this.sogliaMensile = sogliaMensile;
        this.sogliaGiornaliera = sogliaGiornaliera;
    }
}
