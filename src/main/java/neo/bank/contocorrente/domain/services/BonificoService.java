package neo.bank.contocorrente.domain.services;

import neo.bank.contocorrente.domain.models.vo.DatiBonifico;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

public interface BonificoService {
    
    DatiBonifico recuperaDettaglioBonifico(IdOperazione idOperazione);
}
