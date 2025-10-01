package neo.bank.contocorrente.framework.adapter.input.rest.request;

import lombok.Value;

@Value
public class ImpostaSoglieBonificoRequest {
    private String idCliente;
    private int sogliaMensile;
    private int sogliaGiornaliera ;
}
