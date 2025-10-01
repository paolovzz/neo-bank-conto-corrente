package neo.bank.contocorrente.framework.adapter.output.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.framework.adapter.output.rest.AnagraficaClienteRestClient;

@ApplicationScoped
@Slf4j
public class AnagraficaClienteServiceImpl implements AnagraficaClienteService{
    
    @RestClient
    private final AnagraficaClienteRestClient client;

    @Inject
    public AnagraficaClienteServiceImpl(@RestClient AnagraficaClienteRestClient client) {
        this.client = client;
    }

    @Override
    public boolean richiediVerificaCliente(IdCliente idCliente) {
       log.info("Chiedo verifica riguarda l'esistenza del cliente [{}]", idCliente.id());
        try {
            client.recuperaCliente(idCliente.id());
            log.info("Verifica conclusa positivamente");
            return true;
        } catch(WebApplicationException ex) {
            if(ex.getResponse().getStatus() == 404) {
                return false;
            }
            throw ex;
        }
    }
}
