package neo.bank.contocorrente.domain.services;

import neo.bank.contocorrente.domain.models.vo.IdCliente;

public interface AnagraficaClienteService {
    
    boolean richiediVerificaCliente(IdCliente idCliente);
}
