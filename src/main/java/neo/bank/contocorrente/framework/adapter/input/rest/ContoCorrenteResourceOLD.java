// package neo.bank.contocorrente.framework.adapter.input.rest;

// import java.time.LocalDate;
// import java.time.LocalTime;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.DefaultValue;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.HeaderParam;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.PUT;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.PathParam;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
// import jakarta.ws.rs.core.Context;
// import jakarta.ws.rs.core.HttpHeaders;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// import lombok.extern.slf4j.Slf4j;
// import neo.bank.contocorrente.application.ContoCorrenteUseCase;
// import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
// import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoGiornalieraCmd;
// import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoMensileCmd;
// import neo.bank.contocorrente.application.ports.input.commands.PredisponiBonificoCmd;
// import neo.bank.contocorrente.application.ports.input.commands.RecuperaContoCorrenteCmd;
// import neo.bank.contocorrente.application.ports.input.commands.RecuperaTransazioniCmd;
// import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
// import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
// import neo.bank.contocorrente.domain.models.vo.Iban;
// import neo.bank.contocorrente.domain.models.vo.RispostaPaginata;
// import neo.bank.contocorrente.domain.models.vo.Transazione;
// import neo.bank.contocorrente.domain.models.vo.UsernameCliente;
// import neo.bank.contocorrente.framework.adapter.input.rest.request.ImpostaSogliaBonificoRequest;
// import neo.bank.contocorrente.framework.adapter.input.rest.request.InviaBonificoRequest;
// import neo.bank.contocorrente.framework.adapter.input.rest.response.ContoCorrenteInfoResponse;
// import neo.bank.contocorrente.framework.adapter.input.rest.response.TransazioneResponse;

// @Path("/cc")
// @ApplicationScoped
// @Slf4j
// public class ContoCorrenteResource {

//     @Inject
//     private ContoCorrenteUseCase app;

//     @Context
//     private HttpHeaders headers;

//     @Path("/{iban}")
//     @GET
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response recuperaContoCorrenteDaIban(@HeaderParam("X-Authenticated-User") String authenticatedUser, @PathParam(value = "iban") String iban) {

        // ContoCorrente contoCorrente = app.recuperaContoCorrenteDaIban(new RecuperaContoCorrenteCmd(new UsernameCliente(authenticatedUser), new Iban(iban)));
        // ContoCorrenteInfoResponse bodyResponse = new ContoCorrenteInfoResponse(contoCorrente);
        // return Response.ok(bodyResponse).build();
//     }

//     @POST
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response creaContoCorrente(@HeaderParam("X-Authenticated-User") String authenticatedUser) {
        // app.creaContoCorrente(new CreaContoCorrenteCmd(new UsernameCliente(authenticatedUser)));
        // return Response.ok().build();
//     }

//     @Path("/soglia-bonifico-giornaliera")
//     @PUT
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response impostaSogliaBonificoGiornaliera(@HeaderParam("X-Authenticated-User") String authenticatedUser, ImpostaSogliaBonificoRequest request) {
        // app.impostaSogliaBonificoGiornaliera(new ImpostaSogliaBonificoGiornalieraCmd(new UsernameCliente(authenticatedUser), new Iban(request.getIban()), request.getNuovaSoglia()));
        // return Response.noContent().build();
//     }

//     @Path("/soglia-bonifico-mensile")
//     @PUT
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response impostaSogliaBonificoMensile(@HeaderParam("X-Authenticated-User") String authenticatedUser,ImpostaSogliaBonificoRequest request) {
//         app.impostaSogliaBonificoMensile(new ImpostaSogliaBonificoMensileCmd(new UsernameCliente(authenticatedUser), new Iban(request.getIban()), request.getNuovaSoglia()));
//         return Response.noContent().build();
//     }
    
//     @Path("/predisponi-bonifico")
//     @POST
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response inviaBonifico(@HeaderParam("X-Authenticated-User") String authenticatedUser, InviaBonificoRequest request) {

        // app.predisponiBonifico(new PredisponiBonificoCmd(new UsernameCliente(authenticatedUser), new Iban(request.getIbanMittente()), request.getImporto(), request.getCausale(), new Iban(request.getIbanDestinatario())));
        // return Response.accepted().build();
//     }


//     @Path("/{iban}/transazioni")
//     @GET
//     @Produces(value = MediaType.APPLICATION_JSON)
//     public Response recuperaTransazioni(
//         @HeaderParam("X-Authenticated-User") String authenticatedUser,
//         @PathParam(value = "iban") String iban, 
//         @QueryParam(value = "dataCreazioneMin" ) LocalDate dataCreazioneMin,
//         @QueryParam(value = "dataCreazioneMax" ) LocalDate dataCreazioneMax,
//         @QueryParam(value = "tipologiaFlusso" ) TipologiaFlusso tipologiaFlusso,
//         @QueryParam(value = "importoMin" ) Double importoMin,
//         @QueryParam(value = "importoMax" ) Double importoMax,
//         @QueryParam(value = "numeroPagina") @DefaultValue("0") Integer numeroPagina,
//         @QueryParam(value = "dimensionePagina") @DefaultValue("10") Integer dimensionePagina
//     ) {

        // RispostaPaginata<Transazione> rp = app.recuperaTransazioni(new RecuperaTransazioniCmd(new UsernameCliente(authenticatedUser), new Iban(iban), dataCreazioneMin != null ? dataCreazioneMin.atStartOfDay() : null, dataCreazioneMax != null ? dataCreazioneMax.atTime(LocalTime.MAX): null, importoMin, importoMax, tipologiaFlusso, numeroPagina, dimensionePagina));
        // RispostaPaginata<TransazioneResponse> risultato = new RispostaPaginata<>(rp.getResult().stream().map(TransazioneResponse::new).toList(), rp.getNumeroPagina(), rp.getDimensionePagina(), rp.getTotaleRisultati());
        // return Response.ok(risultato).build();
//     }
// }
