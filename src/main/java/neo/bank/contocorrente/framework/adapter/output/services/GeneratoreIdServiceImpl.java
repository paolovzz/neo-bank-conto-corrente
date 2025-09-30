package neo.bank.contocorrente.framework.adapter.output.services;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.services.GeneratoreIdService;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GeneratoreIdServiceImpl  implements GeneratoreIdService{@Override
    public IdContoCorrente genera() {
        return new IdContoCorrente(UUID.randomUUID().toString());
    }


    
}
