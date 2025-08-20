package Model;

import java.util.List;

public class RecursoSalaPremium implements RecursoStrategy {
    public List<Recurso> gerarRecursos() {
        return List.of(
            new Recurso("Ar-condicionado", "Conforto"),
            new Recurso("Projetor 4K", "Tecnologia"),
            new Recurso("Sistema de Som", "Tecnologia"),
            new Recurso("Internet Wi-Fi", "Tecnologia")
        );
    }
}
