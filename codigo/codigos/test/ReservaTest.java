// ReservaTest.java
import org.junit.Test;

import Model.Endereco;
import Model.Reserva;
import Model.Usuario;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ReservaTest {

    @Test
    public void testeGettersEEstornar() {
        Endereco end = new Endereco("12345-678", "Rua Teste", "1", "Cidade", "Pais");
        Usuario user = new Usuario("Teste", "12345678900", end, false);
        LocalDateTime inicio = LocalDateTime.of(2025, 5, 10, 9, 0);
        LocalDateTime fim    = LocalDateTime.of(2025, 5, 10, 11, 0);
        Reserva res1 = new Reserva(user, inicio, fim);

        assertEquals(inicio, res1.getDataInicio());
        assertEquals(fim, res1.getDataFim());
        assertEquals(user, res1.getUsuario());
        assertEquals(0.0, res1.estornarValor(), 0.0001);
    }

    @Test
    public void testeEscritaArquivoContemCampos() {
        Endereco end = new Endereco("12345-678", "Rua Teste", "1", "Cidade", "Pais");
        Usuario user = new Usuario("Teste", "12345678900", end, false);
        LocalDateTime inicio = LocalDateTime.of(2025, 5, 10, 9, 0);
        LocalDateTime fim    = LocalDateTime.of(2025, 5, 10, 11, 0);
        Reserva res = new Reserva(user, inicio, fim);
        String arquivo = res.escritaArquivo();

        assertTrue(arquivo.startsWith(user.escritaArquivo() + ";" + res.getId()));
        assertTrue(arquivo.contains(inicio.toString()));
        assertTrue(arquivo.contains(fim.toString()));
    }
}
