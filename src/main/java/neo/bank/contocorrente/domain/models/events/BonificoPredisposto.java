package neo.bank.contocorrente.domain.models.events;

import java.time.LocalDateTime;

import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

public record BonificoPredisposto(
        Iban ibanMittente,
        Iban ibanDestinatario,
        double importo,
        String causale, IdOperazione idOperazione, LocalDateTime dataOperazione) implements EventPayload {

    @Override
    public String eventType() {
        return "BonificoPredisposto";
    }
}
