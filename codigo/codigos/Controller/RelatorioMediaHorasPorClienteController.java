package Controller;

import Dao.ReservaDao;
import Model.Usuario;
import View.RelatorioMediaHorasPorClienteView;
import java.util.Map;
import javax.swing.*;

public class RelatorioMediaHorasPorClienteController {

    private RelatorioMediaHorasPorClienteView view;

    public RelatorioMediaHorasPorClienteController(JDesktopPane desktopPane) {
        this.view = new RelatorioMediaHorasPorClienteView();

        desktopPane.add(view);
        view.setVisible(true);

        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        view.getBtnGerar().addActionListener(e -> gerarRelatorio());
    }

    private void gerarRelatorio() {
        try {
            ReservaDao dao = new ReservaDao();
            Map<Usuario, Double> medias = dao.calcularMediaHorasPorCliente();

            if (medias.isEmpty()) {
                view.exibirMensagem("Nenhuma reserva encontrada.");
                return;
            }

            StringBuilder resultado = new StringBuilder();
            for (Map.Entry<Usuario, Double> entry : medias.entrySet()) {
                resultado.append("Cliente: ").append(entry.getKey().getNome())
                         .append(" - Média de horas: ").append(String.format("%.2f", entry.getValue()))
                         .append(" horas\n");
            }

            view.setResultado(resultado.toString());

        } catch (Exception e) {
            view.exibirMensagem("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
