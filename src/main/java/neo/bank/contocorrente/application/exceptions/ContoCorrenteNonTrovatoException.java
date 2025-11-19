package neo.bank.contocorrente.application.exceptions;

import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

public class ContoCorrenteNonTrovatoException extends RuntimeException {
    
    public ContoCorrenteNonTrovatoException(IdContoCorrente idContoCorrente) {
        super(String.format("ContoCorrente con id [%s] non trovato...", idContoCorrente.getId()));
    }

    public ContoCorrenteNonTrovatoException(Iban iban) {
        super(String.format("ContoCorrente con iban [%s] non trovato...", iban.getCodice()));
    }
}
