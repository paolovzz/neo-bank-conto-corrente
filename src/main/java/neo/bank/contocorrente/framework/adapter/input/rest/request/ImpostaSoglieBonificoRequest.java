package neo.bank.contocorrente.framework.adapter.input.rest.request;

import lombok.Value;

@Value
public class ImpostaSoglieBonificoRequest {
    private String usernameCliente;
    private int sogliaMensile;
    private int sogliaGiornaliera ;
}
