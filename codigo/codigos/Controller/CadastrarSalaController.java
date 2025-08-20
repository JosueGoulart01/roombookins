package Controller;

import Dao.SalaDao;
import Model.*;
import Service.SalaService;
import View.CadastrarSalaView;
import java.util.ArrayList;
import javax.swing.*;

public class CadastrarSalaController {

    private final CadastrarSalaView view;
    private final ArrayList<Sala> listaSalas;
    private final SalaService salaService;

    public CadastrarSalaController(JDesktopPane tela,
                                   ArrayList<Sala> listaSalas,
                                   ArrayList<Usuario> listaUsuarios,
                                   ArrayList<Reserva> listaReservas) {
        this.listaSalas = listaSalas;
        this.salaService = new SalaService(new SalaDao());

        view = new CadastrarSalaView();
        tela.add(view);

        int x = (tela.getWidth() - view.getWidth()) / 2;
        int y = (tela.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        carregarSalas();
        view.getBtnSalvar().addActionListener(e -> salvarSala());
        view.setVisible(true);
    }

    private void carregarSalas() {
        try {
            salaService.carregarSalasSeNecessario(listaSalas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao carregar salas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Endereco criarEndereco() {
        return new Endereco(
                view.getTxtCep().getText().trim(),
                view.getTxtRua().getText().trim(),
                view.getTxtNumero().getText().trim(),
                view.getTxtCidade().getText().trim(),
                view.getTxtPais().getText().trim()
        );
    }

    private void salvarSala() {
        String codigo = view.getTxtCodigoSala().getText().trim();
        String capacidadeStr = view.getTxtCapacidade().getText().trim();
        String tipoSalaNome = (String) view.getComboTipo().getSelectedItem();
        Endereco endereco = criarEndereco();

        try {
            salaService.validarCamposObrigatorios(codigo, capacidadeStr, endereco, tipoSalaNome);
            int capacidade = salaService.converterCapacidade(capacidadeStr);
            int tipo = salaService.obterTipoPorNome(tipoSalaNome);
            Sala novaSala = salaService.criarSala(codigo, capacidade, tipo, endereco);

            listaSalas.add(novaSala);
            salaService.salvarSalas(listaSalas);

            JOptionPane.showMessageDialog(view,
                    "Sala cadastrada com sucesso!\nRecursos: " +
                            novaSala.getRecursos().stream().map(Recurso::getNome).toList());

            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
