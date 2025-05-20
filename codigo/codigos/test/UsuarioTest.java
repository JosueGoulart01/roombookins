// UsuarioTest.java
import org.junit.Test;

import Model.Endereco;
import Model.Usuario;

import static org.junit.Assert.*;

public class UsuarioTest {

    @Test
    public void testeUsuarioCorporativo() {
        Endereco endereco = new Endereco("12345-678", "Rua A", "10", "CidadeX", "PaisY");
        Usuario user = new Usuario("João", "11122233344", endereco, true);

        assertEquals("João", user.getNome());
        assertEquals("11122233344", user.getCpf());
        assertTrue(user.isCorporativo());
        assertFalse(user.isConvidado());
        assertEquals(endereco, user.getEndereco());
    }

    @Test
    public void testeUsuarioConvidado() {
        Usuario user = new Usuario("Maria", true);

        assertEquals("Maria", user.getNome());
        assertNull(user.getCpf());
        assertFalse(user.isCorporativo());
        assertTrue(user.isConvidado());
    }

    @Test
    public void testeEscritaArquivo() {
        Endereco endereco = new Endereco("12345-678", "Rua A", "10", "CidadeX", "PaisY");
        Usuario user = new Usuario("Pedro", "99988877766", endereco, false);

        String esperado = "Pedro;99988877766;false;false;" + endereco.escritaArquivo();
        assertEquals(esperado, user.escritaArquivo());
    }
}
