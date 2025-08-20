package Controller;

import Dao.ReservaDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.*;
import View.ListarReservasView;
import java.util.ArrayList;
import javax.swing.*;

public class ListarReservasController {

    private ArrayList<Reserva> listaReservas;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Sala> listaSalas;
    private ListarReservasView view;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final SalaDao salaDao = new SalaDao();
    private final ReservaDao reservaDao = new ReservaDao();

    public ListarReservasController(JDesktopPane desktopPane,
                                   ArrayList<Reserva> listaReservas,
                                   ArrayList<Usuario> listaUsuarios,
                                   ArrayList<Sala> listaSalas) {
        this.listaReservas = listaReservas;
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;

        carregarDadosSeNecessario();

        view = new ListarReservasView(this.listaReservas);
        desktopPane.add(view);
        view.setVisible(true);

        // Centraliza janela
        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        try {
            view.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void carregarDadosSeNecessario() {
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
            JOptionPane.showMessageDialog(null,
                "Erro ao carregar dados: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
