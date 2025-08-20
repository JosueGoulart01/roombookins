package Model;

public class RecursoFactory {
    public static RecursoStrategy getStrategy(int tipo) {
        return switch (tipo) {
            case 1 -> new RecursoSalaPremium();
            case 2 -> new RecursoSalaVip();
            case 3 -> new RecursoSalaStandard();
            default -> throw new IllegalArgumentException("Tipo de sala inválido");
        };
    }
}
