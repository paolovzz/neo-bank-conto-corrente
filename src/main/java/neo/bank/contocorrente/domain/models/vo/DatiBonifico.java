package neo.bank.contocorrente.domain.models.vo;

import lombok.Value;


@Value
public class DatiBonifico {

    private IdOperazione idOperazione;
    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private double importo;
    private String causale;
}
