package neo.bank.contocorrente.application.ports.output;

import java.util.List;

import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.events.EventPayload;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

public interface ContoCorrenteRepositoryPort {
    
    public void save(IdContoCorrente idContoCorrente, List<EventPayload> events);
    public ContoCorrente findById (String aggregateId);
}
