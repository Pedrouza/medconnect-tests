package medconnect.validator;

import static org.junit.jupiter.api.Assertions.*;

import medconnect.exception.ValidacaoException;
import medconnect.model.Prontuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProntuarioValidator - regras de prontuário")
class ProntuarioValidatorTest {

    private ProntuarioValidator validator;
    private Prontuario prontuario;

    @BeforeEach
    void setUp() {
        validator = new ProntuarioValidator();
        prontuario = new Prontuario(1, "Paciente com febre há 2 dias.", "Dipirona 500mg");
    }

    private String mensagemDoErro() {
        return assertThrows(ValidacaoException.class, () -> validator.validar(prontuario)).getMessage();
    }

    @Test
    @DisplayName("Prontuário válido não lança exceção")
    void valido() {
        assertDoesNotThrow(() -> validator.validar(prontuario));
    }

    @Test
    @DisplayName("Receita é opcional")
    void receitaOpcional() {
        prontuario.setReceita(null);
        assertDoesNotThrow(() -> validator.validar(prontuario));
    }

    @Test
    @DisplayName("Consulta associada é obrigatória e com ID positivo")
    void consulta() {
        prontuario.setIdConsulta(null);
        assertEquals("Consulta é obrigatória", mensagemDoErro());
        prontuario.setIdConsulta(0);
        assertEquals("ID da consulta inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("Observações obrigatórias, limite de 5000 caracteres")
    void observacoes() {
        prontuario.setObservacoes("");
        assertEquals("Observações são obrigatórias", mensagemDoErro());
        prontuario.setObservacoes("o".repeat(5000));
        assertDoesNotThrow(() -> validator.validar(prontuario));
        prontuario.setObservacoes("o".repeat(5001));
        assertEquals("Observações devem ter no máximo 5000 caracteres", mensagemDoErro());
    }
}
