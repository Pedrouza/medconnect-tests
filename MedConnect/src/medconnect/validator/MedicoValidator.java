package medconnect.validator;

import medconnect.model.Medico;
import medconnect.exception.ValidacaoException;

/**
 * Validador de Médico
 * Responsabilidade Única: Apenas validações de médico
 */
public class MedicoValidator implements Validator<Medico> {
    
    @Override
    public void validar(Medico medico) throws ValidacaoException {
        if (medico == null) {
            throw new ValidacaoException("Médico não pode ser nulo");
        }
        
        validarNome(medico.getNome());
        validarCRM(medico.getCrm());
        validarEspecialidade(medico.getEspecialidade());
        validarHorarioAtendimento(medico.getHorarioAtendimento());
    }
    
    private void validarNome(String nome) throws ValidacaoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("Nome é obrigatório");
        }
        if (nome.length() < 3) {
            throw new ValidacaoException("Nome deve ter no mínimo 3 caracteres");
        }
        if (nome.length() > 100) {
            throw new ValidacaoException("Nome deve ter no máximo 100 caracteres");
        }
    }
    
    private void validarCRM(String crm) throws ValidacaoException {
        if (crm == null || crm.trim().isEmpty()) {
            throw new ValidacaoException("CRM é obrigatório");
        }
        
        // Remover formatação
        String crmLimpo = crm.replaceAll("\\D", "");
        
        if (crmLimpo.length() < 4 || crmLimpo.length() > 7) {
            throw new ValidacaoException("CRM deve ter entre 4 e 7 dígitos");
        }
        
        // Verificar se contém apenas números após remover letras
        if (!crmLimpo.matches("\\d+")) {
            throw new ValidacaoException("CRM deve conter apenas números");
        }
    }
    
    private void validarEspecialidade(String especialidade) throws ValidacaoException {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new ValidacaoException("Especialidade é obrigatória");
        }
        if (especialidade.length() > 100) {
            throw new ValidacaoException("Especialidade deve ter no máximo 100 caracteres");
        }
    }
    
    private void validarHorarioAtendimento(String horario) throws ValidacaoException {
        if (horario == null || horario.trim().isEmpty()) {
            throw new ValidacaoException("Horário de atendimento é obrigatório");
        }
        if (horario.length() > 100) {
            throw new ValidacaoException("Horário deve ter no máximo 100 caracteres");
        }
    }
}