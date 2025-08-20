package Service;

import Dao.ReservaDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class CancelarReservaService {

    private final ArrayList<Reserva> listaReservas;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final SalaDao salaDao = new SalaDao();
    private final ReservaDao reservaDao = new ReservaDao();

    public CancelarReservaService(ArrayList<Usuario> listaUsuarios,
                                   ArrayList<Sala> listaSalas,
                                   ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
    }

    public void carregarDados() {
        try {
            if (listaUsuarios.isEmpty()) {
                listaUsuarios.addAll(usuarioDao.carregar());
            }
            if (listaSalas.isEmpty()) {
                listaSalas.addAll(salaDao.carregar());
            }
            listaReservas.clear();
            listaReservas.addAll(reservaDao.carregar(listaUsuarios, listaSalas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Reserva> buscarReservasPorCpf(String cpf) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        for (Reserva r : listaReservas) {
            if (r.getUsuario().getCpf().equals(cpf)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public double cancelarReserva(Reserva reserva) {
        if (reserva == null) return -1;

        double estorno = reserva.removeReserva(listaReservas);

        try {
            reservaDao.salvar(listaReservas);
            return estorno;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}
