package neo.bank.contocorrente.domain.models.events;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.SogliaBonifico;

@Value
public class SogliaBonificoMensileImpostata implements EventPayload {

    private SogliaBonifico nuovaSogliaBonifico;
    @Override
    public String eventType() {
        return "SogliaBonificoMensileImpostata";
    }
}
