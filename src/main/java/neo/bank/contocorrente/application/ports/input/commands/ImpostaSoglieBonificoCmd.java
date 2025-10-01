package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;

@Value
public class ImpostaSoglieBonificoCmd {
    
    private IdCliente idCliente;
    private IdContoCorrente idContoCorrente;
    private SoglieBonifico nuovSoglieBonifico;
}
