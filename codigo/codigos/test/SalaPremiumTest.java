// SalaPremiumTest.java
import org.junit.Test;
import Model.Endereco;
import Model.Reserva;
import Model.SalaPremium;
import Model.Usuario;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SalaPremiumTest {

    @Test
    public void testeRecursosIniciais() throws Exception {
        Endereco end = new Endereco("55555-555", "Rua Premium", "99", "Top", "Mundo");
        SalaPremium prem = new SalaPremium("654MNO", 20, end);

        assertEquals(20, prem.getCapacidade());
        assertEquals(end, prem.getEndereco());
        assertTrue(prem.getRecursos().stream().anyMatch(r -> r.getNome().equals("ar-condicionado")));
        assertTrue(prem.getRecursos().stream().anyMatch(r -> r.getNome().equals("projetor")));
    }

    @Test
    public void testeCalcularPrecoEPorcentagemRembolso() throws Exception {
        Endereco end = new Endereco("55555-555", "Rua Premium", "99", "Top", "Mundo");
        SalaPremium prem = new SalaPremium("654MNO", 20, end);

        LocalDateTime inicio = LocalDateTime.of(2025, 9, 5, 10, 0);
        LocalDateTime fim    = LocalDateTime.of(2025, 9, 5, 13, 0);
        Reserva r = new Reserva(new Usuario("P", "55555555555", end, false), inicio, fim);

        double preco = prem.calcularPreco(r);
        // 3 horas * 50 * 1.15
        assertEquals(3 * 50 * SalaPremium.PORCENTAGEM_AUMENTO, preco, 0.001);
        assertEquals(0.4, prem.getPorcentagemRembolso(), 0.0001);
    }
}
