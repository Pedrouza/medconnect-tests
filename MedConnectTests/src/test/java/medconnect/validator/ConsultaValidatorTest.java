package medconnect.validator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import medconnect.exception.ValidacaoException;
import medconnect.model.Consulta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ConsultaValidator - regras de agendamento")
class ConsultaValidatorTest {

    private ConsultaValidator validator;
    private Consulta consulta;

    @BeforeEach
    void setUp() {
        validator = new ConsultaValidator();
        consulta = new Consulta(1, 2, new Date(System.currentTimeMillis() + 86_400_000L), "Consulta de rotina");
    }

    private String mensagemDoErro() {
        return assertThrows(ValidacaoException.class, () -> validator.validar(consulta)).getMessage();
    }

    @Test
    @DisplayName("Consulta válida não lança exceção")
    void consultaValida() {
        assertDoesNotThrow(() -> validator.validar(consulta));
    }

    @Test
    @DisplayName("Consulta nula é rejeitada")
    void consultaNula() {
        assertThrows(ValidacaoException.class, () -> validator.validar(null));
    }

    @Test
    @DisplayName("Paciente é obrigatório e deve ter ID positivo")
    void paciente() {
        consulta.setIdPaciente(null);
        assertEquals("Paciente é obrigatório", mensagemDoErro());
        consulta.setIdPaciente(0);
        assertEquals("ID do paciente inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("Médico é obrigatório e deve ter ID positivo")
    void medico() {
        consulta.setIdMedico(null);
        assertEquals("Médico é obrigatório", mensagemDoErro());
        consulta.setIdMedico(-1);
        assertEquals("ID do médico inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("Data/hora obrigatória")
    void dataObrigatoria() {
        consulta.setDataHora(null);
        assertEquals("Data e hora são obrigatórias", mensagemDoErro());
    }

    @Test
    @DisplayName("Agendamento no passado é rejeitado")
    void dataNoPassado() {
        consulta.setDataHora(new Date(System.currentTimeMillis() - 60_000L));
        assertEquals("Não é possível agendar consultas no passado", mensagemDoErro());
    }

    @Test
    @DisplayName("Motivo obrigatório")
    void motivoObrigatorio() {
        consulta.setMotivo("  ");
        assertEquals("Motivo da consulta é obrigatório", mensagemDoErro());
    }

    @Test
    @DisplayName("Motivo: 500 caracteres passa, 501 falha (valor limite)")
    void motivoLimite() {
        consulta.setMotivo("x".repeat(500));
        assertDoesNotThrow(() -> validator.validar(consulta));
        consulta.setMotivo("x".repeat(501));
        assertEquals("Motivo deve ter no máximo 500 caracteres", mensagemDoErro());
    }
}
