package Controller;

import Model.Sala;
import View.ListarSalasView;
import java.util.List;
import javax.swing.*;

public class ListarSalasController {

    private final ListarSalasView view;

    public ListarSalasController(JDesktopPane tela, List<Sala> salas) {
        this.view = new ListarSalasView();
        tela.add(view);
        view.setVisible(true);

        int x = (tela.getWidth() - view.getWidth()) / 2;
        int y = (tela.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        preencherTabela(salas);
    }

    private void preencherTabela(List<Sala> salas) {
        view.limparTabela();

        for (Sala sala : salas) {
            String tipo;
            switch (sala.getTipo()) {
                case 1 -> tipo = "Premium";
                case 2 -> tipo = "Vip";
                case 3 -> tipo = "Standard";
                default -> tipo = "Desconhecido";
            }

            String endereco = sala.getEndereco().getRua() + ", " + sala.getEndereco().getNumero()
                    + " - " + sala.getEndereco().getCidade();

            view.adicionarSala(sala.getCodigoSala(), sala.getCapacidade(), tipo, endereco);
        }
    }
}