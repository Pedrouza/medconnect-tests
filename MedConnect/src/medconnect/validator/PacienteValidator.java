package medconnect.validator;

import medconnect.model.Paciente;
import medconnect.exception.ValidacaoException;
import java.util.Date;

public class PacienteValidator implements Validator<Paciente> {
    
    @Override
    public void validar(Paciente paciente) throws ValidacaoException {
        if (paciente == null) {
            throw new ValidacaoException("Paciente não pode ser nulo");
        }
        
        validarNome(paciente.getNome());
        validarCPF(paciente.getCpf());
        validarDataNascimento(paciente.getDataNascimento()); 
        validarEmail(paciente.getEmail());
        validarTelefone(paciente.getTelefone());
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
    
    private void validarCPF(String cpf) throws ValidacaoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidacaoException("CPF é obrigatório");
        }
        
        String cpfLimpo = cpf.replaceAll("\\D", "");
        
        if (cpfLimpo.length() != 11) {
            throw new ValidacaoException("CPF deve ter 11 dígitos");
        }
        
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new ValidacaoException("CPF inválido");
        }
        
        if (!isValidCPF(cpfLimpo)) {
            throw new ValidacaoException("CPF inválido");
        }
    }
    
    private void validarEmail(String email) throws ValidacaoException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidacaoException("E-mail é obrigatório");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidacaoException("E-mail inválido");
        }
    }
    
    private void validarTelefone(String telefone) throws ValidacaoException {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new ValidacaoException("Telefone é obrigatório");
        }
        String telLimpo = telefone.replaceAll("\\D", "");
        if (telLimpo.length() < 10 || telLimpo.length() > 11) {
            throw new ValidacaoException("Telefone inválido");
        }
    }
    
    private boolean isValidCPF(String cpf) {
        try {
            int d1, d2;
            int digito1, digito2, resto;
            int digitoVerificador;
            String nDigResult;
            
            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;
            
            for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
                digitoVerificador = Integer.parseInt(cpf.substring(nCount - 1, nCount));
                d1 += digitoVerificador * (11 - nCount);
                d2 += digitoVerificador * (12 - nCount);
            }
            
            resto = (d1 % 11);
            digito1 = (resto < 2) ? 0 : 11 - resto;
            
            d2 += 2 * digito1;
            resto = (d2 % 11);
            digito2 = (resto < 2) ? 0 : 11 - resto;
            
            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
            return nDigResult.equals(cpf.substring(cpf.length() - 2, cpf.length()));
            
        } catch (Exception e) {
            return false;
        }
    }
    
    private void validarDataNascimento(Date dataNascimento) throws ValidacaoException {
        if (dataNascimento == null) {
            throw new ValidacaoException("Data de nascimento é obrigatória");
        }
        
        // Não permitir data futura
        if (dataNascimento.after(new Date())) {
            throw new ValidacaoException("Data de nascimento não pode ser no futuro");
        }
        
        // Não permitir datas muito antigas (ex: mais de 120 anos)
        long anos = (new Date().getTime() - dataNascimento.getTime()) / (1000L * 60 * 60 * 24 * 365);
        if (anos > 120) {
            throw new ValidacaoException("Data de nascimento inválida (idade superior a 120 anos)");
        }
    }
}