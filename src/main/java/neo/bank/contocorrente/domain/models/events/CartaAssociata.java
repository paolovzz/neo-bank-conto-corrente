package neo.bank.contocorrente.domain.models.events;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.NumeroCarta;

@Value
public class CartaAssociata implements EventPayload {

    private NumeroCarta numeroCarta;

    @Override
    public String eventType() {
        return "CartaAssociata";
    }
}
