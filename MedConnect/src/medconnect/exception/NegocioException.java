package medconnect.exception;

/**
 * Exceção para erros de regra de negócio
 */
public class NegocioException extends Exception {
    
    public NegocioException(String message) {
        super(message);
    }
}