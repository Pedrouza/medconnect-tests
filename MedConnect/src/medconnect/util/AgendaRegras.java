package medconnect.util;

import medconnect.model.Consulta;
import java.util.Date;
import java.util.List;

/**
 * Regras de agenda extraídas do ConsultaService para permitir teste unitário
 * sem acesso ao banco de dados.
 */
public final class AgendaRegras {

    private AgendaRegras() {}

    /**
     * Retorna true se já existe, na lista de consultas do médico, alguma
     * consulta marcada exatamente para a mesma data/hora.
     */
    public static boolean existeConflitoHorario(List<Consulta> consultasDoMedico, Date novaDataHora) {
        if (consultasDoMedico == null || novaDataHora == null) {
            return false;
        }
        for (Consulta c : consultasDoMedico) {
            if (c.getDataHora() != null && c.getDataHora().equals(novaDataHora)) {
                return true;
            }
        }
        return false;
    }
}
