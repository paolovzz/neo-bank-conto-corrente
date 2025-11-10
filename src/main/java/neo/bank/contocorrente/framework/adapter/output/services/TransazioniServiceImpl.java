package neo.bank.contocorrente.framework.adapter.output.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.output.TransazioneProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.RispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.Transazione;
import neo.bank.contocorrente.domain.services.TransazioniService;

@ApplicationScoped
@Slf4j
public class TransazioniServiceImpl implements TransazioniService{
    

    @Inject
    private TransazioneProjectionRepositoryPort transazioniPrjRepo;

    @Override
    public double richiediTotaleBonificiUscita(Iban iban, LocalDate dataInf, LocalDate dataSup) {
        log.info("Recupero totale bonifici in uscita per l'iban [{}] nel periodo tra [{}] e [{}]", iban.getCodice(), dataInf, dataSup);
        double totale = transazioniPrjRepo.calcolaTotaleBonificiUscita(iban, dataInf.atStartOfDay(ZoneOffset.UTC).toLocalDateTime(), dataSup.atTime(LocalTime.MAX));
        log.info("Totale bonifici recuperato: [{}]", totale);
        return totale;
    }


    public void creaTransazione(Transazione transazione) {
        log.info("Creo transazione: {}", transazione);
        transazioniPrjRepo.salva(transazione);
    }

    @Override
    public void cancellaTransazione(IdOperazione idOperazione) {
        transazioniPrjRepo.cancellaDaIdOperazione(idOperazione); 
    }


    @Override
    public RispostaPaginata<Transazione> recuperaTransazioni(IdContoCorrente idCC, LocalDateTime dataInf, LocalDateTime dataSup,
            Double importoMin, Double importoMax, TipologiaFlusso tipologiaFlusso, int numeroPagina,
            int dimensionePagina) {

        return transazioniPrjRepo.recuperaTransazioni(idCC, dataInf, dataSup, importoMin, importoMax, tipologiaFlusso, numeroPagina, dimensionePagina);
    }
}
