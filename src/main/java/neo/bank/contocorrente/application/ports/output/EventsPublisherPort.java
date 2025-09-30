package neo.bank.contocorrente.application.ports.output;

import java.util.List;

import neo.bank.contocorrente.domain.models.events.EventPayload;

public interface EventsPublisherPort {
    void publish(String aggregateName, String aggregateId, List<EventPayload> events);
}
