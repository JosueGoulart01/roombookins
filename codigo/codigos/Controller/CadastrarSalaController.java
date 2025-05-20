// CadastrarSalaController.java - Atualizado para salvar no arquivo
package Controller;

import Dao.Dados;
import Model.*;
import View.CadastrarSalaView;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class CadastrarSalaController {
    private CadastrarSalaView cadastrarSalaView;
    private ArrayList<Sala> listaSalas;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Reserva> listaReservas;
    private File arquivoSalas, arquivoUsuarios, arquivoReservas;

    public CadastrarSalaController(JDesktopPane tela, ArrayList<Sala> listaSalas, ArrayList<Usuario> listaUsuarios,
                                   ArrayList<Reserva> listaReservas, File arquivoSalas, File arquivoUsuarios, File arquivoReservas) {
        this.listaSalas = listaSalas;
        this.listaUsuarios = listaUsuarios;
        this.listaReservas = listaReservas;
        this.arquivoSalas = arquivoSalas;
        this.arquivoUsuarios = arquivoUsuarios;
        this.arquivoReservas = arquivoReservas;

        cadastrarSalaView = new CadastrarSalaView();
        tela.add(cadastrarSalaView);

        int x = (tela.getWidth() - cadastrarSalaView.getWidth()) / 2;
        int y = (tela.getHeight() - cadastrarSalaView.getHeight()) / 2;
        cadastrarSalaView.setLocation(x, y);

        cadastrarSalaView.getBtnSalvar().addActionListener(e -> salvarSala());

        cadastrarSalaView.setVisible(true);
    }

    private void salvarSala() {
        String codigo = cadastrarSalaView.getTxtCodigoSala().getText();
        int capacidade;
        try {
            capacidade = Integer.parseInt(cadastrarSalaView.getTxtCapacidade().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(cadastrarSalaView, "Capacidade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoSala = (String) cadastrarSalaView.getComboTipo().getSelectedItem();
        int tipo = tipoSala.equals("Premium") ? 1 : tipoSala.equals("Vip") ? 2 : 3;

        Endereco endereco = new Endereco(
            cadastrarSalaView.getTxtCep().getText(),
            cadastrarSalaView.getTxtRua().getText(),
            cadastrarSalaView.getTxtNumero().getText(),
            cadastrarSalaView.getTxtCidade().getText(),
            cadastrarSalaView.getTxtPais().getText()
        );

        try {
            Sala sala;
            switch (tipo) {
                case 1 -> sala = new SalaPremium(codigo, capacidade, tipo, endereco);
                case 2 -> sala = new SalaVip(codigo, capacidade, tipo, endereco);
                case 3 -> sala = new SalaStandard(codigo, capacidade, tipo, endereco);
                default -> throw new IllegalArgumentException("Tipo de sala inválido");
            }
            listaSalas.add(sala);
            try {
                Dados.escritaArquivos(listaSalas, listaUsuarios, listaReservas, arquivoSalas, arquivoUsuarios, arquivoReservas);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(cadastrarSalaView, "Erro ao salvar arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(cadastrarSalaView, "Sala cadastrada com sucesso!");
            cadastrarSalaView.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cadastrarSalaView, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
