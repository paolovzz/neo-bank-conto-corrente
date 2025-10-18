package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.SogliaBonificoGiornalieraImpostata;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IESogliaBonificoGiornalieraImpostata;

@ApplicationScoped
public class SogliaBonificoGiornalieraImpostataConverter implements IntegrationEventConverter<SogliaBonificoGiornalieraImpostata, IESogliaBonificoGiornalieraImpostata>, IntegrationEventConverterMarker{

    @Override
    public IESogliaBonificoGiornalieraImpostata convert(SogliaBonificoGiornalieraImpostata ev) {

        return new IESogliaBonificoGiornalieraImpostata(ev.nuovaSogliaBonifico());
    }

    @Override
    public Class<SogliaBonificoGiornalieraImpostata> supportedDomainEvent() {
        return SogliaBonificoGiornalieraImpostata.class;
    }
    
}
