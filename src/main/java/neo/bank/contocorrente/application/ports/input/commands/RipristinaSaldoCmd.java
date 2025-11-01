package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

@Value
public class RipristinaSaldoCmd {
    
    private Iban ibanMittente;
    private IdOperazione idOperazione;
    private double importo;
}
