package neo.bank.contocorrente.framework.adapter.output.rest;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/operazioni")
@RegisterRestClient(configKey = "operazioni-client")
public interface OperazioniRestClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response recuperaDettaglioOperazione(
        @PathParam("id") String idOperazione
    );

}
