package Model;

import java.util.List;

public class RecursoSalaStandard implements RecursoStrategy {
    public List<Recurso> gerarRecursos() {
        return List.of(
            new Recurso("Cadeiras Ergonômicas", "Conforto"),
            new Recurso("Internet Wi-Fi", "Tecnologia")
        );
    }
}
