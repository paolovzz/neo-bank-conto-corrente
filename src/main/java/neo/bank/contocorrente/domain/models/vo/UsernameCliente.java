package neo.bank.contocorrente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.domain.models.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class UsernameCliente {

    private String username;

    public UsernameCliente(String username) {
        if (username == null || username.isBlank())  {
            throw new ValidazioneException(
                    UsernameCliente.class.getSimpleName(),
                    CodiceErrore.USERNAME_CLIENTE_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.username = username;
    }
}
