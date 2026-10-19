package medconnect.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Prontuário
 */
public class Prontuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer idConsulta;        // ✅ Certifique-se que existe
    private String observacoes;
    private String receita;

    public Prontuario() {}

    public Prontuario(Integer idConsulta, String observacoes, String receita) {
        this.idConsulta = idConsulta;
        this.observacoes = observacoes;
        this.receita = receita;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getIdConsulta() { return idConsulta; }  // ✅ ADICIONAR
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }  // ✅ ADICIONAR
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public String getReceita() { return receita; }
    public void setReceita(String receita) { this.receita = receita; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario prontuario = (Prontuario) o;
        return Objects.equals(id, prontuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prontuario{" +
               "id=" + id +
               ", idConsulta=" + idConsulta +
               ", observacoes='" + observacoes + '\'' +
               ", receita='" + receita + '\'' +
               '}';
    }
}