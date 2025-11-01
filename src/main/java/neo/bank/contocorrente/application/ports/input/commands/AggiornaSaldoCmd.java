package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

@Value
public class AggiornaSaldoCmd {

    private Iban ibanMittente;
    private IdOperazione idOperazione;
    private Iban ibanDestinatario;
    private String causale;
    private double importo;
}
