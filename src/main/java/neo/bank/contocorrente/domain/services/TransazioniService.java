package neo.bank.contocorrente.domain.services;

import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.SommaBonificiUscita;

public interface TransazioniService {
    
    SommaBonificiUscita richiediTotaleBonificiUscita(Iban iban);
}
