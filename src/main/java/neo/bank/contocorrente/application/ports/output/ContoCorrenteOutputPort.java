package neo.bank.contocorrente.application.ports.output;

import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

public interface ContoCorrenteOutputPort {
    
    public void salva(ContoCorrente cc);
    public ContoCorrente recuperaDaId(IdContoCorrente idContoCorrente);
}
