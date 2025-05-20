package Model;
import java.time.Duration;

public class SalaStandard extends Sala {
    public final static double PORCENTAGEM_REMBOLSO= 0.6;
    private String recursos;

    public SalaStandard(String codigoSala, int capacidade,int tipo ,Endereco endereco) throws Exception {
        super(codigoSala, capacidade,tipo, endereco);
        this.recursos= "Tv";
    }

    @Override
    public double calcularPreco(Reserva r) {
        Duration duracao= Duration.between(r.getDataInicio(), r.getDataFim());
            return ((duracao.toMinutes()/60)*PRECO_HORA);
    }
    @Override
    public double getPorcentagemRembolso() {
        return PORCENTAGEM_REMBOLSO;
    }
    @Override
    public String escritaArquivo(){
        return codigoSala + ";" + capacidade  + ";"+ tipo + ";" + endereco.escritaArquivo() + ";" + recursos;
    }
}
