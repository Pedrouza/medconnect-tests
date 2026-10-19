package medconnect.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import medconnect.model.Consulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AgendaRegras - conflito de horário")
class AgendaRegrasTest {

    private static final long BASE = 1_800_000_000_000L;
    private static final long HORA = 3_600_000L;

    private Consulta consultaEm(long millis) {
        return new Consulta(1, 1, new Date(millis), "Rotina");
    }

    @Test
    @DisplayName("Lista vazia: não há conflito")
    void listaVazia() {
        assertFalse(AgendaRegras.existeConflitoHorario(new ArrayList<>(), new Date(BASE)));
    }

    @Test
    @DisplayName("Mesmo horário: há conflito")
    void mesmoHorario() {
        assertTrue(AgendaRegras.existeConflitoHorario(List.of(consultaEm(BASE)), new Date(BASE)));
    }

    @Test
    @DisplayName("Horários diferentes: não há conflito")
    void horariosDiferentes() {
        List<Consulta> lista = List.of(consultaEm(BASE), consultaEm(BASE + HORA));
        assertFalse(AgendaRegras.existeConflitoHorario(lista, new Date(BASE + 2 * HORA)));
    }

    @Test
    @DisplayName("Conflito é detectado em qualquer posição da lista")
    void conflitoNoFinalDaLista() {
        List<Consulta> lista = List.of(consultaEm(BASE), consultaEm(BASE + HORA), consultaEm(BASE + 2 * HORA));
        assertTrue(AgendaRegras.existeConflitoHorario(lista, new Date(BASE + 2 * HORA)));
    }

    @Test
    @DisplayName("Entradas nulas não geram conflito nem exceção")
    void entradasNulas() {
        assertFalse(AgendaRegras.existeConflitoHorario(null, new Date(BASE)));
        assertFalse(AgendaRegras.existeConflitoHorario(List.of(consultaEm(BASE)), null));
    }
}
