package neo.bank.contocorrente.framework.adapter.output.rest.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BonificoInfoResponse {
   
    private String idOperazione;
    private String ibanMittente;
    private String ibanDestinatario;
    private double importo;
    private String causale;

}
