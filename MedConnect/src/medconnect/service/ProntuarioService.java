package medconnect.service;

import medconnect.model.Prontuario;
import medconnect.dao.ProntuarioDAO;
import medconnect.validator.ProntuarioValidator;
import medconnect.exception.NegocioException;
import java.util.List;

/**
 * Service para regras de negócio de Prontuário
 * Responsabilidade Única: Apenas regras de negócio
 */
public class ProntuarioService {
    
    private final ProntuarioDAO prontuarioDAO;
    private final ProntuarioValidator validator;
    
    public ProntuarioService() {
        this.prontuarioDAO = new ProntuarioDAO();
        this.validator = new ProntuarioValidator();
    }
    
    /**
     * Cria novo prontuário
     */
    public void criar(Prontuario prontuario) throws NegocioException {
        try {
            validator.validar(prontuario);
            prontuarioDAO.salvar(prontuario);
        } catch (Exception e) {
            throw new NegocioException("Erro ao criar prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza prontuário existente
     */
    public void atualizar(Prontuario prontuario) throws NegocioException {
        try {
            if (prontuario.getId() == null) {
                throw new NegocioException("ID do prontuário é obrigatório");
            }
            validator.validar(prontuario);
            prontuarioDAO.atualizar(prontuario);
        } catch (Exception e) {
            throw new NegocioException("Erro ao atualizar prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Exclui prontuário
     */
    public void excluir(Integer id) throws NegocioException {
        try {
            if (id == null) {
                throw new NegocioException("ID é obrigatório");
            }
            prontuarioDAO.excluir(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao excluir prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Busca prontuário por ID
     */
    public Prontuario buscarPorId(Integer id) throws NegocioException {
        try {
            return prontuarioDAO.buscarPorId(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Busca prontuário por consulta
     */
    public Prontuario buscarPorConsulta(Integer idConsulta) throws NegocioException {
        try {
            return prontuarioDAO.buscarPorConsulta(idConsulta);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Lista todos os prontuários
     */
    public List<Prontuario> listarTodos() throws NegocioException {
        try {
            return prontuarioDAO.listarTodos();
        } catch (Exception e) {
            throw new NegocioException("Erro ao listar prontuários: " + e.getMessage());
        }
    }
    
    /**
     * Busca histórico de prontuários por paciente
     */
    public List<Prontuario> buscarHistoricoPaciente(Integer idPaciente) throws NegocioException {
        try {
            return prontuarioDAO.buscarHistoricoPaciente(idPaciente);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar histórico: " + e.getMessage());
        }
    }
}