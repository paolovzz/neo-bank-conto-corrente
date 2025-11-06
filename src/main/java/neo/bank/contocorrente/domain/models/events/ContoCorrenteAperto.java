package neo.bank.contocorrente.domain.models.events;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.DataApertura;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class ContoCorrenteAperto implements EventPayload {

    private IdContoCorrente idContoCorrente;
    private UsernameCliente usernameCliente;
    private CoordinateBancarie coordinateBancarie;
    private DataApertura dataApertura;
    private double saldoDisponibile;
    private double saldoContabile;

    @Override
    public String eventType() {
        return "ContoCorrenteAperto";
    }
}
