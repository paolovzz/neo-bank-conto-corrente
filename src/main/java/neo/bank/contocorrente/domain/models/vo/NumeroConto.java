package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;



@Getter
@EqualsAndHashCode
public class NumeroConto {

    private String numero;
    
    public NumeroConto(String numero) {
         if (numero == null || numero.isBlank()) {
            throw new ValidazioneException(NumeroConto.class.getSimpleName(), CodiceErrore.NUMERO_CONTO_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.numero = numero;
    }
}
