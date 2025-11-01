package neo.bank.contocorrente.domain.models.entities;

import lombok.Getter;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;

@Getter
public abstract class DatiOperazione {

    private double importo; 
    private TipologiaFlusso tipologiaFlusso;
    public DatiOperazione(double importo, TipologiaFlusso tipologiaFlusso) {
        this.importo = importo;
        this.tipologiaFlusso = tipologiaFlusso;
    }

    
    
}
