package Controller;

import Model.Reserva;
import View.RelatorioView;
import java.util.List;
import javax.swing.*;

public class RelatorioController {

    private RelatorioView relatorioView;
    private List<Reserva> listaReservas;
    private JDesktopPane desktopPane;

    public RelatorioController(JDesktopPane desktopPane, List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
        this.desktopPane = desktopPane;

        this.relatorioView = new RelatorioView();

        desktopPane.add(relatorioView);
        relatorioView.setVisible(true);

        int x = (desktopPane.getWidth() - relatorioView.getWidth()) / 2;
        int y = (desktopPane.getHeight() - relatorioView.getHeight()) / 2;
        relatorioView.setLocation(x, y);

        adicionarAcoes();
    }

    private void adicionarAcoes() {
        relatorioView.getBtnTotalArrecadado().addActionListener(e -> abrirRelatorioTotalArrecadado());
        relatorioView.getBtnSalasMaisReservadas().addActionListener(e -> {
            relatorioView.exibirMensagem("Relatório Salas Mais Reservadas em construção.");
        });
        relatorioView.getBtnMediaHorasPorCliente().addActionListener(e -> {
            relatorioView.exibirMensagem("Relatório Média de Horas por Cliente em construção.");
        });
    }

    private void abrirRelatorioTotalArrecadado() {
        RelatorioTotalArrecadadoController controller = new RelatorioTotalArrecadadoController(desktopPane, listaReservas);
    }
}
