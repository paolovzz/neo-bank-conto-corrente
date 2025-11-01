package neo.bank.contocorrente.application.ports.input.commands;

import java.time.LocalDateTime;

import lombok.Value;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

@Value
public class RecuperaTransazioniCmd {

    private UsernameCliente usernameCliente;
    private Iban ibanRichiedente;
    private LocalDateTime dataInf;
    private LocalDateTime dataSup;
    private Double importoMin;
    private Double importoMax;
    private TipologiaFlusso tipologiaFlusso;
    private int numeroPagina;
    private int dimensionePagina;
}
