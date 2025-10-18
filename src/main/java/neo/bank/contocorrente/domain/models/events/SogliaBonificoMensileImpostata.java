package neo.bank.contocorrente.domain.models.events;


public record SogliaBonificoMensileImpostata(int nuovaSogliaBonifico) implements EventPayload {

    @Override
    public String eventType() {
        return "SogliaBonificoMensileImpostata";
    }
}
