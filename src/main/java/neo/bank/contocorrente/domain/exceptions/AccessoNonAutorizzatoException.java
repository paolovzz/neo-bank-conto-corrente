package neo.bank.contocorrente.domain.exceptions;

public class AccessoNonAutorizzatoException extends RuntimeException {


    public AccessoNonAutorizzatoException(String username) {
        super(String.format("Accesso non autorizzato per il cliente [%s]", username));
    }


    
}
