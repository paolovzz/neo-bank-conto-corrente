package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.ContoCorrenteAperto;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IEContoCorrenteAperto;

@ApplicationScoped
public class ContoCorrenteApertoConverter implements IntegrationEventConverter<ContoCorrenteAperto, IEContoCorrenteAperto>, IntegrationEventConverterMarker{

    @Override
    public IEContoCorrenteAperto convert(ContoCorrenteAperto ev) {

        CoordinateBancarie coordinate = ev.getCoordinateBancarie();
        return new IEContoCorrenteAperto(ev.getIdContoCorrente().getId(), ev.getUsernameCliente().getUsername(), coordinate.getNumeroConto().getNumero(), coordinate.getIban().getCodice(), coordinate.getBic().getCodice(), coordinate.getCab().getCodice(), coordinate.getAbi().getCodice(), ev.getDataApertura().getDataOra(), ev.getSaldoDisponibile(), ev.getSaldoContabile());
    }

    @Override
    public Class<ContoCorrenteAperto> supportedDomainEvent() {
        return ContoCorrenteAperto.class;
    }
    
}
