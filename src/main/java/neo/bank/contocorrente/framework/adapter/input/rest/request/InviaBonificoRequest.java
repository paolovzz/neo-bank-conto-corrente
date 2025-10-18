package neo.bank.contocorrente.framework.adapter.input.rest.request;

import lombok.Value;

@Value
public class InviaBonificoRequest {
    private String usernameCliente;
    private String ibanMittente;
    private String ibanDestinatario;
    private double importo;
    private String causale;

}
