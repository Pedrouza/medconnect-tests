package medconnect.dao;

import medconnect.model.Prontuario;
import medconnect.repository.Repository;
import medconnect.exception.BancoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso a dados de Prontuário
 * Responsabilidade Única: Apenas operações de banco
 */
public class ProntuarioDAO implements Repository<Prontuario> {
    
    @Override
    public void salvar(Prontuario prontuario) {
        String sql = "INSERT INTO prontuarios (id_consulta, observacoes, receita) " +
                     "VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, prontuario.getIdConsulta());
            stmt.setString(2, prontuario.getObservacoes());
            stmt.setString(3, prontuario.getReceita());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao salvar prontuário: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizar(Prontuario prontuario) {
        String sql = "UPDATE prontuarios SET observacoes=?, receita=? WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, prontuario.getObservacoes());
            stmt.setString(2, prontuario.getReceita());
            stmt.setInt(3, prontuario.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao atualizar prontuário: " + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM prontuarios WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao excluir prontuário: " + e.getMessage());
        }
    }
    
    @Override
    public Prontuario buscarPorId(Integer id) {
        String sql = "SELECT * FROM prontuarios WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar prontuário: " + e.getMessage());
        }
    }
    
    @Override
    public List<Prontuario> listarTodos() {
        String sql = "SELECT * FROM prontuarios ORDER BY id DESC";
        List<Prontuario> prontuarios = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                prontuarios.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao listar prontuários: " + e.getMessage());
        }
        
        return prontuarios;
    }
    
    @Override
    public List<Prontuario> buscarPorNome(String nome) {
        // Não aplicável para prontuários
        return new ArrayList<>();
    }
    
    /**
     * Busca prontuário por consulta
     */
    public Prontuario buscarPorConsulta(Integer idConsulta) {
        String sql = "SELECT * FROM prontuarios WHERE id_consulta=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idConsulta);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar prontuário por consulta: " + e.getMessage());
        }
    }
    
    /**
     * Busca histórico de prontuários por paciente
     */
    public List<Prontuario> buscarHistoricoPaciente(Integer idPaciente) {
        String sql = "SELECT p.* FROM prontuarios p " +
                     "INNER JOIN consultas c ON p.id_consulta = c.id " +
                     "WHERE c.id_paciente = ? ORDER BY c.data_hora DESC";
        List<Prontuario> prontuarios = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                prontuarios.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar histórico do paciente: " + e.getMessage());
        }
        
        return prontuarios;
    }
    
    private Prontuario mapearResultSet(ResultSet rs) throws SQLException {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(rs.getInt("id"));
        prontuario.setIdConsulta(rs.getInt("id_consulta"));
        prontuario.setObservacoes(rs.getString("observacoes"));
        prontuario.setReceita(rs.getString("receita"));
        return prontuario;
    }
}