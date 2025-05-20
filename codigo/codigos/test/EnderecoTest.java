// EnderecoTest.java
import org.junit.Test;

import Model.Endereco;

import static org.junit.Assert.*;

public class EnderecoTest {

    @Test
    public void testeGettersESetters() {
        Endereco end = new Endereco("54321-000", "Av. Brasil", "100", "Rio", "Brasil");
        assertEquals("54321-000", end.getCep());
        assertEquals("Av. Brasil", end.getRua());
        assertEquals("100", end.getNumero());
        assertEquals("Rio", end.getCidade());
        assertEquals("Brasil", end.getPais());

        end.setCep("00000-111");
        end.setRua("Rua Nova");
        end.setNumero("200");
        end.setCidade("São Paulo");
        end.setPais("Brasil");

        assertEquals("00000-111", end.getCep());
        assertEquals("Rua Nova", end.getRua());
        assertEquals("200", end.getNumero());
        assertEquals("São Paulo", end.getCidade());
        assertEquals("Brasil", end.getPais());
    }

    @Test
    public void testeEscritaArquivo() {
        Endereco end = new Endereco("54321-000", "Av. Brasil", "100", "Rio", "Brasil");
        String esperado = "54321-000;Av. Brasil;100;Rio;Brasil";
        assertEquals(esperado, end.escritaArquivo());
    }
}
