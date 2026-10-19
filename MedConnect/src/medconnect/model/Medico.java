package medconnect.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Medico - Representa um médico no sistema
 */
public class Medico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private String crm;
    private String especialidade;
    private String horarioAtendimento;

    public Medico() {}

    public Medico(String nome, String crm, String especialidade, String horarioAtendimento) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.horarioAtendimento = horarioAtendimento;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    
    public String getHorarioAtendimento() { return horarioAtendimento; }
    public void setHorarioAtendimento(String horarioAtendimento) { this.horarioAtendimento = horarioAtendimento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(id, medico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Medico{" + "id=" + id + ", nome='" + nome + "', crm='" + crm + '\'' + '}';
    }
}