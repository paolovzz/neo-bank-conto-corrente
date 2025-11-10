package neo.bank.contocorrente.application.ports.input.commands;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

@Value
public class AggiornaSaldoDisponibileCmd {

    private IdContoCorrente idContoCorrente;
    private double importo;
}
