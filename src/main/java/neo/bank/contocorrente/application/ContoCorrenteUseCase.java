package neo.bank.contocorrente.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.input.commands.AggiornaSaldoCmd;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSoglieBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.InviaBonificoCmd;
import neo.bank.contocorrente.application.ports.input.commands.RipristinaSaldoCmd;
import neo.bank.contocorrente.application.ports.output.ContoCorrenteOutputPort;
import neo.bank.contocorrente.application.ports.output.IbanProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.DettaglioOperazione;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;
import neo.bank.contocorrente.domain.services.OperazioniService;
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

    @Inject
    private OperazioniService operazioniService;


    public void creaContoCorrente(CreaContoCorrenteCmd cmd) {
        log.info("Comando [creaContoCorrente] in esecuzione...");
        ContoCorrente cc = ContoCorrente.apri(generatoreIdService, generatoreCoordinateBancarie, anagraficaClienteService, cmd.getIdCliente());
        ccOutputPort.salva(cc);
        ibanProjRepoPort.salva(cc.getCoordinateBancarie().iban(), cc.getIdContoCorrente());
        log.info("Comando [creaContoCorrente] terminato...");
    }

    public ContoCorrente recuperaContoCorrenteDaId(IdContoCorrente idContoCorrente) {
        log.info("Recupero info contoCorrente per id [{}]", idContoCorrente.id());
        ContoCorrente contoCorrente = ccOutputPort.recuperaDaId(idContoCorrente);
        log.info("Recupero terminato");
        return contoCorrente;
    }

    public void impostaSoglieBonifico(ImpostaSoglieBonificoCmd cmd) {
        log.info("Comando [impostaSoglieBonifico] in esecuzione...");
        ContoCorrente cc = ccOutputPort.recuperaDaId(cmd.getIdContoCorrente());
        cc.impostaSoglieBonifico(cmd.getIdCliente(), cmd.getNuovSoglieBonifico());
        ccOutputPort.salva(cc);
        log.info("Comando [impostaSoglieBonifico] terminato...");
    }

    public void inviaBonifico(InviaBonificoCmd cmd) {
        log.info("Comando [inviaBonifico] in esecuzione...");


        ContoCorrente cc = ccOutputPort.recuperaDaId(cmd.getIdContoCorrente());
        cc.predisponiBonifico(cmd.getIbanDestinatario(), cmd.getCausale(), cmd.getImporto(), cmd.getIdCliente(), transazioniService);
        ccOutputPort.salva(cc);
        log.info("Comando [inviaBonifico] terminato...");
    }

    public void aggiornaSaldo(AggiornaSaldoCmd cmd) {
        log.info("Comando [aggiornaSaldo] in esecuzione...");
        DettaglioOperazione dettaglioOperazione = operazioniService.recuperaDettaglioOperazione(cmd.getIdOperazione());
        
        IdContoCorrente idCCAccredito = ibanProjRepoPort.recuperaDaIban(new Iban(dettaglioOperazione.getTransazioneAccredito().getIban()));
        if(idCCAccredito != null) {
            log.info("Iban presente nel sistema bancario. Aggiornamento saldo..");
            ContoCorrente ccAccredito = ccOutputPort.recuperaDaId(idCCAccredito);
            ccAccredito.aggiornaSaldoContabile(dettaglioOperazione.getTransazioneAccredito().getImporto());
            ccAccredito.aggiornaSaldoDisponibile(dettaglioOperazione.getTransazioneAccredito().getImporto());
            ccOutputPort.salva(ccAccredito);
        }
        IdContoCorrente idCCAddebito = ibanProjRepoPort.recuperaDaIban(new Iban(dettaglioOperazione.getTransazioneAddebito().getIban()));
        ContoCorrente ccAddebito = ccOutputPort.recuperaDaId(idCCAddebito);
        ccAddebito.aggiornaSaldoContabile((-1) * dettaglioOperazione.getTransazioneAddebito().getImporto());
        ccOutputPort.salva(ccAddebito);
        log.info("Comando [aggiornaSaldo] terminato...");
    }

    public void ripristinaSaldo(RipristinaSaldoCmd cmd) {
        log.info("Comando [ripristinaSaldo] in esecuzione...");
        DettaglioOperazione dettaglioOperazione = operazioniService.recuperaDettaglioOperazione(cmd.getIdOperazione());
        IdContoCorrente idCCAddebito = ibanProjRepoPort.recuperaDaIban(new Iban(dettaglioOperazione.getTransazioneAddebito().getIban()));
        ContoCorrente ccAddebito = ccOutputPort.recuperaDaId(idCCAddebito);
        ccAddebito.aggiornaSaldoDisponibile( dettaglioOperazione.getTransazioneAddebito().getImporto());
        ccOutputPort.salva(ccAddebito);
        log.info("Comando [ripristinaSaldo] terminato...");
    }


}