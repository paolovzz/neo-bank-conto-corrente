package neo.bank.contocorrente.domain.models.events;

public record SaldoContabileAggiornato(double importo) implements EventPayload {

    @Override
    public String eventType() {
        return "SaldoContabileAggiornato";
    }
}
