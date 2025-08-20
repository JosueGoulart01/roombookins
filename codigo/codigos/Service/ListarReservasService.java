package Service;

import Dao.ReservaDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;

import javax.swing.*;
import java.util.ArrayList;

public class ListarReservasService {

    private final ArrayList<Reserva> listaReservas;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final SalaDao salaDao = new SalaDao();
    private final ReservaDao reservaDao = new ReservaDao();

    public ListarReservasService(ArrayList<Reserva> listaReservas, ArrayList<Usuario> listaUsuarios, ArrayList<Sala> listaSalas) {
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
}
