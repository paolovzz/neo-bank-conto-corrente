package neo.bank.contocorrente.domain.models.vo;

import lombok.Value;

@Value
public class DettaglioOperazione{
   
    private Transazione transazioneAccredito;
    private Transazione transazioneAddebito;

}
