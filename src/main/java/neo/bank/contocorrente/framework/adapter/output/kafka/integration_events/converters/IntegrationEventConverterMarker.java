package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.contocorrente.domain.models.events.EventPayload;

public interface IntegrationEventConverterMarker {
    Class<? extends EventPayload> supportedDomainEvent();
}
