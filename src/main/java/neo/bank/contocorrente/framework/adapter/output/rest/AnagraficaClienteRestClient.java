package neo.bank.contocorrente.framework.adapter.output.rest;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clienti")
@RegisterRestClient(configKey = "anagrafica-cliente")
public interface AnagraficaClienteRestClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response recuperaCliente(@HeaderParam("X-Authenticated-User") String username);

}
