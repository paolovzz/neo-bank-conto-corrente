package neo.bank.contocorrente.framework.adapter.input.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ContoCorrenteUseCase;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.PredisponiBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.RecuperaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.RecuperaTransazioniCmd;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;
import neo.bank.contocorrente.domain.models.vo.VORispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.VOTransazione;
import neo.bank.contocorrente.framework.adapter.input.rest.api.ContoCorrenteApi;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ContoCorrenteInfoResponse;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ImpostaSogliaBonificoRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.model.PredisponiBonificoRequest;
import neo.bank.contocorrente.framework.adapter.input.rest.model.RispostaPaginataTransazioni;
import neo.bank.contocorrente.framework.adapter.input.rest.model.TipologiaFlussoEnum;
import neo.bank.contocorrente.framework.adapter.input.rest.model.TransazioneResponse;

@ApplicationScoped
@Slf4j
public class ContoCorrenteResource implements ContoCorrenteApi {

    @Inject
    private ContoCorrenteUseCase app;

    @Override
    public Response creaContoCorrente(String xAuthenticatedUser) {
        log.info("Richiesta creazione conto corrente: [{}]", xAuthenticatedUser);
        app.creaContoCorrente(new CreaContoCorrenteCmd(new UsernameCliente(xAuthenticatedUser)));
        return Response.status(201).build();
    }

    @Override
    public Response impostaSogliaBonificoGiornaliera(String xAuthenticatedUser,
            ImpostaSogliaBonificoRequest request) {

        log.info("Richiesto aggiornamento soglia bonifico giornaliera : [{}] - {}", xAuthenticatedUser, request);
        app.impostaSogliaBonificoGiornaliera(new ImpostaSogliaBonificoCmd(new UsernameCliente(xAuthenticatedUser), new Iban(request.getIban()), request.getNuovaSoglia()));
        return Response.noContent().build();
    }

    @Override
    public Response impostaSogliaBonificoMensile(String xAuthenticatedUser, ImpostaSogliaBonificoRequest request) {
        
        log.info("Richiesto aggiornamento soglia bonifico mensile : [{}] - {}", xAuthenticatedUser, request);
        app.impostaSogliaBonificoMensile(new ImpostaSogliaBonificoCmd(new UsernameCliente(xAuthenticatedUser), new Iban(request.getIban()), request.getNuovaSoglia()));
        return Response.noContent().build();
    }

    @Override
    public Response predisponiBonifico(String xAuthenticatedUser, PredisponiBonificoRequest request) {
        
        log.info("Richiesta predisposizione bonifico : [{}] - {}", xAuthenticatedUser, request);
        app.predisponiBonifico(new PredisponiBonificoCmd(new UsernameCliente(xAuthenticatedUser), new Iban(request.getIbanMittente()), request.getImporto(), request.getCausale(), new Iban(request.getIbanDestinatario())));
        return Response.accepted().build();
    }

    @Override
    public Response recuperaContoCorrenteDaIban(String xAuthenticatedUser, String iban) {
        
        log.info("Richiesta recupero info CC da iban  : [{}] - {}", xAuthenticatedUser, iban);
        ContoCorrente contoCorrente = app.recuperaContoCorrenteDaIban(new RecuperaContoCorrenteCmd(new UsernameCliente(xAuthenticatedUser), new Iban(iban)));
        ContoCorrenteInfoResponse bodyResponse = ContoCorrenteInfoResponse.builder()
                                                    .abi(contoCorrente.getCoordinateBancarie().getAbi().getCodice())
                                                    .bic(contoCorrente.getCoordinateBancarie().getBic().getCodice())
                                                    .cab(contoCorrente.getCoordinateBancarie().getCab().getCodice())
                                                    .iban(contoCorrente.getCoordinateBancarie().getIban().getCodice())
                                                    .numeroConto(contoCorrente.getCoordinateBancarie().getNumeroConto().getNumero())
                                                    .saldoContabile(contoCorrente.getSaldoContabile())
                                                    .saldoDisponibile(contoCorrente.getSaldoDisponibile())
                                                    .sogliaBonificiGiornaliera(contoCorrente.getSogliaBonificoGiornaliera())
                                                    .sogliaBonificiMensile(contoCorrente.getSogliaBonificoMensile())
                                                    .build();
        return Response.ok(bodyResponse).build();
    }

    @Override
    public Response recuperaTransazioni(String xAuthenticatedUser, String iban, LocalDate dataCreazioneMin,
            LocalDate dataCreazioneMax, TipologiaFlussoEnum tipologiaFlusso, Double importoMin, Double importoMax,
            Integer numeroPagina, Integer dimensionePagina) {

        log.info("Richiesta recupero trandazioni  : [{}]", xAuthenticatedUser);
                 VORispostaPaginata<VOTransazione> rp = app.recuperaTransazioni(new RecuperaTransazioniCmd(new UsernameCliente(xAuthenticatedUser), new Iban(iban), dataCreazioneMin != null ? dataCreazioneMin.atStartOfDay() : null, dataCreazioneMax != null ? dataCreazioneMax.atTime(LocalTime.MAX): null, importoMin, importoMax, neo.bank.contocorrente.domain.models.events.TipologiaFlusso.valueOf(tipologiaFlusso.name()), numeroPagina, dimensionePagina));
                List<TransazioneResponse> contenuto = rp.getResult().stream().map( t -> {
                    TransazioneResponse transazioneResponse = new TransazioneResponse();

                    return transazioneResponse;
                }).toList();
        return Response.ok(new RispostaPaginataTransazioni(rp.getNumeroPagina(), rp.getDimensionePagina(), rp.getTotaleRisultati(), contenuto)).build();
    }

    
}
