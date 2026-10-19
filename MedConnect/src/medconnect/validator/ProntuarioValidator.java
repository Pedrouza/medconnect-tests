package medconnect.validator;

import medconnect.model.Prontuario;
import medconnect.exception.ValidacaoException;

/**
 * Validador de Prontuário
 * Responsabilidade Única: Apenas validações de prontuário
 */
public class ProntuarioValidator implements Validator<Prontuario> {
    
    @Override
    public void validar(Prontuario prontuario) throws ValidacaoException {
        if (prontuario == null) {
            throw new ValidacaoException("Prontuário não pode ser nulo");
        }
        
        validarConsulta(prontuario.getIdConsulta());
        validarObservacoes(prontuario.getObservacoes());
    }
    
    private void validarConsulta(Integer idConsulta) throws ValidacaoException {
        if (idConsulta == null) {
            throw new ValidacaoException("Consulta é obrigatória");
        }
        if (idConsulta <= 0) {
            throw new ValidacaoException("ID da consulta inválido");
        }
    }
    
    private void validarObservacoes(String observacoes) throws ValidacaoException {
        if (observacoes == null || observacoes.trim().isEmpty()) {
            throw new ValidacaoException("Observações são obrigatórias");
        }
        if (observacoes.length() > 5000) {
            throw new ValidacaoException("Observações devem ter no máximo 5000 caracteres");
        }
    }
}