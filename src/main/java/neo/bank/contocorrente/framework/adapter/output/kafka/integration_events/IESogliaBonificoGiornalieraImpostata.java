package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IESogliaBonificoGiornalieraImpostata implements Serializable {
    private int sogliaBonificoGiornaliera;
}
