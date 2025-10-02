package neo.bank.contocorrente.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.input.commands.CreaContoCorrenteCmd;
import neo.bank.contocorrente.application.ports.input.commands.ImpostaSoglieBonificoCmd;
import neo.bank.contocorrente.application.ports.output.ContoCorrenteOutputPort;
import neo.bank.contocorrente.application.ports.output.ErrorEventsPublisherPort;
import neo.bank.contocorrente.application.ports.output.IbanProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.aggregates.ContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.services.AnagraficaClienteService;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;

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
    // public void chiudiContoCorrente(ChiudiContoCorrenteCmd cmd) {
    //     log.info("Comando [chiudiContoCorrente] in esecuzione...");
    //     ContoCorrente cc = ccOutputPort.recuperaDaId(cmd.getIdContoCorrente());
    //     cc.chiudi(cmd.getIdContoCorrente());
    //     ccOutputPort.salva(cc);
    //     log.info("Comando [chiudiContoCorrente] terminato...");
    // }

    // public void validaRichiestaCointestazione(ValidaRichiestaCointestazioneCmd cmd) {
    //     log.info("Comando [validaRichiestaCointestazione] in esecuzione...");
    //     ContoCorrente cc = ccOutputPort.recuperaDaId(cmd.getIdContoCorrente());
    //     cc.validaRichiestaCointestazione(cmd.getIdContoCorrenteRichiedente(), cmd.getNuovoIdContoCorrente());
    //     ccOutputPort.salva(cc);
    //     log.info("Comando [validaRichiestaCointestazione] terminato...");
    // }

    // public void valutaCointestazione(ValutaCointestazioneCmd cmd) {
    //     log.info("Comando [valutaCointestazione] in esecuzione...");
    //     ContoCorrente cc = ccOutputPort.recuperaDaId(cmd.getIdContoCorrente());
    //     cc.valutaCointestazione(cmd.getIdContoCorrente(), cmd.isConferma());
    //     ccOutputPort.salva(cc);
    //     log.info("Comando [valutaCointestazione] terminato...");
    // }


}