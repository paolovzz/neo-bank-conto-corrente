package neo.bank.contocorrente.domain.models.aggregates;

import neo.bank.contocorrente.domain.models.events.EventPayload;

public interface Applier {
    void apply(EventPayload event);
}