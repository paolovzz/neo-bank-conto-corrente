package neo.bank.contocorrente.domain.models.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class DataChiusura {

    private LocalDateTime dataOra;

    public DataChiusura(LocalDateTime dataOra) {
        if (dataOra == null) {
            throw new ValidazioneException(DataChiusura.class.getSimpleName(), CodiceErrore.DATA_CHIUSURA_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.dataOra = dataOra;
    }
}
