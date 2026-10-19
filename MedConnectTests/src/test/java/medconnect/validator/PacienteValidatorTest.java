package medconnect.validator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import medconnect.exception.ValidacaoException;
import medconnect.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("PacienteValidator - regras de cadastro de paciente")
class PacienteValidatorTest {

    private static final long UM_ANO = 1000L * 60 * 60 * 24 * 365;

    private PacienteValidator validator;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        validator = new PacienteValidator();
        paciente = new Paciente("Maria da Silva", "529.982.247-25",
                new Date(System.currentTimeMillis() - 30 * UM_ANO),
                "(13) 99999-8888", "maria@email.com", "Rua A, 10");
    }

    private String mensagemDoErro() {
        return assertThrows(ValidacaoException.class, () -> validator.validar(paciente)).getMessage();
    }

    @Test
    @DisplayName("Paciente válido não lança exceção")
    void pacienteValido() {
        assertDoesNotThrow(() -> validator.validar(paciente));
    }

    @Test
    @DisplayName("Paciente nulo é rejeitado")
    void pacienteNulo() {
        assertThrows(ValidacaoException.class, () -> validator.validar(null));
    }

    @Test
    @DisplayName("CPF sem formatação também é aceito")
    void cpfSemFormatacao() {
        paciente.setCpf("11144477735");
        assertDoesNotThrow(() -> validator.validar(paciente));
    }

    @Test
    @DisplayName("Nome vazio ou nulo é rejeitado")
    void nomeObrigatorio() {
        paciente.setNome("   ");
        assertEquals("Nome é obrigatório", mensagemDoErro());
        paciente.setNome(null);
        assertEquals("Nome é obrigatório", mensagemDoErro());
    }

    @Test
    @DisplayName("Nome com menos de 3 caracteres é rejeitado")
    void nomeCurto() {
        paciente.setNome("Jo");
        assertEquals("Nome deve ter no mínimo 3 caracteres", mensagemDoErro());
    }

    @Test
    @DisplayName("Nome com mais de 100 caracteres é rejeitado")
    void nomeLongo() {
        paciente.setNome("A".repeat(101));
        assertEquals("Nome deve ter no máximo 100 caracteres", mensagemDoErro());
    }

    @Test
    @DisplayName("CPF obrigatório")
    void cpfObrigatorio() {
        paciente.setCpf("");
        assertEquals("CPF é obrigatório", mensagemDoErro());
    }

    @Test
    @DisplayName("CPF com quantidade de dígitos errada é rejeitado")
    void cpfTamanhoErrado() {
        paciente.setCpf("123.456.789");
        assertEquals("CPF deve ter 11 dígitos", mensagemDoErro());
    }

    @ParameterizedTest(name = "CPF com dígitos repetidos ({0}) é inválido")
    @ValueSource(strings = {"111.111.111-11", "000.000.000-00", "99999999999"})
    void cpfDigitosRepetidos(String cpf) {
        paciente.setCpf(cpf);
        assertEquals("CPF inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("CPF com dígito verificador incorreto é rejeitado")
    void cpfDigitoVerificadorErrado() {
        paciente.setCpf("529.982.247-26");
        assertEquals("CPF inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("Data de nascimento obrigatória")
    void dataNascimentoObrigatoria() {
        paciente.setDataNascimento(null);
        assertEquals("Data de nascimento é obrigatória", mensagemDoErro());
    }

    @Test
    @DisplayName("Data de nascimento futura é rejeitada")
    void dataNascimentoFutura() {
        paciente.setDataNascimento(new Date(System.currentTimeMillis() + 86_400_000L));
        assertEquals("Data de nascimento não pode ser no futuro", mensagemDoErro());
    }

    @Test
    @DisplayName("Idade acima de 120 anos é rejeitada")
    void idadeMaiorQue120() {
        paciente.setDataNascimento(new Date(System.currentTimeMillis() - 125 * UM_ANO));
        assertEquals("Data de nascimento inválida (idade superior a 120 anos)", mensagemDoErro());
    }

    @ParameterizedTest(name = "E-mail inválido: \"{0}\"")
    @ValueSource(strings = {"maria.email.com", "maria@", "@email.com"})
    void emailInvalido(String email) {
        paciente.setEmail(email);
        assertEquals("E-mail inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("E-mail obrigatório")
    void emailObrigatorio() {
        paciente.setEmail(null);
        assertEquals("E-mail é obrigatório", mensagemDoErro());
    }

    @ParameterizedTest(name = "Telefone válido: {0}")
    @ValueSource(strings = {"(13) 3333-4444", "13 99999-8888", "1333334444"})
    void telefoneValido(String telefone) {
        paciente.setTelefone(telefone);
        assertDoesNotThrow(() -> validator.validar(paciente));
    }

    @ParameterizedTest(name = "Telefone inválido: {0}")
    @ValueSource(strings = {"99999-888", "(13) 99999-88889"})
    void telefoneInvalido(String telefone) {
        paciente.setTelefone(telefone);
        assertEquals("Telefone inválido", mensagemDoErro());
    }

    @Test
    @DisplayName("Telefone obrigatório")
    void telefoneObrigatorio() {
        paciente.setTelefone(" ");
        assertEquals("Telefone é obrigatório", mensagemDoErro());
    }
}
