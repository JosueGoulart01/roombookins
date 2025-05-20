package Controller;

import Dao.Dados;
import Model.*;
import View.CadastrarReservaView;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;

public class CadastrarReservaController {
    private CadastrarReservaView cadastrarReservaView;
    private ArrayList<Reserva> listaReservas;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Sala> listaSalas;
    private File arquivoSalas, arquivoUsuarios, arquivoReservas;

    public CadastrarReservaController(JDesktopPane tela, ArrayList<Usuario> listaUsuarios, ArrayList<Sala> listaSalas,
                                     ArrayList<Reserva> listaReservas, File arquivoSalas, File arquivoUsuarios, File arquivoReservas) {
        this.listaReservas = listaReservas;
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
        this.arquivoSalas = arquivoSalas;
        this.arquivoUsuarios = arquivoUsuarios;
        this.arquivoReservas = arquivoReservas;

        cadastrarReservaView = new CadastrarReservaView();
        tela.add(cadastrarReservaView);

        int x = (tela.getWidth() - cadastrarReservaView.getWidth()) / 2;
        int y = (tela.getHeight() - cadastrarReservaView.getHeight()) / 2;
        cadastrarReservaView.setLocation(x, y);

        cadastrarReservaView.getBtnSalvar().addActionListener(e -> salvarReserva());

        cadastrarReservaView.setVisible(true);
    }

    private void salvarReserva() {
        String cpf = cadastrarReservaView.getTxtCpfUsuario().getText();
        String codigoSala = cadastrarReservaView.getTxtCodigoSala().getText();
        String inicio = cadastrarReservaView.getTxtDataInicio().getText();
        String fim = cadastrarReservaView.getTxtDataFim().getText();

        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        Sala sala = listaSalas.stream()
                .filter(s -> s.getCodigoSala().equals(codigoSala))
                .findFirst()
                .orElse(null);

        if (usuario == null || sala == null) {
            JOptionPane.showMessageDialog(cadastrarReservaView, "Usuário ou Sala não encontrados.");
            return;
        }

        try {
            LocalDateTime dataInicio = LocalDateTime.parse(inicio);
            LocalDateTime dataFim = LocalDateTime.parse(fim);

            Reserva reserva = new Reserva(usuario, sala, dataInicio, dataFim);

            if (!sala.verificarHorario(listaReservas, reserva)) {
                JOptionPane.showMessageDialog(cadastrarReservaView, "Sala já está reservada neste horário.");
                return;
            }

            listaReservas.add(reserva);

            Dados.escritaArquivos(listaSalas, listaUsuarios, listaReservas, arquivoSalas, arquivoUsuarios, arquivoReservas);

            JOptionPane.showMessageDialog(cadastrarReservaView,
                    "Reserva cadastrada com sucesso!\nPreço: " + reserva.calcularPreco(reserva));
            cadastrarReservaView.dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(cadastrarReservaView, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cadastrarReservaView,
                    "Erro ao cadastrar reserva. Verifique os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
