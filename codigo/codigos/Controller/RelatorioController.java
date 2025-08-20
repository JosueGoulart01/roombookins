package Controller;

import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import Service.RelatorioService;
import View.RelatorioView;
import java.util.ArrayList;
import javax.swing.*;

public class RelatorioController {

    private final RelatorioView relatorioView;
    private final JDesktopPane desktopPane;
    private final RelatorioService relatorioService;

    public RelatorioController(JDesktopPane desktopPane,
                               ArrayList<Reserva> listaReservas,
                               ArrayList<Usuario> listaUsuarios,
                               ArrayList<Sala> listaSalas) {
        this.desktopPane = desktopPane;
        this.relatorioView = new RelatorioView();
        this.relatorioService = new RelatorioService(listaReservas, listaUsuarios, listaSalas);

        relatorioService.carregarDados();

        desktopPane.add(relatorioView);
        relatorioView.setVisible(true);
        relatorioView.setLocation(
                (desktopPane.getWidth() - relatorioView.getWidth()) / 2,
                (desktopPane.getHeight() - relatorioView.getHeight()) / 2
        );

        configurarAcoes();
    }

    private void configurarAcoes() {
        // Agora passamos o Service para todos os Controllers
        relatorioView.getBtnTotalArrecadado().addActionListener(e ->
                new RelatorioTotalArrecadadoController(desktopPane, relatorioService));

        relatorioView.getBtnSalasMaisReservadas().addActionListener(e ->
                new RelatorioSalasMaisReservadasController(desktopPane, relatorioService));

        relatorioView.getBtnMediaHorasPorCliente().addActionListener(e ->
                new RelatorioMediaHorasPorClienteController(desktopPane, relatorioService));
    }
}
