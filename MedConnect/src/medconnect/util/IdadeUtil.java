package medconnect.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * Regras de cálculo relacionadas à idade.
 * Responsabilidade Única: cálculo puro, sem acesso a banco ou interface.
 */
public final class IdadeUtil {

    public static final int IDADE_MAIORIDADE = 18;

    private IdadeUtil() {}

    /**
     * Calcula a idade completa (em anos) na data de referência,
     * respeitando o dia e o mês do aniversário e anos bissextos.
     *
     * @throws IllegalArgumentException se alguma data for nula ou se o
     *         nascimento for posterior à data de referência
     */
    public static int calcularIdade(LocalDate nascimento, LocalDate referencia) {
        if (nascimento == null || referencia == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        if (nascimento.isAfter(referencia)) {
            throw new IllegalArgumentException("Data de nascimento posterior à data de referência");
        }
        return Period.between(nascimento, referencia).getYears();
    }

    /** Indica se a pessoa tem 18 anos ou mais na data de referência. */
    public static boolean isMaiorDeIdade(LocalDate nascimento, LocalDate referencia) {
        return calcularIdade(nascimento, referencia) >= IDADE_MAIORIDADE;
    }
}
