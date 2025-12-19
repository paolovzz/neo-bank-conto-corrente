package neo.bank.contocorrente.domain.models.events;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.SogliaBonifico;

@Value
public class SogliaBonificoGiornalieraImpostata implements EventPayload {

    private SogliaBonifico nuovaSogliaBonifico;
    @Override
    public String eventType() {
        return "SogliaBonificoGiornalieraImpostata";
    }
}
