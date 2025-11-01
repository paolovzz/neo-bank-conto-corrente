package neo.bank.contocorrente.domain.models.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;

@Getter
@EqualsAndHashCode(callSuper=true)
public class Bonifico extends DatiOperazione {

    private Iban mittente;
    private Iban destinatario;
    private String causale;

    public Bonifico(double importo, TipologiaFlusso tipologiaFlusso, Iban mittente, Iban destinatario, String causale) {
        super(importo, tipologiaFlusso);
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.causale = causale;
    }
}
