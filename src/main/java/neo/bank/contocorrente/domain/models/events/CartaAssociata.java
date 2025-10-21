package neo.bank.contocorrente.domain.models.events;

import neo.bank.contocorrente.domain.models.vo.NumeroCarta;

public record CartaAssociata( NumeroCarta numeroCarta) implements EventPayload {

    @Override
    public String eventType() {
        return "CartaAssociata";
    }
}
