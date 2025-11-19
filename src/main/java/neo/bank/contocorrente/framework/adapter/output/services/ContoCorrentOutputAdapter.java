package neo.bank.contocorrente.framework.adapter.output.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import neo.bank.contocorrente.application.exceptions.ContoCorrenteNonTrovatoException;
import neo.bank.contocorrente.application.ports.output.ContoCorrenteOutputPort;
import neo.bank.contocorrente.application.ports.output.ContoCorrenteRepositoryPort;
import neo.bank.contocorrente.application.ports.output.EventsPublisherPort;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.events.EventPayload;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ContoCorrentOutputAdapter  implements ContoCorrenteOutputPort{

    private final ContoCorrenteRepositoryPort ccRepo;
    private final EventsPublisherPort publisherPort;

    @Override
    public void salva(ContoCorrente cc) {

        List<EventPayload> events = cc.popChanges();
        ccRepo.save(cc.getIdContoCorrente(), events);
        publisherPort.publish(ContoCorrente.AGGREGATE_NAME, cc.getIdContoCorrente().getId(), events);
    }

    @Override
    public ContoCorrente recuperaDaId(IdContoCorrente idContoCorrente) {
        ContoCorrente cc = ccRepo.findById(idContoCorrente.getId());
        if(cc == null) {
            throw new ContoCorrenteNonTrovatoException(idContoCorrente);
        }
       return cc;
    }
    
}
