package neo.bank.contocorrente.domain.models.vo;

import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

public record UsernameCliente(String username) {
    public UsernameCliente {
        if (username == null) {
            throw new ValidazioneException(
                UsernameCliente.class.getSimpleName(),
                CodiceErrore.USERNAME_CLIENTE_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
    }
}
