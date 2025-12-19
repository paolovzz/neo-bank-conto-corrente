package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class Cab {

    private String codice;

    public Cab(String codice) {
        if (codice == null || codice.isBlank()) {
            throw new ValidazioneException(Cab.class.getSimpleName(),
                    CodiceErrore.CODICE_CAB_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
