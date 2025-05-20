package Model;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class   Sala {
    protected String codigoSala;
    protected int capacidade;
    protected Endereco endereco;
    protected  final static double PRECO_HORA= 50;
    protected int tipo;
    
    public Sala(String codigoSala, int capacidade, int tipo ,Endereco endereco) throws Exception {
        this.codigoSala = codigoSala;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.endereco = endereco;
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

    private boolean verificarCodigoSala(String codigoSala){
        Pattern codigoPadrao = Pattern.compile("^[a-zA-Z]{3}[0-9]{3}$");
        Matcher matcher= codigoPadrao.matcher(codigoSala);
        return matcher.matches();
    }

    public Boolean verificarHorario(ArrayList<Reserva>reservas,Reserva reserva){
        for(Reserva r : reservas){
            //compara as datas para ve se ja estao sendo usadas
            if((r.getDataInicio().isBefore(reserva.getDataFim()) && r.getDataInicio().isAfter(reserva.getDataInicio()))
            || (r.getDataFim().isBefore(reserva.getDataFim()) && r.getDataFim().isAfter(reserva.getDataInicio()))){
                return false;
            }
            if(r.getDataInicio().equals(reserva.getDataInicio()) || r.getDataFim().equals(reserva.getDataFim())){
                return false;
            }
        }
        return true;
    }
   
    public abstract double calcularPreco(Reserva r);
    public abstract double getPorcentagemRembolso();
    public abstract String escritaArquivo();
}
