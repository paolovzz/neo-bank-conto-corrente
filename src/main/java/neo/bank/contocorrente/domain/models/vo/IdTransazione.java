package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class IdTransazione {

    private String id;

    public IdTransazione(String id) {
        if (id == null) {
            throw new ValidazioneException(
                    IdTransazione.class.getSimpleName(),
                    CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice());
        }
         this.id = id;
    }
}
