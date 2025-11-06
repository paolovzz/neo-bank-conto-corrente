package neo.bank.contocorrente.domain.models.events;

import java.time.LocalDateTime;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

@Value
public class BonificoPredisposto implements EventPayload {

    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private double importo;
    private String causale;
    private IdOperazione idOperazione;
    private LocalDateTime dataOperazione;

    @Override
    public String eventType() {
        return "BonificoPredisposto";
    }
}
