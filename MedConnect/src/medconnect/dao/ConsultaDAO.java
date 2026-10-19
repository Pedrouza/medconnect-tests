package medconnect.dao;

import medconnect.model.Consulta;
import medconnect.repository.Repository;
import medconnect.exception.BancoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso a dados de Consulta
 * Responsabilidade Única: Apenas operações de banco
 */
public class ConsultaDAO implements Repository<Consulta> {
    
    @Override
    public void salvar(Consulta consulta) {
        String sql = "INSERT INTO consultas (id_paciente, id_medico, data_hora, motivo) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setInt(2, consulta.getIdMedico());
            stmt.setTimestamp(3, new Timestamp(consulta.getDataHora().getTime()));
            stmt.setString(4, consulta.getMotivo());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao salvar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consultas SET id_paciente=?, id_medico=?, " +
                     "data_hora=?, motivo=? WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setInt(2, consulta.getIdMedico());
            stmt.setTimestamp(3, new Timestamp(consulta.getDataHora().getTime()));
            stmt.setString(4, consulta.getMotivo());
            stmt.setInt(5, consulta.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM consultas WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao excluir consulta: " + e.getMessage());
        }
    }
    
    @Override
    public Consulta buscarPorId(Integer id) {
        String sql = "SELECT * FROM consultas WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public List<Consulta> listarTodos() {
        String sql = "SELECT * FROM consultas ORDER BY data_hora DESC";
        List<Consulta> consultas = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                consultas.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao listar consultas: " + e.getMessage());
        }
        
        return consultas;
    }
    
    @Override
    public List<Consulta> buscarPorNome(String nome) {
        // Implementação específica para consultas
        return new ArrayList<>();
    }
    
    /**
     * Busca consultas por paciente
     */
    public List<Consulta> buscarPorPaciente(Integer idPaciente) {
        String sql = "SELECT * FROM consultas WHERE id_paciente=? ORDER BY data_hora DESC";
        List<Consulta> consultas = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                consultas.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar consultas do paciente: " + e.getMessage());
        }
        
        return consultas;
    }
    
   
    public List<Consulta> buscarPorMedico(Integer idMedico) {
        String sql = "SELECT * FROM consultas WHERE id_medico=? ORDER BY data_hora DESC";
        List<Consulta> consultas = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                consultas.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar consultas do médico: " + e.getMessage());
        }
        
        return consultas;
    }
    
    /**
     * Busca consultas por data
     */
    public List<Consulta> buscarPorData(java.util.Date data) {
    String sql = "SELECT * FROM consultas WHERE DATE(data_hora) = ? ORDER BY data_hora";
    List<Consulta> consultas = new ArrayList<>();
    
    try (Connection conn = ConexaoDB.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setDate(1, new java.sql.Date(data.getTime()));  // Conversão interna
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            consultas.add(mapearResultSet(rs));
        }
        
    } catch (SQLException e) {
        throw new BancoException("Erro ao buscar consultas por data: " + e.getMessage());
    }
    
    return consultas;
}
    
    private Consulta mapearResultSet(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(rs.getInt("id"));
        consulta.setIdPaciente(rs.getInt("id_paciente"));
        consulta.setIdMedico(rs.getInt("id_medico"));
        consulta.setDataHora(rs.getTimestamp("data_hora"));
        consulta.setMotivo(rs.getString("motivo"));
        return consulta;
    }
}