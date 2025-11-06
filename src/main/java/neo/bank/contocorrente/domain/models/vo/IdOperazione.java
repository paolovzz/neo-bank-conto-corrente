package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class IdOperazione {

    private String id;

    public IdOperazione(String id) {
        if (id == null) {
            throw new ValidazioneException(
                IdOperazione.class.getSimpleName(),
                CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
        this.id = id;
    }
}
