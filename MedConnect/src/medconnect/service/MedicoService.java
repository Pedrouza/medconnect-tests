package medconnect.service;

import medconnect.model.Medico;
import medconnect.dao.MedicoDAO;
import medconnect.validator.MedicoValidator;
import medconnect.exception.NegocioException;
import java.util.List;

/**
 * Service para regras de negócio de Médico
 * Responsabilidade Única: Apenas regras de negócio
 */
public class MedicoService {
    
    private final MedicoDAO medicoDAO;
    private final MedicoValidator validator;
    
    public MedicoService() {
        this.medicoDAO = new MedicoDAO();
        this.validator = new MedicoValidator();
    }
    
    /**
     * Cadastra novo médico
     */
    public void cadastrar(Medico medico) throws NegocioException {
        try {
            validator.validar(medico);
            medicoDAO.salvar(medico);
        } catch (Exception e) {
            throw new NegocioException("Erro ao cadastrar médico: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza médico existente
     */
    public void atualizar(Medico medico) throws NegocioException {
        try {
            if (medico.getId() == null) {
                throw new NegocioException("ID do médico é obrigatório para atualização");
            }
            validator.validar(medico);
            medicoDAO.atualizar(medico);
        } catch (Exception e) {
            throw new NegocioException("Erro ao atualizar médico: " + e.getMessage());
        }
    }
    
    /**
     * Exclui médico
     */
    public void excluir(Integer id) throws NegocioException {
        try {
            if (id == null) {
                throw new NegocioException("ID é obrigatório");
            }
            medicoDAO.excluir(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao excluir médico: " + e.getMessage());
        }
    }
    
    /**
     * Busca médico por ID
     */
    public Medico buscarPorId(Integer id) throws NegocioException {
        try {
            return medicoDAO.buscarPorId(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar médico: " + e.getMessage());
        }
    }
    
    /**
     * Lista todos os médicos
     */
    public List<Medico> listarTodos() throws NegocioException {
        try {
            return medicoDAO.listarTodos();
        } catch (Exception e) {
            throw new NegocioException("Erro ao listar médicos: " + e.getMessage());
        }
    }
    
    /**
     * Busca médicos por nome
     */
    public List<Medico> buscarPorNome(String nome) throws NegocioException {
        try {
            return medicoDAO.buscarPorNome(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar médicos: " + e.getMessage());
        }
    }
}