package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.ContoCorrenteAperto;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IEContoCorrenteAperto;

@ApplicationScoped
public class ContoCorrenteApertoConverter implements IntegrationEventConverter<ContoCorrenteAperto, IEContoCorrenteAperto>, IntegrationEventConverterMarker{

    @Override
    public IEContoCorrenteAperto convert(ContoCorrenteAperto ev) {

        CoordinateBancarie coordinate = ev.coordinateBancarie();
        return new IEContoCorrenteAperto(ev.idContoCorrente().id(), ev.usernameCliente().username(), coordinate.numeroConto().numero(), coordinate.iban().codice(), coordinate.bic().codice(), coordinate.cab().codice(), coordinate.abi().codice(), ev.dataApertura().dataOra(), ev.saldoDisponibile(), ev.saldoContabile());
    }

    @Override
    public Class<ContoCorrenteAperto> supportedDomainEvent() {
        return ContoCorrenteAperto.class;
    }
    
}
