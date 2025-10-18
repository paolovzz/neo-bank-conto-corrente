package neo.bank.contocorrente.domain.models.events;


public record SogliaBonificoGiornalieraImpostata(int nuovaSogliaBonifico) implements EventPayload {

    @Override
    public String eventType() {
        return "SogliaBonificoGiornalieraImpostata";
    }
}
