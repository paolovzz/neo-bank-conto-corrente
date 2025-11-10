package neo.bank.contocorrente.framework.adapter.output.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.models.vo.DatiBonifico;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.services.BonificoService;
import neo.bank.contocorrente.framework.adapter.output.rest.BonificoRestClient;
import neo.bank.contocorrente.framework.adapter.output.rest.response.BonificoInfoResponse;

@ApplicationScoped
@Slf4j
public class BonificoServiceImpl implements BonificoService{
    
    @RestClient
    private final BonificoRestClient client;

    @Inject
    public BonificoServiceImpl(@RestClient BonificoRestClient client) {
        this.client = client;
    }

    @Override
    public DatiBonifico recuperaDettaglioBonifico(IdOperazione idOperazione) {
        log.info("Recupero dettaglio bonifico con id [{}]", idOperazione.getId());
        Response response = client.recuperaBonifico(idOperazione.getId());
        BonificoInfoResponse bonifico = response.readEntity(BonificoInfoResponse.class);
        log.info("Recupera dettaglio bonifico");
        return new DatiBonifico(new IdOperazione(bonifico.getIdOperazione()), new Iban(bonifico.getIbanMittente()),
                new Iban(bonifico.getIbanDestinatario()), bonifico.getImporto(), bonifico.getCausale());
    }
}
