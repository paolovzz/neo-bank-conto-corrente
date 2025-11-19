package neo.bank.contocorrente.application.ports.output;


import java.time.LocalDateTime;

import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.VORispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.VOTransazione;

public interface TransazioneProjectionRepositoryPort {
    
    public void salva(VOTransazione transazione);
    public VOTransazione recuperaDaIdOperazione(IdOperazione idOperazione);
    public void cancella(VOTransazione transazione);
    public double calcolaTotaleBonificiUscita(IdContoCorrente idContoCorrente, LocalDateTime dataInf, LocalDateTime dataSup);
    public VORispostaPaginata<VOTransazione> recuperaTransazioni(IdContoCorrente idCC, LocalDateTime dataInf, LocalDateTime dataSup, Double importoMin, Double importoMax, TipologiaFlusso tipologiaFlusso, int numeroPagina, int dimensionePagina);
    void cancellaDaIdOperazione(IdOperazione idOperazione);
}
