package neo.bank.contocorrente.domain.models.events;

import lombok.Value;

@Value
public class SaldoDisponibileAggiornato implements EventPayload {

    private double importo;
    @Override
    public String eventType() {
        return "SaldoDisponibileAggiornato";
    }
}
