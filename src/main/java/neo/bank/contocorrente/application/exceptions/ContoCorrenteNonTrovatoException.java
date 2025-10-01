package neo.bank.contocorrente.application.exceptions;

public class ContoCorrenteNonTrovatoException extends RuntimeException {
    
    public ContoCorrenteNonTrovatoException(String idContoCorrente) {
        super(String.format("ContoCorrente con id [%s] non trovato...", idContoCorrente));
    }
}
