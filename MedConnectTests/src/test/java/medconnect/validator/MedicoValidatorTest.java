package medconnect.validator;

import static org.junit.jupiter.api.Assertions.*;

import medconnect.exception.ValidacaoException;
import medconnect.model.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("MedicoValidator - regras de cadastro de médico")
class MedicoValidatorTest {

    private MedicoValidator validator;
    private Medico medico;

    @BeforeEach
    void setUp() {
        validator = new MedicoValidator();
        medico = new Medico("Dr. Carlos Souza", "12345", "Cardiologia", "Seg a Sex 08h-17h");
    }

    private String mensagemDoErro() {
        return assertThrows(ValidacaoException.class, () -> validator.validar(medico)).getMessage();
    }

    @Test
    @DisplayName("Médico válido não lança exceção")
    void medicoValido() {
        assertDoesNotThrow(() -> validator.validar(medico));
    }

    @Test
    @DisplayName("Médico nulo é rejeitado")
    void medicoNulo() {
        assertThrows(ValidacaoException.class, () -> validator.validar(null));
    }

    @ParameterizedTest(name = "CRM válido: {0}")
    @ValueSource(strings = {"1234", "1234567", "CRM/SP 123456", "123.456"})
    void crmValido(String crm) {
        medico.setCrm(crm);
        assertDoesNotThrow(() -> validator.validar(medico));
    }

    @ParameterizedTest(name = "CRM com tamanho inválido: {0}")
    @ValueSource(strings = {"123", "12345678", "ABC"})
    void crmTamanhoInvalido(String crm) {
        medico.setCrm(crm);
        assertEquals("CRM deve ter entre 4 e 7 dígitos", mensagemDoErro());
    }

    @Test
    @DisplayName("CRM obrigatório")
    void crmObrigatorio() {
        medico.setCrm("");
        assertEquals("CRM é obrigatório", mensagemDoErro());
    }

    @Test
    @DisplayName("Nome obrigatório e com tamanho entre 3 e 100")
    void nome() {
        medico.setNome(null);
        assertEquals("Nome é obrigatório", mensagemDoErro());
        medico.setNome("Al");
        assertEquals("Nome deve ter no mínimo 3 caracteres", mensagemDoErro());
        medico.setNome("A".repeat(101));
        assertEquals("Nome deve ter no máximo 100 caracteres", mensagemDoErro());
    }

    @Test
    @DisplayName("Especialidade obrigatória")
    void especialidadeObrigatoria() {
        medico.setEspecialidade(" ");
        assertEquals("Especialidade é obrigatória", mensagemDoErro());
    }

    @Test
    @DisplayName("Horário de atendimento obrigatório")
    void horarioObrigatorio() {
        medico.setHorarioAtendimento(null);
        assertEquals("Horário de atendimento é obrigatório", mensagemDoErro());
    }
}
