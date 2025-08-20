package Controller;

import Dao.ReservaDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import Service.ReservaService;
import View.CadastrarReservaView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastrarReservaController {

    private final CadastrarReservaView view;
    private final ArrayList<Reserva> listaReservas;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final SalaDao salaDao = new SalaDao();
    private final ReservaDao reservaDao = new ReservaDao();
    private final ReservaService reservaService = new ReservaService(reservaDao);

    public CadastrarReservaController(JDesktopPane tela,
                                      ArrayList<Usuario> listaUsuarios,
                                      ArrayList<Sala> listaSalas,
                                      ArrayList<Reserva> listaReservas) {
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
        this.listaReservas = listaReservas;

        carregarDados();

        this.view = new CadastrarReservaView();
        tela.add(view);

        int x = (tela.getWidth() - view.getWidth()) / 2;
        int y = (tela.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        view.getBtnSalvar().addActionListener(e -> salvarReserva());

        view.setVisible(true);
    }

    private void carregarDados() {
        try {
            if (listaUsuarios.isEmpty()) {
                listaUsuarios.addAll(usuarioDao.carregar());
            }
            if (listaSalas.isEmpty()) {
                listaSalas.addAll(salaDao.carregar());
            }
            listaReservas.clear();
            listaReservas.addAll(reservaService.carregarReservas(listaUsuarios, listaSalas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void salvarReserva() {
        String cpf = view.getTxtCpfUsuario().getText().trim();
        String codigoSala = view.getTxtCodigoSala().getText().trim();
        String inicio = view.getTxtDataInicio().getText().trim();
        String fim = view.getTxtDataFim().getText().trim();

        try {
            reservaService.validarCamposObrigatorios(cpf, codigoSala, inicio, fim);

            Usuario usuario = listaUsuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
            Sala sala = listaSalas.stream().filter(s -> s.getCodigoSala().equals(codigoSala)).findFirst().orElse(null);

            reservaService.validarUsuarioESala(usuario, sala);

            Reserva reserva = reservaService.criarReserva(usuario, sala, inicio, fim);
            reservaService.salvarReserva(reserva, listaReservas);

            JOptionPane.showMessageDialog(view, "Reserva cadastrada com sucesso!\nPreço: " + reserva.calcularPreco());
            view.dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar no banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
