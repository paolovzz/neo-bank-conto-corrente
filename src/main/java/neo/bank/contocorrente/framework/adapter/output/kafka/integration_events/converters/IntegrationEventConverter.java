package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.contocorrente.domain.models.events.EventPayload;

public interface IntegrationEventConverter<DE extends EventPayload, IE> {
    IE convert(DE domainEvent);
}