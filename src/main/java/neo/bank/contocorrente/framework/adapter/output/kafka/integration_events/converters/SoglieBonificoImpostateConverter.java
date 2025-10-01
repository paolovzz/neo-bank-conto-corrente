package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.SoglieBonificoImpostate;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IESoglieBonificoImpostate;

@ApplicationScoped
public class SoglieBonificoImpostateConverter implements IntegrationEventConverter<SoglieBonificoImpostate, IESoglieBonificoImpostate>, IntegrationEventConverterMarker{

    @Override
    public IESoglieBonificoImpostate convert(SoglieBonificoImpostate ev) {

        return new IESoglieBonificoImpostate(ev.soglieBonifico().sogliaMensile(), ev.soglieBonifico().sogliaGiornaliera());
    }

    @Override
    public Class<SoglieBonificoImpostate> supportedDomainEvent() {
        return SoglieBonificoImpostate.class;
    }
    
}
