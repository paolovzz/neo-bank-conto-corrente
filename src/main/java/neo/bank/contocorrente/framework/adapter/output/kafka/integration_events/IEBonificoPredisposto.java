package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IEBonificoPredisposto implements Serializable {
    private String ibanMittente;
    private String ibanDestinatario;
    private double importo;
    private String causale;
}
