package neo.bank.contocorrente.framework.adapter.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.contocorrente.application.ContoCorrenteUseCase;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.framework.adapter.input.rest.request.CreaContoCorrenteRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.response.ContoCorrenteInfoResponse;

@Path("/cc")
@ApplicationScoped
public class ContoCorrenteResource {

    @Inject
    private ContoCorrenteUseCase app;

    @Path("/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaContoCorrenteDaId(@PathParam(value = "id") String idContoCorrente) {

        ContoCorrente contoCorrente = app.recuperaContoCorrenteDaId(new IdContoCorrente(idContoCorrente));
        ContoCorrenteInfoResponse bodyResponse = new ContoCorrenteInfoResponse(contoCorrente);
        return Response.ok(bodyResponse).build();
    }

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response creaContoCorrente(CreaContoCorrenteRequest cmd) {
        app.creaContoCorrente(new CreaContoCorrenteCmd(new IdCliente(cmd.getIdCliente())));
        return Response.ok().build();
    }
    
}
