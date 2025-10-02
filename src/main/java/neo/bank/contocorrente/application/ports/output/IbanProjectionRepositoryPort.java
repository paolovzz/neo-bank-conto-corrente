package neo.bank.contocorrente.application.ports.output;

import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

public interface IbanProjectionRepositoryPort {
    
    public void salva(Iban iban, IdContoCorrente idContoCorrente);
    public IdContoCorrente recuperaDaIban (Iban iban);
}
