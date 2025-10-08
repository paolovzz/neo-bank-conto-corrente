package neo.bank.contocorrente.domain.models.events;

public record SaldoDisponibileAggiornato(double importo) implements EventPayload {

    @Override
    public String eventType() {
        return "SaldoDisponibileAggiornato";
    }
}
