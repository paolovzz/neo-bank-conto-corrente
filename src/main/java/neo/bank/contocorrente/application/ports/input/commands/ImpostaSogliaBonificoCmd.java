package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class ImpostaSogliaBonificoCmd {
    
    private UsernameCliente usernameCliente;
    private Iban iban;
    private int nuovaSogliaBonifico;
}
