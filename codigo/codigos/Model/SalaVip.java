package Model;
import java.time.Duration;

public class SalaVip extends Sala {
    public final static double PORCENTAGEM_AUMENTO= 1.3;
    public final static double PORCENTAGEM_REMBOLSO= 0.3;
    public final static double AUMENTAR_CAPACIDADE= 1.3;


    public SalaVip(String codigoSala, int capacidade ,int tipo, Endereco endereco) throws Exception {
        super(codigoSala, (int)(capacidade * AUMENTAR_CAPACIDADE),tipo ,endereco ); 
    }

    @Override
    public double calcularPreco(Reserva r) {
        Duration duracao= Duration.between(r.getDataInicio(), r.getDataFim());
        return ((duracao.toMinutes()/60)*PRECO_HORA)*PORCENTAGEM_AUMENTO;
    }
    @Override
    public double getPorcentagemRembolso() {
        return PORCENTAGEM_REMBOLSO;
    }
}
    
