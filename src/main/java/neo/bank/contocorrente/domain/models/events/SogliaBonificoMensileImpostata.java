package neo.bank.contocorrente.domain.models.events;

import lombok.Value;

@Value
public class SogliaBonificoMensileImpostata implements EventPayload {

    private int nuovaSogliaBonifico;
    @Override
    public String eventType() {
        return "SogliaBonificoMensileImpostata";
    }
}
