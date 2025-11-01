package neo.bank.contocorrente.domain.models.vo;

import java.time.LocalDateTime;

import lombok.Value;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;

@Value
public class Transazione {
   
    private IdTransazione idTransazione;
    private IdContoCorrente idContoCorrente;
    private IdOperazione idOperazione;
    private double importo;
    private Iban iban;
    private LocalDateTime dataCreazione;
    private String causale;
    private TipologiaFlusso tipologiaFlusso;
}
