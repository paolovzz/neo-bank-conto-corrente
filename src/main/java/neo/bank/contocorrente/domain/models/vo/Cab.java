package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record Cab ( String codice){


    public Cab(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Cab.class.getSimpleName(), CodiceErrore.CODICE_CAB_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
