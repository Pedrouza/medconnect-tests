package medconnect.service;

import medconnect.model.Paciente;
import medconnect.dao.PacienteDAO;
import medconnect.validator.PacienteValidator;
import medconnect.exception.ValidacaoException;
import medconnect.exception.BancoException;
import medconnect.exception.NegocioException;

import java.util.List;

/**
 * Service para regras de negócio de Paciente
 */
public class PacienteService {
    
    private final PacienteDAO pacienteDAO;
    private final PacienteValidator validator;
    
    public PacienteService() {
        this.pacienteDAO = new PacienteDAO();
        this.validator = new PacienteValidator();
    }
    
    /**
     * Cadastra novo paciente
     */
    public void cadastrar(Paciente paciente) throws NegocioException {
        try {
            validator.validar(paciente);
            
            List<Paciente> existentes = pacienteDAO.buscarPorNome(paciente.getNome());
            for (Paciente p : existentes) {
                if (p.getCpf().equals(paciente.getCpf())) {
                    throw new ValidacaoException("CPF já cadastrado");
                }
            }
            
            pacienteDAO.salvar(paciente);
            
        } catch (ValidacaoException e) {
            throw new NegocioException("Erro de validação: " + e.getMessage());
        } catch (BancoException e) {
            throw new NegocioException("Erro no banco de dados: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza paciente existente
     */
    public void atualizar(Paciente paciente) throws NegocioException {
        try {
            if (paciente.getId() == null) {
                throw new ValidacaoException("ID do paciente é obrigatório para atualização");
            }
            
            validator.validar(paciente);
            pacienteDAO.atualizar(paciente);
            
        } catch (ValidacaoException e) {
            throw new NegocioException("Erro de validação: " + e.getMessage());
        } catch (BancoException e) {
            throw new NegocioException("Erro no banco de dados: " + e.getMessage());
        }
    }
    
    /**
     * Exclui paciente
     */
    public void excluir(Integer id) throws NegocioException {
        try {
            if (id == null) {
                throw new ValidacaoException("ID é obrigatório");
            }
            pacienteDAO.excluir(id);
        } catch (ValidacaoException e) {
            throw new NegocioException("Erro de validação: " + e.getMessage());
        } catch (BancoException e) {
            throw new NegocioException("Erro ao excluir: " + e.getMessage());
        }
    }
    
    /**
     * Busca paciente por ID
     */
    public Paciente buscarPorId(Integer id) throws NegocioException {
        try {
            return pacienteDAO.buscarPorId(id);
        } catch (BancoException e) {
            throw new NegocioException("Erro ao buscar: " + e.getMessage());
        }
    }
    
    /**
     * Lista todos os pacientes
     */
    public List<Paciente> listarTodos() throws NegocioException {
        try {
            return pacienteDAO.listarTodos();
        } catch (BancoException e) {
            throw new NegocioException("Erro ao listar: " + e.getMessage());
        }
    }
    
    /**
     * Busca pacientes por nome
     */
    public List<Paciente> buscarPorNome(String nome) throws NegocioException {
        try {
            return pacienteDAO.buscarPorNome(nome);
        } catch (BancoException e) {
            throw new NegocioException("Erro ao buscar: " + e.getMessage());
        }
    }
    
    /**
     * Conta total de pacientes
     */
    public int contarTotal() throws NegocioException {
        try {
            return pacienteDAO.contarTotal();
        } catch (BancoException e) {
            throw new NegocioException("Erro ao contar: " + e.getMessage());
        }
    }
}