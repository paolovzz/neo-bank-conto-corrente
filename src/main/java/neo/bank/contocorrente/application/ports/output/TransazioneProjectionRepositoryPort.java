package neo.bank.contocorrente.application.ports.output;


import java.time.LocalDateTime;

import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.RispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.Transazione;

public interface TransazioneProjectionRepositoryPort {
    
    public void salva(Transazione transazione);
    public Transazione recuperaDaIdOperazione(IdOperazione idOperazione);
    public void cancella(Transazione transazione);
    public double calcolaTotaleBonificiUscita(Iban iban, LocalDateTime dataInf, LocalDateTime dataSup);
    public RispostaPaginata<Transazione> recuperaTransazioni(IdContoCorrente idCC, LocalDateTime dataInf, LocalDateTime dataSup, Double importoMin, Double importoMax, TipologiaFlusso tipologiaFlusso, int numeroPagina, int dimensionePagina);
}
