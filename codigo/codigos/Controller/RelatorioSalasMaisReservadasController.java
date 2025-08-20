package Controller;

import Service.RelatorioService;
import View.RelatorioSalasMaisReservadasView;
import java.util.Map;
import javax.swing.*;

public class RelatorioSalasMaisReservadasController {

    private final RelatorioSalasMaisReservadasView view;
    private final RelatorioService service;

    public RelatorioSalasMaisReservadasController(JDesktopPane desktopPane, RelatorioService service) {
        this.view = new RelatorioSalasMaisReservadasView();
        this.service = service;

        desktopPane.add(view);
        view.setVisible(true);

        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        view.getBtnGerar().addActionListener(e -> gerarRelatorio());
    }

    private void gerarRelatorio() {
        try {
            int mesSelecionado = view.getMesSelecionado();
            int anoSelecionado = view.getAnoSelecionado();

            Map<String, Long> reservasPorSala = service.obterReservasPorSala(mesSelecionado, anoSelecionado);

            if (reservasPorSala.isEmpty()) {
                view.setResultado(new String[0][0]);
                return;
            }

            String[][] dados = reservasPorSala.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .map(e -> new String[]{e.getKey(), e.getValue() + " reservas"})
                    .toArray(String[][]::new);

            view.setResultado(dados);

        } catch (Exception e) {
            view.exibirMensagem("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
