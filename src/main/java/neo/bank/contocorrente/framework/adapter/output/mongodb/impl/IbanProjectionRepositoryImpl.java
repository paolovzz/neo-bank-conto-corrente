package neo.bank.contocorrente.framework.adapter.output.mongodb.impl;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.output.IbanProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.framework.adapter.output.mongodb.entities.IbanProjectionEntity;

@ApplicationScoped
@Slf4j
public class IbanProjectionRepositoryImpl implements PanacheMongoRepositoryBase<IbanProjectionEntity, String>, IbanProjectionRepositoryPort {
    
    
    @Override
    public void salva(Iban iban, IdContoCorrente idContoCorrente) {
        log.info("Aggiorno la projection...");
        persist(new IbanProjectionEntity(iban.codice(), idContoCorrente.id()));
    }

    @Override
    public IdContoCorrente recuperaDaIban(Iban iban) {
        IbanProjectionEntity entity = findById(iban.codice());
        return entity == null ? null : new IdContoCorrente(entity.getIdContoCorrente());
    }


}
