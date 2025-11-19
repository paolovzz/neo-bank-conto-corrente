package neo.bank.contocorrente.domain.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import lombok.AllArgsConstructor;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.DatiBonifico;
import neo.bank.contocorrente.domain.models.vo.IdTransazione;
import neo.bank.contocorrente.domain.models.vo.VOTransazione;

@AllArgsConstructor
public class ApplicaBonificoDomainService {

    private TransazioniService transazioniService;

    public void applica(ContoCorrente ccMittente, ContoCorrente ccDestinatario, DatiBonifico datiOperazione, double importo ) {
        if(ccMittente != null) {
            ccMittente.aggiornaSaldoContabile(datiOperazione.getImporto() * -1);
            transazioniService.creaTransazione(new VOTransazione(new IdTransazione(UUID.randomUUID().toString()), ccMittente.getIdContoCorrente(), datiOperazione.getIdOperazione(), datiOperazione.getImporto(), datiOperazione.getIbanDestinatario(), LocalDateTime.now(ZoneOffset.UTC), datiOperazione.getCausale(), TipologiaFlusso.ADDEBITO));
        }
        
        if(ccDestinatario != null) {
            ccDestinatario.aggiornaSaldoContabile(importo);
            ccDestinatario.aggiornaSaldoDisponibile(importo);
            transazioniService.creaTransazione(new VOTransazione(new IdTransazione(UUID.randomUUID().toString()), ccDestinatario.getIdContoCorrente(), datiOperazione.getIdOperazione(), datiOperazione.getImporto(), datiOperazione.getIbanMittente(), LocalDateTime.now(ZoneOffset.UTC), datiOperazione.getCausale(), TipologiaFlusso.ACCREDITO));
        }
        
            
    }
    
}
