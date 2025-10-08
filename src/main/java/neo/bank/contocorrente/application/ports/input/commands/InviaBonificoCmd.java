package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

@Value
public class InviaBonificoCmd {
    
    private IdCliente idCliente;
    private IdContoCorrente idContoCorrente;
    private double importo;
    private String causale;
    private Iban ibanDestinatario;
}
