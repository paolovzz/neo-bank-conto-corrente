package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.IdCliente;

@Value
public class CreaContoCorrenteCmd {
    
    private IdCliente idCliente;
}
