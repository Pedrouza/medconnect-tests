package medconnect.exception;

/**
 * Exceção para erros de validação
 */
public class ValidacaoException extends Exception {
    
    public ValidacaoException(String message) {
        super(message);
    }
}