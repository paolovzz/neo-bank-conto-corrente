package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class IdContoCorrente {

    private String id;

    public IdContoCorrente(String id) {
        if (id == null) {
            throw new ValidazioneException(
                IdContoCorrente.class.getSimpleName(),
                CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
        this.id = id;
    }
}
