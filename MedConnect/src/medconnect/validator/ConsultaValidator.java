package medconnect.validator;

import medconnect.model.Consulta;
import medconnect.exception.ValidacaoException;
import java.util.Date;

/**
 * Validador de Consulta
 * Responsabilidade Única: Apenas validações de consulta
 */
public class ConsultaValidator implements Validator<Consulta> {
    
    @Override
    public void validar(Consulta consulta) throws ValidacaoException {
        if (consulta == null) {
            throw new ValidacaoException("Consulta não pode ser nula");
        }
        
        validarPaciente(consulta.getIdPaciente());
        validarMedico(consulta.getIdMedico());
        validarDataHora(consulta.getDataHora());
        validarMotivo(consulta.getMotivo());
    }
    
    private void validarPaciente(Integer idPaciente) throws ValidacaoException {
        if (idPaciente == null) {
            throw new ValidacaoException("Paciente é obrigatório");
        }
        if (idPaciente <= 0) {
            throw new ValidacaoException("ID do paciente inválido");
        }
    }
    
    private void validarMedico(Integer idMedico) throws ValidacaoException {
        if (idMedico == null) {
            throw new ValidacaoException("Médico é obrigatório");
        }
        if (idMedico <= 0) {
            throw new ValidacaoException("ID do médico inválido");
        }
    }
    
    private void validarDataHora(Date dataHora) throws ValidacaoException {
        if (dataHora == null) {
            throw new ValidacaoException("Data e hora são obrigatórias");
        }
        
        // Não permitir agendamento no passado
        if (dataHora.before(new Date())) {
            throw new ValidacaoException("Não é possível agendar consultas no passado");
        }
    }
    
    private void validarMotivo(String motivo) throws ValidacaoException {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new ValidacaoException("Motivo da consulta é obrigatório");
        }
        if (motivo.length() > 500) {
            throw new ValidacaoException("Motivo deve ter no máximo 500 caracteres");
        }
    }
}