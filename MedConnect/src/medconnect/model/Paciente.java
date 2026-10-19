package medconnect.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import medconnect.util.IdadeUtil;

/**
 * Entidade Paciente - Representa um paciente no sistema
 */
public class Paciente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String endereco;

    public Paciente() {}

    public Paciente(String nome, String cpf, Date dataNascimento, 
                   String telefone, String email, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { 
        this.dataNascimento = dataNascimento; 
    }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Integer calcularIdade() {
        if (dataNascimento == null) return null;
        java.time.LocalDate nasc = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
        return IdadeUtil.calcularIdade(nasc, java.time.LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nome='" + nome + '\'' + '}';
    }
}