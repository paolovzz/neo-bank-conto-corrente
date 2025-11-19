package neo.bank.contocorrente.domain.models.vo;

import java.util.List;

import lombok.Value;

@Value
public class VORispostaPaginata<T> {
    
    private List<T> result;
    private int numeroPagina;
    private int dimensionePagina;
    private long totaleRisultati;
}
