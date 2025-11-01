package neo.bank.contocorrente.application;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.input.commands.AggiornaSaldoCmd;
import neo.bank.contocorrente.application.ports.input.commands.AssociaCartaCmd;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoGiornalieraCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSogliaBonificoMensileCmd;
import neo.bank.contocorrente.application.ports.input.commands.PredisponiBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.RecuperaTransazioniCmd;
import neo.bank.contocorrente.application.ports.input.commands.RipristinaSaldoCmd;
import neo.bank.contocorrente.application.ports.output.ContoCorrenteOutputPort;
import neo.bank.contocorrente.application.ports.output.IbanProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.IdTransazione;
import neo.bank.contocorrente.domain.models.vo.RispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.Transazione;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;
import neo.bank.contocorrente.domain.services.TransazioniService;

@ApplicationScoped
@Slf4j
public class ContoCorrenteUseCase {
    
    @Inject
    private GeneratoreIdService generatoreIdService;
    
    @Inject
    private GeneratoreCoordinateBancarieService generatoreCoordinateBancarie;

    @Inject
    private AnagraficaClienteService anagraficaClienteService;

    @Inject
    private ContoCorrenteOutputPort ccOutputPort;

    @Inject
    private IbanProjectionRepositoryPort ibanProjRepoPort;

    @Inject
    private TransazioniService transazioniService;


    public void creaContoCorrente(CreaContoCorrenteCmd cmd) {
        log.info("Comando [creaContoCorrente] in esecuzione...");
        ContoCorrente cc = ContoCorrente.apri(generatoreIdService, generatoreCoordinateBancarie, anagraficaClienteService, cmd.getUsernameCliente());
        ccOutputPort.salva(cc);
        ibanProjRepoPort.salva(cc.getCoordinateBancarie().iban(), cc.getIdContoCorrente());
        log.info("Comando [creaContoCorrente] terminato...");
    }

    public void associaCarta(AssociaCartaCmd cmd) {
        log.info("Comando [associaCarta] in esecuzione...");
        IdContoCorrente idContoCorrente = ibanProjRepoPort.recuperaDaIban(cmd.getIban());
        ContoCorrente cc = ccOutputPort.recuperaDaId(idContoCorrente);
        cc.associaCarta(cmd.getUsernameCliente(), cmd.getNumeroCarta());
        ccOutputPort.salva(cc);
        ibanProjRepoPort.salva(cc.getCoordinateBancarie().iban(), cc.getIdContoCorrente());
        log.info("Comando [associaCarta] terminato...");
    }


    public ContoCorrente recuperaContoCorrenteDaId(IdContoCorrente idContoCorrente) {
        log.info("Recupero info contoCorrente per id [{}]", idContoCorrente.id());
        ContoCorrente contoCorrente = ccOutputPort.recuperaDaId(idContoCorrente);
        log.info("Recupero terminato");
        return contoCorrente;
    }

    public ContoCorrente recuperaContoCorrenteDaIban(Iban iban) {
        log.info("Recupero info contoCorrente per iban [{}]", iban.codice());
        IdContoCorrente idContoCorrente = ibanProjRepoPort.recuperaDaIban(iban);
        ContoCorrente contoCorrente = ccOutputPort.recuperaDaId(idContoCorrente);
        log.info("Recupero terminato");
        return contoCorrente;
    }

    public void impostaSogliaBonificoGiornaliera(ImpostaSogliaBonificoGiornalieraCmd cmd) {
        log.info("Comando [impostaSogliaBonificoGiornaliera] in esecuzione...");
        IdContoCorrente idContoCorrente = ibanProjRepoPort.recuperaDaIban(cmd.getIban());
        ContoCorrente cc = ccOutputPort.recuperaDaId(idContoCorrente);
        cc.impostaSogliaBonificoGiornaliero(cmd.getUsernameCliente(), cmd.getNuovaSogliaBonifico());
        ccOutputPort.salva(cc);
        log.info("Comando [impostaSogliaBonificoGiornaliera] terminato...");
    }

    public void impostaSogliaBonificoMensile(ImpostaSogliaBonificoMensileCmd cmd) {
        log.info("Comando [impostaSogliaBonificoMensile] in esecuzione...");
        IdContoCorrente idContoCorrente = ibanProjRepoPort.recuperaDaIban(cmd.getIban());
        ContoCorrente cc = ccOutputPort.recuperaDaId(idContoCorrente);
        cc.impostaSogliaBonificoMensile(cmd.getUsernameCliente(), cmd.getNuovaSogliaBonifico());
        ccOutputPort.salva(cc);
        log.info("Comando [impostaSogliaBonificoMensile] terminato...");
    }

