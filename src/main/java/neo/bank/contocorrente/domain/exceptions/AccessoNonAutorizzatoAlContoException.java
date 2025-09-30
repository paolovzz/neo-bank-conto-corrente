package neo.bank.contocorrente.domain.exceptions;

public class AccessoNonAutorizzatoAlContoException extends RuntimeException {


    public AccessoNonAutorizzatoAlContoException(String idCliente) {
        super(String.format("Accesso al conto non autorizzato per il cliente [%s]", idCliente));
    }


    
}
