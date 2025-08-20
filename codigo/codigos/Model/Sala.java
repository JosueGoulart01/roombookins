package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class   Sala {
    protected String codigoSala;
    protected int capacidade;
    protected Endereco endereco;
    protected  final static double PRECO_HORA= 50;
    protected int tipo;
    protected ArrayList<Recurso> recursos;
    
    public Sala(String codigoSala, int capacidade, int tipo ,Endereco endereco) throws Exception {
        this.codigoSala = codigoSala;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.endereco = endereco;
        this.recursos = new ArrayList<>(RecursoFactory.getStrategy(tipo).gerarRecursos());
        if(!verificarCodigoSala(codigoSala)){
            throw new Exception("Codigo invalido!!");
            }
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public int getTipo() {
        return tipo;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    private boolean verificarCodigoSala(String codigoSala){
        Pattern codigoPadrao = Pattern.compile("^[a-zA-Z]{3}[0-9]{3}$");
        Matcher matcher= codigoPadrao.matcher(codigoSala);
        return matcher.matches();
    }

    public boolean verificarHorario(List<Reserva> reservas, Reserva novaReserva) {
    for (Reserva r : reservas) {
        if (r.getSala().getCodigoSala().equals(novaReserva.getSala().getCodigoSala())) {
            boolean sobrepoe =
                    !novaReserva.getDataFim().isBefore(r.getDataInicio()) &&
                    !novaReserva.getDataInicio().isAfter(r.getDataFim());
            if (sobrepoe) return false;
        }
    }
    return true;
}
   
    public abstract double calcularPreco(Reserva r);
    public abstract double getPorcentagemRembolso();
}
