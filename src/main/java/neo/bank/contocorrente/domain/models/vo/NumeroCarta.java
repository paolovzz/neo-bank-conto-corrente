package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class NumeroCarta {

    private String numero;

    public NumeroCarta(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new ValidazioneException(
                    NumeroCarta.class.getSimpleName(),
                    CodiceErrore.NUMERO_CARTA_NON_PUO_ESSERE_NULL.getCodice());
        }
         this.numero = numero;
    }
}
