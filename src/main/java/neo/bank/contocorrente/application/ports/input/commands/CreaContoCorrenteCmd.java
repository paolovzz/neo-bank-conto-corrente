package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class CreaContoCorrenteCmd {
    
    private UsernameCliente usernameCliente;
}
