package medconnect.dao;

import medconnect.model.Paciente;
import medconnect.repository.Repository;
import medconnect.exception.BancoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso a dados de Paciente
 * Responsabilidade Única: Apenas operações de banco
 */
public class PacienteDAO implements Repository<Paciente> {
    
    @Override
    public void salvar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nome, cpf, data_nascimento, " +
                     "telefone, email, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            
            // ✅ TRATAMENTO PARA DATA NULA
            if (paciente.getDataNascimento() != null) {
                stmt.setDate(3, new java.sql.Date(paciente.getDataNascimento().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getEndereco());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao salvar paciente: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizar(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome=?, cpf=?, data_nascimento=?, " +
                     "telefone=?, email=?, endereco=? WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            
            // ✅ TRATAMENTO PARA DATA NULA (IGUAL AO SALVAR)
            if (paciente.getDataNascimento() != null) {
                stmt.setDate(3, new java.sql.Date(paciente.getDataNascimento().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getEndereco());
            stmt.setInt(7, paciente.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao atualizar paciente: " + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM pacientes WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao excluir paciente: " + e.getMessage());
        }
    }
    
    @Override
    public Paciente buscarPorId(Integer id) {
        String sql = "SELECT * FROM pacientes WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar paciente: " + e.getMessage());
        }
    }
    
    @Override
    public List<Paciente> listarTodos() {
        String sql = "SELECT * FROM pacientes ORDER BY nome";
        List<Paciente> pacientes = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pacientes.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao listar pacientes: " + e.getMessage());
        }
        
        return pacientes;
    }
    
    @Override
    public List<Paciente> buscarPorNome(String nome) {
        String sql = "SELECT * FROM pacientes WHERE nome LIKE ? ORDER BY nome";
        List<Paciente> pacientes = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                pacientes.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar pacientes por nome: " + e.getMessage());
        }
        
        return pacientes;
    }
    
    public int contarTotal() {
        String sql = "SELECT COUNT(*) as total FROM pacientes";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao contar pacientes: " + e.getMessage());
        }
    }
    
    private Paciente mapearResultSet(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNome(rs.getString("nome"));
        paciente.setCpf(rs.getString("cpf"));
        paciente.setDataNascimento(rs.getDate("data_nascimento"));
        paciente.setTelefone(rs.getString("telefone"));
        paciente.setEmail(rs.getString("email"));
        paciente.setEndereco(rs.getString("endereco"));
        return paciente;
    }
}