package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.BonificoPredisposto;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IEBonificoPredisposto;

@ApplicationScoped
public class BonificoPredispostoConverter implements IntegrationEventConverter<BonificoPredisposto, IEBonificoPredisposto>, IntegrationEventConverterMarker{

    @Override
    public IEBonificoPredisposto convert(BonificoPredisposto ev) {
        return new IEBonificoPredisposto(ev.getIbanMittente().getCodice(), ev.getIbanDestinatario().getCodice(), ev.getImporto(), ev.getCausale(), ev.getIdOperazione().getId(), ev.getDataOperazione());
    }

    @Override
    public Class<BonificoPredisposto> supportedDomainEvent() {
        return BonificoPredisposto.class;
    }
    
}
