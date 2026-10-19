package medconnect.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entidade Consulta
 */
public class Consulta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer idPaciente;        // ✅ Certifique-se que existe
    private Integer idMedico;          // ✅ Certifique-se que existe
    private Date dataHora;
    private String motivo;

    public Consulta() {}

    public Consulta(Integer idPaciente, Integer idMedico, Date dataHora, String motivo) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getIdPaciente() { return idPaciente; }  // ✅ ADICIONAR
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }  // ✅ ADICIONAR
    
    public Integer getIdMedico() { return idMedico; }  // ✅ ADICIONAR
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }  // ✅ ADICIONAR
    
    public Date getDataHora() { return dataHora; }
    public void setDataHora(Date dataHora) { this.dataHora = dataHora; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Consulta{" +
               "id=" + id +
               ", idPaciente=" + idPaciente +
               ", idMedico=" + idMedico +
               ", dataHora=" + dataHora +
               ", motivo='" + motivo + '\'' +
               '}';
    }
}