package neo.bank.contocorrente.domain.models.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

@AllArgsConstructor
@Getter
@Setter
public class Operazione<T extends DatiOperazione>{
    
    private IdOperazione idOperazione;
    private LocalDateTime dataOperazione;
    private T datiOperazione;
}
