package neo.bank.contocorrente.domain.models.events;

import lombok.Value;

@Value
public class SaldoContabileAggiornato implements EventPayload {

    private double importo;
    
    @Override
    public String eventType() {
        return "SaldoContabileAggiornato";
    }
}
