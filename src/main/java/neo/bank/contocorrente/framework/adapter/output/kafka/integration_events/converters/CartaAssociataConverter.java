package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IECartaAssociata;
import neo.bank.contocorrente.domain.models.events.CartaAssociata;

@ApplicationScoped
public class CartaAssociataConverter implements IntegrationEventConverter<CartaAssociata, IECartaAssociata>, IntegrationEventConverterMarker {

    @Override
    public IECartaAssociata convert(CartaAssociata domainEvent) {
        return new IECartaAssociata(domainEvent.numeroCarta().numero());
    }

    @Override
    public Class<CartaAssociata> supportedDomainEvent() {
        return CartaAssociata.class;
    }

}
