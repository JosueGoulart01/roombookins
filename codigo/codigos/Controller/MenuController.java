package Controller;

import Model.*;
import View.MenuView;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class MenuController {
    private MenuView menuView;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Sala> listaSalas;
    private ArrayList<Reserva> listaReservas;
    private File arquivoSalas, arquivoUsuarios, arquivoReservas;

    public MenuController(ArrayList<Usuario> listaUsuarios, ArrayList<Sala> listaSalas, ArrayList<Reserva> listaReservas,
                          File arquivoSalas, File arquivoUsuarios, File arquivoReservas) {
        this.menuView = new MenuView();
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
        this.listaReservas = listaReservas;
        this.arquivoSalas = arquivoSalas;
        this.arquivoUsuarios = arquivoUsuarios;
        this.arquivoReservas = arquivoReservas;

        this.menuView.getBtnCadastrarUsuario().addActionListener(e -> {
            new CadastrarUsuarioController(menuView.getTela());
        });

        this.menuView.getBtnCadastrarReserva().addActionListener(e -> {
            new CadastrarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas,
                                           arquivoSalas, arquivoUsuarios, arquivoReservas);
        });

        this.menuView.getBtnCadastrarSala().addActionListener(e -> {
            new CadastrarSalaController(menuView.getTela(), listaSalas, listaUsuarios, listaReservas,
                                        arquivoSalas, arquivoUsuarios, arquivoReservas);
        });

        this.menuView.getBtnCancelarReserva().addActionListener(e -> {
            new CancelarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas,
                                        arquivoSalas, arquivoUsuarios, arquivoReservas);
        });

        this.menuView.getBtnListarReservas().addActionListener(e -> {
            if (listaReservas.isEmpty()) {
                JOptionPane.showMessageDialog(menuView, "Nenhuma reserva cadastrada.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Reserva r : listaReservas) {
                    sb.append("Usuário: ").append(r.getUsuario().getNome()).append(" - CPF: ").append(r.getUsuario().getCpf())
                      .append("\nSala: ").append(r.getSala().getCodigoSala())
                      .append("\nInício: ").append(r.getDataInicio())
                      .append("\nFim: ").append(r.getDataFim())
                      .append("\n\n");
                }
                JTextArea area = new JTextArea(sb.toString());
                area.setEditable(false);
                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(500, 300));
                JOptionPane.showMessageDialog(menuView, scroll, "Lista de Reservas", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Listener do botão Gerar Relatório
        this.menuView.getBtnGerarRelatorio().addActionListener(e -> {
            RelatorioUtil.gerarRelatorioSalasMaisRentaveis(listaReservas);
        });

        menuView.setVisible(true);
    }
}
