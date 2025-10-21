package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.NumeroCarta;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class AssociaCartaCmd {
    
    private NumeroCarta numeroCarta;
    private Iban iban;
    private UsernameCliente usernameCliente;
}
