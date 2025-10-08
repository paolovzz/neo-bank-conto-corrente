package neo.bank.contocorrente.framework.adapter.output.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.SommaBonificiUscita;
import neo.bank.contocorrente.domain.services.TransazioniService;
import neo.bank.contocorrente.framework.adapter.output.rest.TransazioniRestClient;

@ApplicationScoped
@Slf4j
public class TransazioniServiceImpl implements TransazioniService{
    
    @RestClient
    private final TransazioniRestClient client;

    @Inject
    public TransazioniServiceImpl(@RestClient TransazioniRestClient client) {
        this.client = client;
    }

    @Override
    public SommaBonificiUscita richiediTotaleBonificiUscita(Iban iban) {
        log.info("Chiedo totale bonifici in uscita per l'iban [{}]", iban.codice());
            Response response = client.recuperaTotaleBonificiUscita(iban.codice());
            SommaBonificiUscita body = response.readEntity(SommaBonificiUscita.class);
            log.info("Totale bonifici recuperato: [{}]", body);

            return body;
    }
}
