package neo.bank.contocorrente.framework.adapter.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.contocorrente.application.ContoCorrenteUseCase;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoGiornalieraCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoMensileCmd;
import neo.bank.contocorrente.application.ports.input.commands.PredisponiBonificoCmd;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;
import neo.bank.contocorrente.framework.adapter.input.rest.request.CreaContoCorrenteRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.request.ImpostaSogliaBonificoRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.request.InviaBonificoRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.response.ContoCorrenteInfoResponse;

@Path("/cc")
@ApplicationScoped
public class ContoCorrenteResource {

    @Inject
    private ContoCorrenteUseCase app;

    @Path("/{iban}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaContoCorrenteDaIban(@PathParam(value = "iban") String iban) {

        ContoCorrente contoCorrente = app.recuperaContoCorrenteDaIban(new Iban(iban));
        ContoCorrenteInfoResponse bodyResponse = new ContoCorrenteInfoResponse(contoCorrente);
        return Response.ok(bodyResponse).build();
    }

    @Path("/{iban}/verifica")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response verificaContoCorrente(@PathParam(value = "iban") String iban, @QueryParam("cliente") String cliente) {

        ContoCorrente contoCorrente = app.recuperaContoCorrenteDaIban(new Iban(iban));
        if(contoCorrente.getIntestatario().equals(new UsernameCliente(cliente))) {
            return Response.ok().build();
        } else {
            return Response.status(403).build();
        }
    }

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response creaContoCorrente(CreaContoCorrenteRequest cmd) {
        app.creaContoCorrente(new CreaContoCorrenteCmd(new UsernameCliente(cmd.getUsernameCliente())));
        return Response.ok().build();
    }

    @Path("/soglia-bonifico-giornaliera")
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response impostaSogliaBonificoGiornaliera( ImpostaSogliaBonificoRequest request) {
        app.impostaSogliaBonificoGiornaliera(new ImpostaSogliaBonificoGiornalieraCmd(new UsernameCliente(request.getUsernameCliente()), new Iban(request.getIban()), request.getNuovaSoglia()));
        return Response.noContent().build();
    }

    @Path("/soglia-bonifico-mensile")
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response impostaSogliaBonificoMensile(ImpostaSogliaBonificoRequest request) {
        app.impostaSogliaBonificoMensile(new ImpostaSogliaBonificoMensileCmd(new UsernameCliente(request.getUsernameCliente()), new Iban(request.getIban()), request.getNuovaSoglia()));
        return Response.noContent().build();
    }
    
    @Path("/predisponi-bonifico")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response inviaBonifico(InviaBonificoRequest request) {
        app.predisponiBonifico(new PredisponiBonificoCmd(new UsernameCliente(request.getUsernameCliente()), new Iban(request.getIbanMittente()), request.getImporto(), request.getCausale(), new Iban(request.getIbanDestinatario())));
        return Response.accepted().build();
    }
}
