package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class PredisponiBonificoCmd {
    
    private UsernameCliente usernameCliente;
    private Iban ibanMittente;
    private double importo;
    private String causale;
    private Iban ibanDestinatario;
}
