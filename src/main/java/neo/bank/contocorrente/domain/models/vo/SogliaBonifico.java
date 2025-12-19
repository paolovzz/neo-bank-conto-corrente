package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class SogliaBonifico {

    private int soglia;

    public SogliaBonifico(int soglia) {
        if (soglia <= 0) {
            throw new ValidazioneException(
                SogliaBonifico.class.getSimpleName(),
                CodiceErrore.SOGLIA_BONIFICO_NON_PUO_ESSERE_MINORE_UGUALE_A_ZERO.getCodice()
            );
        }
        this.soglia = soglia;
    }
}
