package medconnect.validator;

import medconnect.exception.ValidacaoException;

/**
 * Interface genérica para validadores
 */
public interface Validator<T> {
    
    void validar(T objeto) throws ValidacaoException;
}