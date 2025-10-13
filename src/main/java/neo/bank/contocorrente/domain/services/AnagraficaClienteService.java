package neo.bank.contocorrente.domain.services;

import neo.bank.contocorrente.domain.models.vo.UsernameCliente;

public interface AnagraficaClienteService {
    
    boolean richiediVerificaCliente(UsernameCliente idCliente);
}
