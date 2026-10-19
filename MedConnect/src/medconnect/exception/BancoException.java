package medconnect.exception;

/**
 * Exceção para erros de banco de dados
 */
public class BancoException extends RuntimeException {
    
    public BancoException(String message) {
        super(message);
    }
}