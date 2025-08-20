package Service;

import Dao.ReservaDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final ReservaDao reservaDao;

    public ReservaService(ReservaDao reservaDao) {
        this.reservaDao = reservaDao;
    }

    /**
     * Valida se os campos obrigatórios foram preenchidos.
     */
    public void validarCamposObrigatorios(String cpf, String codigoSala, String inicio, String fim) {
        if (cpf == null || cpf.isEmpty() ||
            codigoSala == null || codigoSala.isEmpty() ||
            inicio == null || inicio.isEmpty() ||
            fim == null || fim.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }
    }

    /**
     * Verifica se o usuário e a sala existem.
     */
    public void validarUsuarioESala(Usuario usuario, Sala sala) {
        if (usuario == null || sala == null) {
            throw new IllegalArgumentException("Usuário ou Sala não encontrados.");
        }
    }

    /**
     * Cria uma nova reserva a partir das datas em formato string.
     */
    public Reserva criarReserva(Usuario usuario, Sala sala, String inicio, String fim) {
        try {
            LocalDateTime dataInicio = LocalDateTime.parse(inicio);
            LocalDateTime dataFim = LocalDateTime.parse(fim);

            if (dataInicio.isAfter(dataFim)) {
                throw new IllegalArgumentException("Data de início deve ser anterior à data de fim.");
            }

            return new Reserva(usuario, sala, dataInicio, dataFim);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato da data inválido. Use o formato ISO: yyyy-MM-ddTHH:mm:ss");
        }
    }

    /**
     * Verifica se a reserva não colide com outra existente e persiste no banco.
     */
    public void salvarReserva(Reserva reserva, List<Reserva> reservasExistentes) throws SQLException {
        boolean horarioDisponivel = reserva.getSala().verificarHorario(reservasExistentes, reserva);

        if (!horarioDisponivel) {
            throw new IllegalArgumentException("Sala já está reservada neste horário.");
        }

        reservaDao.salvar(reserva);
    }

    /**
     * Carrega as reservas do banco associando usuários e salas já carregados.
     */
    public List<Reserva> carregarReservas(List<Usuario> usuarios, List<Sala> salas) throws SQLException {
        return reservaDao.carregar(usuarios, salas);
    }
}
