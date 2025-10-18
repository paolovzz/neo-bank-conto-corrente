package neo.bank.contocorrente.domain.models.events;

import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.DataApertura;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

public record ContoCorrenteAperto(
        IdContoCorrente idContoCorrente,
        UsernameCliente usernameCliente,
        CoordinateBancarie coordinateBancarie,
        DataApertura dataApertura,
        double saldoDisponibile, double saldoContabile) implements EventPayload {

    @Override
    public String eventType() {
        return "ContoCorrenteAperto";
    }
}
