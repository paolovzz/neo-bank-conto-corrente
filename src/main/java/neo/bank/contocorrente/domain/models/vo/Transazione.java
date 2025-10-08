package neo.bank.contocorrente.domain.models.vo;

import java.time.LocalDateTime;

import lombok.Value;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;

@Value
public class Transazione {
   
    private String idTransazione;
    private String idOperazione;
    private double importo;
    private String iban;
    private LocalDateTime dataCreazione;
    private String causale;
    private TipologiaFlusso tipologiaFlusso;
}
