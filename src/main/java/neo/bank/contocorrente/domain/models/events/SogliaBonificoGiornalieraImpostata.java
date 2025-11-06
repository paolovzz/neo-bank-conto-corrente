package neo.bank.contocorrente.domain.models.events;

import lombok.Value;

@Value
public class SogliaBonificoGiornalieraImpostata implements EventPayload {

    private int nuovaSogliaBonifico;
    @Override
    public String eventType() {
        return "SogliaBonificoGiornalieraImpostata";
    }
}
