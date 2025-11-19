package neo.bank.contocorrente.framework.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import neo.bank.contocorrente.domain.services.ApplicaBonificoDomainService;
import neo.bank.contocorrente.domain.services.TransazioniService;

@ApplicationScoped
public class Producer {
    
    @Inject
    private TransazioniService transazioniService;
    @Produces
    public ApplicaBonificoDomainService buildApplicaBonificoDomainService() {
        return new ApplicaBonificoDomainService(transazioniService);
    }
}
