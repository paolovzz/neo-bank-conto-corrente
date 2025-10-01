package neo.bank.contocorrente.domain.models.events;

import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;

public record SoglieBonificoImpostate(
        SoglieBonifico soglieBonifico) implements EventPayload {

    @Override
    public String eventType() {
        return "SoglieBonificoImpostate";
    }
}
