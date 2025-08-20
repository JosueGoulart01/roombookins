package Model;

import java.util.List;

public class RecursoSalaVip implements RecursoStrategy {
    public List<Recurso> gerarRecursos() {
        return List.of(
            new Recurso("Ar-condicionado", "Conforto"),
            new Recurso("Poltronas VIP", "Conforto"),
            new Recurso("Sistema de Som", "Tecnologia"),
            new Recurso("Projetor 4K", "Tecnologia"),
            new Recurso("Lousa Interativa", "Educação"),
            new Recurso("Internet Wi-Fi", "Tecnologia"),
            new Recurso("Cadeiras Ergonômicas", "Conforto")
        );
    }
}
