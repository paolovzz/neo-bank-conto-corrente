package neo.bank.contocorrente.framework.adapter.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.contocorrente.application.ContoCorrenteUseCase;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSoglieBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.InviaBonificoCmd;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.SoglieBonifico;
import neo.bank.contocorrente.framework.adapter.input.rest.request.CreaContoCorrenteRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.request.ImpostaSoglieBonificoRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.request.InviaBonificoRequest;
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
        app.creaContoCorrente(new CreaContoCorrenteCmd(new UsernameCliente(cmd.getUsernameCliente())));
        return Response.ok().build();
    }

    @Path("/{id}/soglie-bonifici")
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response impostaSoglieBonifici(@PathParam(value = "id") String idContoCorrente, ImpostaSoglieBonificoRequest request) {
        app.impostaSoglieBonifico(new ImpostaSoglieBonificoCmd(new UsernameCliente(request.getUsernameCliente()), new IdContoCorrente(idContoCorrente), new SoglieBonifico(request.getSogliaMensile(), request.getSogliaGiornaliera())));
        return Response.noContent().build();
    }
    
    @Path("/{id}/invia-bonifico")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response inviaBonifico(@PathParam(value = "id") String idContoCorrente, InviaBonificoRequest request) {
        app.inviaBonifico(new InviaBonificoCmd(new UsernameCliente(request.getUsernameCliente()), new IdContoCorrente(idContoCorrente), request.getImporto(), request.getCausale(), new Iban(request.getIbanDestinatario())));
        return Response.accepted().build();
    }
}
