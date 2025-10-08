package neo.bank.contocorrente.domain.models.events;

import neo.bank.contocorrente.domain.models.vo.Iban;

public record BonificoPredisposto(
        Iban ibanMittente,
        Iban ibanDestinatario,
        double importo,
        String causale) implements EventPayload {

    @Override
    public String eventType() {
        return "BonificoPredisposto";
    }
}
