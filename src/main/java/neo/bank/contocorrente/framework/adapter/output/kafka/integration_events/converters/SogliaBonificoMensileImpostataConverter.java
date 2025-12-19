package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.SogliaBonificoMensileImpostata;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IESogliaBonificoMensileImpostata;

@ApplicationScoped
public class SogliaBonificoMensileImpostataConverter implements IntegrationEventConverter<SogliaBonificoMensileImpostata, IESogliaBonificoMensileImpostata>, IntegrationEventConverterMarker{

    @Override
    public IESogliaBonificoMensileImpostata convert(SogliaBonificoMensileImpostata ev) {

        return new IESogliaBonificoMensileImpostata(ev.getNuovaSogliaBonifico().getSoglia());
    }

    @Override
    public Class<SogliaBonificoMensileImpostata> supportedDomainEvent() {
        return SogliaBonificoMensileImpostata.class;
    }
    
}
