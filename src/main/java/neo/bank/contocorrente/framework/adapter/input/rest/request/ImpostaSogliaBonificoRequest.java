package neo.bank.contocorrente.framework.adapter.input.rest.request;

import lombok.Value;

@Value
public class ImpostaSogliaBonificoRequest {
    private String iban;
    private String usernameCliente;
    private int nuovaSoglia;
}
