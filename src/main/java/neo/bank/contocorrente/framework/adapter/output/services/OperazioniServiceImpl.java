package neo.bank.contocorrente.framework.adapter.output.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.models.vo.DettaglioOperazione;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.services.OperazioniService;
import neo.bank.contocorrente.framework.adapter.output.rest.OperazioniRestClient;

@ApplicationScoped
@Slf4j
public class OperazioniServiceImpl implements OperazioniService{
    
    @RestClient
    private final OperazioniRestClient client;

    @Inject
    public OperazioniServiceImpl(@RestClient OperazioniRestClient client) {
        this.client = client;
    }

    @Override
    public DettaglioOperazione recuperaDettaglioOperazione(IdOperazione idOperazione) {
        log.info("Recupero dettaglio operazione con id [{}]", idOperazione.id());
        Response response = client.recuperaDettaglioOperazione(idOperazione.id());
        DettaglioOperazione body = response.readEntity(DettaglioOperazione.class);
        log.info("Dettaglio recuperato");
        return body;
    }
}
