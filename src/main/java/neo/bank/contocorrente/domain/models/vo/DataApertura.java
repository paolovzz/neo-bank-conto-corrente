package neo.bank.contocorrente.domain.models.vo;

import java.time.LocalDateTime;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record DataApertura(LocalDateTime dataOra) {


    public DataApertura(LocalDateTime dataOra) {
        if (dataOra == null) {
            throw new ValidazioneException(DataApertura.class.getSimpleName(), CodiceErrore.DATA_APERTURA_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.dataOra = dataOra;
    }
}
