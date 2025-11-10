package neo.bank.contocorrente.domain.models.events;

import lombok.Value;
import neo.bank.contocorrente.domain.models.vo.Iban;

@Value
public class BonificoPredisposto implements EventPayload {

    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private double importo;
    private String causale;

    @Override
    public String eventType() {
        return "BonificoPredisposto";
    }
}
