// SalaVipTest.java
import org.junit.Test;

import Model.Endereco;
import Model.Reserva;
import Model.SalaVip;
import Model.Usuario;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SalaVipTest {

    @Test
    public void testeCapacidadeAumentadaERecursos() throws Exception {
        Endereco end = new Endereco("44444-444", "Av. VIP", "1", "Luxo", "ZonaNobre");
        SalaVip vip = new SalaVip("321JKL", end, 10);

        // capacidade * AUMENTAR_CAPACIDADE (1.3) => (int)(10 * 1.3) == 13
        assertEquals(13, vip.getCapacidade());
        assertEquals(end, vip.getEndereco());
        assertTrue(vip.getRecursos().stream().anyMatch(r -> r.getNome().equals("Projetor")));
        assertTrue(vip.getRecursos().stream().anyMatch(r -> r.getNome().equals("Poltronas VIP")));
    }

    @Test
    public void testeCalcularPrecoEPorcentagemRembolso() throws Exception {
        Endereco end = new Endereco("44444-444", "Av. VIP", "1", "Luxo", "ZonaNobre");
        SalaVip vip = new SalaVip("321JKL", end, 10);

        LocalDateTime inicio = LocalDateTime.of(2025, 8, 1, 14, 0);
        LocalDateTime fim    = LocalDateTime.of(2025, 8, 1, 16, 0);
        Reserva r = new Reserva(new Usuario("VIP", "44444444444", end, true), inicio, fim);

        double preco = vip.calcularPreco(r);
        // 2 horas * 50 * 1.3
        assertEquals(2 * 50 * SalaVip.PORCENTAGEM_AUMENTO, preco, 0.001);
        assertEquals(0.3, vip.getPorcentagemRembolso(), 0.0001);
    }
}
