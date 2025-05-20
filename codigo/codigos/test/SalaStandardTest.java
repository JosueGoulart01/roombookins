// SalaStandardTest.java
import org.junit.Test;

import Model.Endereco;
import Model.Reserva;
import Model.SalaStandard;
import Model.Usuario;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SalaStandardTest {

    @Test
    public void testeConstrutorEGetters() throws Exception {
        Endereco end = new Endereco("11111-111", "Rua X", "5", "ABC", "Brasil");
        SalaStandard sala = new SalaStandard("123ABC", end, 10);

        assertEquals("123ABC", sala.getCodigoSala());
        assertEquals(10, sala.getCapacidade());
        assertTrue(sala.getRecursos().isEmpty());
        assertEquals(end, sala.getEndereco());
    }

    @Test
    public void testeCalcularPrecoEPorcentagemRembolso() throws Exception {
        Endereco end = new Endereco("11111-111", "Rua X", "5", "ABC", "Brasil");
        SalaStandard sala = new SalaStandard("123ABC", end, 10);

        LocalDateTime inicio = LocalDateTime.of(2025, 6, 1, 8, 0);
        LocalDateTime fim    = LocalDateTime.of(2025, 6, 1, 10, 0);
        Reserva r = new Reserva(new Usuario("U", "00000000000", end, false), inicio, fim);

        double preco = sala.calcularPreco(r);
        assertEquals(2 * 50.0, preco, 0.001);

        assertEquals(0.6, sala.getPorcentagemRembolso(), 0.0001);
    }

    @Test
    public void testeAddERemoveReserva() throws Exception {
        Endereco end = new Endereco("22222-222", "Rua Y", "6", "DEF", "Brasil");
        SalaStandard sala = new SalaStandard("456DEF", end, 5);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inicio = now.plusDays(2);
        LocalDateTime fim    = inicio.plusHours(1);
        Reserva r = new Reserva(new Usuario("X", "11111111111", end, false), inicio, fim);

        double valor = sala.addReserva(r);
        assertEquals(1 * 50.0, valor, 0.001);
        assertTrue(sala.getReservas().contains(r));

        double estorno = sala.removeReserva(r);
        assertEquals(50.0 * sala.getPorcentagemRembolso(), estorno, 0.001);
        assertFalse(sala.getReservas().contains(r));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeReservaSobrepostaLancaExcecao() throws Exception {
        Endereco end = new Endereco("33333-333", "Rua Z", "7", "GHI", "Brasil");
        SalaStandard sala = new SalaStandard("789GHI", end, 8);

        LocalDateTime base = LocalDateTime.of(2025, 7, 1, 9, 0);
        Reserva r1 = new Reserva(new Usuario("A", "22222222222", end, false), base, base.plusHours(2));
        Reserva r2 = new Reserva(new Usuario("B", "33333333333", end, false), base.plusHours(1), base.plusHours(3));

        sala.addReserva(r1);
        sala.addReserva(r2); // deve lançar IllegalArgumentException
    }
}
