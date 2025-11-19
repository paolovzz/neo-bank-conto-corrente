package neo.bank.contocorrente.domain.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.VORispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.VOTransazione;

public interface TransazioniService {
    
    public double richiediTotaleBonificiUscita(Iban iban, LocalDate dataInf, LocalDate dataSup);
    public void creaTransazione(VOTransazione transazione);
    void cancellaTransazione(IdOperazione idOperazione);
   public VORispostaPaginata<VOTransazione> recuperaTransazioni(IdContoCorrente idCC, LocalDateTime dataInf, LocalDateTime dataSup,
            Double importoMin, Double importoMax, TipologiaFlusso tipologiaFlusso, int numeroPagina,
            int dimensionePagina);
}
