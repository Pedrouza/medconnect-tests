package medconnect.dao;

import medconnect.model.Medico;
import medconnect.repository.Repository;
import medconnect.exception.BancoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso a dados de Médico
 * Responsabilidade Única: Apenas operações de banco
 */
public class MedicoDAO implements Repository<Medico> {
    
    @Override
    public void salvar(Medico medico) {
        String sql = "INSERT INTO medicos (nome, crm, especialidade, horario_atendimento) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getHorarioAtendimento());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao salvar médico: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizar(Medico medico) {
        String sql = "UPDATE medicos SET nome=?, crm=?, especialidade=?, " +
                     "horario_atendimento=? WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getHorarioAtendimento());
            stmt.setInt(5, medico.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao atualizar médico: " + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM medicos WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao excluir médico: " + e.getMessage());
        }
    }
    
    @Override
    public Medico buscarPorId(Integer id) {
        String sql = "SELECT * FROM medicos WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar médico: " + e.getMessage());
        }
    }
    
    @Override
    public List<Medico> listarTodos() {
        String sql = "SELECT * FROM medicos ORDER BY nome";
        List<Medico> medicos = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                medicos.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao listar médicos: " + e.getMessage());
        }
        
        return medicos;
    }
    
    @Override
    public List<Medico> buscarPorNome(String nome) {
        String sql = "SELECT * FROM medicos WHERE nome LIKE ? ORDER BY nome";
        List<Medico> medicos = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                medicos.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar médicos por nome: " + e.getMessage());
        }
        
        return medicos;
    }
    
    public int contarTotal() {
    String sql = "SELECT COUNT(*) as total FROM medicos";
    
    try (Connection conn = ConexaoDB.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
        
    } catch (SQLException e) {
        throw new BancoException("Erro ao contar médicos: " + e.getMessage());
    }
}
    
    private Medico mapearResultSet(ResultSet rs) throws SQLException {
        Medico medico = new Medico();
        medico.setId(rs.getInt("id"));
        medico.setNome(rs.getString("nome"));
        medico.setCrm(rs.getString("crm"));
        medico.setEspecialidade(rs.getString("especialidade"));
        medico.setHorarioAtendimento(rs.getString("horario_atendimento"));
        return medico;
    }
}