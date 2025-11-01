package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.BonificoPredisposto;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IEBonificoPredisposto;

@ApplicationScoped
public class BonificoPredispostoConverter implements IntegrationEventConverter<BonificoPredisposto, IEBonificoPredisposto>, IntegrationEventConverterMarker{

    @Override
    public IEBonificoPredisposto convert(BonificoPredisposto ev) {
        return new IEBonificoPredisposto(ev.ibanMittente().codice(), ev.ibanDestinatario().codice(), ev.importo(), ev.causale(), ev.idOperazione().id(), ev.dataOperazione());
    }

    @Override
    public Class<BonificoPredisposto> supportedDomainEvent() {
        return BonificoPredisposto.class;
    }
    
}
