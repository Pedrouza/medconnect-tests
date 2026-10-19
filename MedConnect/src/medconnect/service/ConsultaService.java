package medconnect.service;

import medconnect.model.Consulta;
import medconnect.dao.ConsultaDAO;
import medconnect.validator.ConsultaValidator;
import medconnect.exception.NegocioException;
import java.util.List;
import java.util.Date;
import medconnect.util.AgendaRegras;

/**
 * Service para regras de negócio de Consulta
 * Responsabilidade Única: Apenas regras de negócio
 */
public class ConsultaService {
    
    private final ConsultaDAO consultaDAO;
    private final ConsultaValidator validator;
    
    public ConsultaService() {
        this.consultaDAO = new ConsultaDAO();
        this.validator = new ConsultaValidator();
    }
    
    /**
     * Agenda nova consulta
     */
    public void agendar(Consulta consulta) throws NegocioException {
        try {
            validator.validar(consulta);
            
            // Verificar conflito de horário
            if (existeConflitoHorario(consulta)) {
                throw new NegocioException("Horário já está agendado para este médico");
            }
            
            consultaDAO.salvar(consulta);
        } catch (Exception e) {
            throw new NegocioException("Erro ao agendar consulta: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza consulta existente
     */
    public void atualizar(Consulta consulta) throws NegocioException {
        try {
            if (consulta.getId() == null) {
                throw new NegocioException("ID da consulta é obrigatório");
            }
            validator.validar(consulta);
            consultaDAO.atualizar(consulta);
        } catch (Exception e) {
            throw new NegocioException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }
    
    /**
     * Cancela consulta
     */
    public void cancelar(Integer id) throws NegocioException {
        try {
            if (id == null) {
                throw new NegocioException("ID é obrigatório");
            }
            consultaDAO.excluir(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao cancelar consulta: " + e.getMessage());
        }
    }
    
    /**
     * Busca consulta por ID
     */
    public Consulta buscarPorId(Integer id) throws NegocioException {
        try {
            return consultaDAO.buscarPorId(id);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar consulta: " + e.getMessage());
        }
    }
    
    /**
     * Lista todas as consultas
     */
    public List<Consulta> listarTodos() throws NegocioException {
        try {
            return consultaDAO.listarTodos();
        } catch (Exception e) {
            throw new NegocioException("Erro ao listar consultas: " + e.getMessage());
        }
    }
    
    /**
     * Busca consultas por paciente
     */
    public List<Consulta> buscarPorPaciente(Integer idPaciente) throws NegocioException {
        try {
            return consultaDAO.buscarPorPaciente(idPaciente);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar consultas do paciente: " + e.getMessage());
        }
    }
    
    /**
     * Busca consultas por médico
     */
    public List<Consulta> buscarPorMedico(Integer idMedico) throws NegocioException {
        try {
            return consultaDAO.buscarPorMedico(idMedico);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar consultas do médico: " + e.getMessage());
        }
    }
    
    /**
     * Busca consultas por data
     */
   public List<Consulta> buscarPorData(Date data) throws NegocioException {
    try {
        // Converter java.util.Date para java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        return consultaDAO.buscarPorData(sqlDate);
    } catch (Exception e) {
        throw new NegocioException("Erro ao buscar consultas por data: " + e.getMessage());
    }
}
    /**
     * Verifica se existe conflito de horário
     */
    private boolean existeConflitoHorario(Consulta consulta) throws NegocioException {
        List<Consulta> consultasMedico = buscarPorMedico(consulta.getIdMedico());
        return AgendaRegras.existeConflitoHorario(consultasMedico, consulta.getDataHora());
    }
}