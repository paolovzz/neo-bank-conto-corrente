package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class Abi {

    private String codice;
    public Abi(String codice) {
        if (codice == null || codice.isBlank()) {
            throw new ValidazioneException(Abi.class.getSimpleName(), CodiceErrore.CODICE_ABI_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