    public void predisponiBonifico(PredisponiBonificoCmd cmd) {
        log.info("Comando [predisponiBonifico] in esecuzione...");
        IdContoCorrente idContoCorrente = ibanProjRepoPort.recuperaDaIban(cmd.getIbanMittente());
        ContoCorrente cc = ccOutputPort.recuperaDaId(idContoCorrente);
        cc.predisponiBonifico(cmd.getIbanDestinatario(), cmd.getCausale(), cmd.getImporto(), cmd.getUsernameCliente(), transazioniService);
        ccOutputPort.salva(cc);
        transazioniService.creaTransazione(new Transazione(new IdTransazione(UUID.randomUUID().toString()), idContoCorrente, new IdOperazione(UUID.randomUUID().toString()), cmd.getImporto(), cmd.getIbanDestinatario(), LocalDateTime.now(ZoneOffset.UTC), cmd.getCausale(), TipologiaFlusso.ADDEBITO));
        log.info("Comando [predisponiBonifico] terminato...");
    }

    public void aggiornaSaldo(AggiornaSaldoCmd cmd) {
        log.info("Comando [aggiornaSaldo] in esecuzione...");
        
        IdContoCorrente idCCAccredito = ibanProjRepoPort.recuperaDaIban(cmd.getIbanDestinatario());
        if(idCCAccredito != null) {
            log.info("Iban presente nel sistema bancario. Aggiornamento saldo del destinatario..");
            ContoCorrente ccAccredito = ccOutputPort.recuperaDaId(idCCAccredito);
            ccAccredito.aggiornaSaldoContabile(cmd.getImporto());
            ccAccredito.aggiornaSaldoDisponibile(cmd.getImporto());
            ccOutputPort.salva(ccAccredito);
            transazioniService.creaTransazione(new Transazione(new IdTransazione(UUID.randomUUID().toString()), idCCAccredito, cmd.getIdOperazione(), cmd.getImporto(), ccAccredito.getCoordinateBancarie().iban(), LocalDateTime.now(ZoneOffset.UTC), cmd.getCausale(), TipologiaFlusso.ADDEBITO));
        }
        IdContoCorrente idCCAddebito = ibanProjRepoPort.recuperaDaIban(cmd.getIbanMittente());
        ContoCorrente ccAddebito = ccOutputPort.recuperaDaId(idCCAddebito);
        ccAddebito.aggiornaSaldoContabile((-1) * cmd.getImporto());
        ccOutputPort.salva(ccAddebito);
        log.info("Comando [aggiornaSaldo] terminato...");
    }

    public void ripristinaSaldo(RipristinaSaldoCmd cmd) {
        log.info("Comando [ripristinaSaldo] in esecuzione...");
        IdContoCorrente idCCAddebito = ibanProjRepoPort.recuperaDaIban(cmd.getIbanMittente());
        ContoCorrente ccAddebito = ccOutputPort.recuperaDaId(idCCAddebito);
        transazioniService.cancellaTransazione(cmd.getIdOperazione());
        ccAddebito.aggiornaSaldoDisponibile( cmd.getImporto());
        ccOutputPort.salva(ccAddebito);
        log.info("Comando [ripristinaSaldo] terminato...");
    }


    public RispostaPaginata<Transazione>  recuperaTransazioni(RecuperaTransazioniCmd cmd) {
        log.info("Comando [recuperaTransazioni] in esecuzione... {}", cmd);
        IdContoCorrente idCC = ibanProjRepoPort.recuperaDaIban(cmd.getIbanRichiedente());
        ContoCorrente cc = ccOutputPort.recuperaDaId(idCC);
        cc.verificaAccessoCliente(cmd.getUsernameCliente());
        RispostaPaginata<Transazione> transazioni = transazioniService.recuperaTransazioni(idCC, cmd.getDataInf(), cmd.getDataSup(), cmd.getImportoMin(), cmd.getImportoMax(), cmd.getTipologiaFlusso(), cmd.getNumeroPagina(), cmd.getDimensionePagina());
        log.info("Comando [recuperaTransazioni] terminato...");
        return transazioni;
    }


}