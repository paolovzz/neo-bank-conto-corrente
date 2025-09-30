package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;


public record NumeroConto(String numero) {


    public NumeroConto(String numero) {
         if (numero == null) {
            throw new ValidazioneException(NumeroConto.class.getSimpleName(), CodiceErrore.NUMERO_CONTO_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.numero = numero;
    }
}
