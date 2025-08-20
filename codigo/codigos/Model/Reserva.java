package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {
    private Usuario usuario;
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private static int cont = 0;
    private Sala sala;

    public Reserva(Usuario usuario, Sala sala, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.usuario = usuario;
        this.sala = sala;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.id = cont++;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public double estornarValor() {
        return 0.0;
    }

    public double calcularPreco() {
        return sala.calcularPreco(this);
    }

    public double removeReserva(ArrayList<Reserva> listaReservas) {
        LocalDateTime agora = LocalDateTime.now();
        long horasParaReserva = Duration.between(agora, this.getDataInicio()).toHours();

        double valorRembolso = 0.0;
        if (horasParaReserva >= 24) {
            valorRembolso = this.getSala().calcularPreco(this) * this.getSala().getPorcentagemRembolso();
        }

        listaReservas.remove(this);
        return valorRembolso;
    }
}
