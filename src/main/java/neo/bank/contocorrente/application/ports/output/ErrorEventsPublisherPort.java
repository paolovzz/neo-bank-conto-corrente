package neo.bank.contocorrente.application.ports.output;

import neo.bank.contocorrente.domain.models.events.EventPayload;

public interface ErrorEventsPublisherPort {
    void publish(EventPayload event, String aggregateName, String aggregateId);
}
