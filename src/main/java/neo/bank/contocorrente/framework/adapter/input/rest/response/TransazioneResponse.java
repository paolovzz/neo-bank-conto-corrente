package neo.bank.contocorrente.framework.adapter.input.rest.response;

import java.time.LocalDateTime;

import lombok.Getter;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Transazione;

@Getter
public class TransazioneResponse {
   
    private String idTransazione;
    private String idContoCorrente;
    private String idOperazione;
    private double importo;
    private String iban;
    private LocalDateTime dataCreazione;
    private String causale;
    private TipologiaFlusso tipologiaFlusso;

    public TransazioneResponse(Transazione transazione) {
        this.idTransazione = transazione.getIdTransazione().getId();
        this.idContoCorrente = transazione.getIdContoCorrente().getId();
        this.idOperazione = transazione.getIdOperazione().getId();
        this.importo = transazione.getImporto();
        this.dataCreazione = transazione.getDataCreazione();
        this.causale = transazione.getCausale();
        this.tipologiaFlusso = transazione.getTipologiaFlusso();
        this.iban = transazione.getIban().getCodice();
    }

    

}
