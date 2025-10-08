package neo.bank.contocorrente.framework.adapter.output.rest;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transazioni")
@RegisterRestClient(configKey = "transazioni-client")
public interface TransazioniRestClient {

    @Path("/{iban}/totale/bonifici/uscita")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    Response recuperaTotaleBonificiUscita(
        @PathParam("iban") String iban
    );

}
