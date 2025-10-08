package neo.bank.contocorrente.domain.services;

import neo.bank.contocorrente.domain.models.vo.DettaglioOperazione;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

public interface OperazioniService {
    
    DettaglioOperazione recuperaDettaglioOperazione(IdOperazione idOperazione);
}
