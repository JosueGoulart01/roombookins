// RecursoTest.java
import org.junit.Test;
import static org.junit.Assert.*;

public class RecursoTest {

    @Test
    public void testeGetters() {
        Recurso r = new Recurso("Projetor", "eletronico");
        assertEquals("Projetor", r.getNome());
        assertEquals("eletronico", r.getTipo());
    }

    @Test
    public void testeEscritaArquivo() {
        Recurso r = new Recurso("Sistema de som", "eletronico");
        String esperado = "Sistema de som;eletronico";
        assertEquals(esperado, r.escritaArquivo());
    }
}
