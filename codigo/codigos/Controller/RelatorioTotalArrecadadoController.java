package Controller;

import Model.Reserva;
import View.RelatorioTotalArrecadadoView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;

public class RelatorioTotalArrecadadoController {

    private RelatorioTotalArrecadadoView view;
    private List<Reserva> listaReservas;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioTotalArrecadadoController(JDesktopPane desktopPane, List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
        this.view = new RelatorioTotalArrecadadoView();

        desktopPane.add(view);
        view.setVisible(true);

        // Centralizar a janela dentro do desktopPane
        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        // Associar ação do botão
        view.getBtnGerar().addActionListener(e -> gerarRelatorio());
    }

    private void gerarRelatorio() {
        try {
            String dataInicioStr = view.getDataInicioField().getText().trim();
            String dataFimStr = view.getDataFimField().getText().trim();

            if (dataInicioStr.isEmpty() || dataFimStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Por favor, preencha as duas datas.");
                return;
            }

            LocalDateTime inicio = LocalDate.parse(dataInicioStr, FORMATTER).atStartOfDay();
            LocalDateTime fim = LocalDate.parse(dataFimStr, FORMATTER).atTime(23, 59, 59);

            // Filtra reservas que tenham interseção com o período [inicio, fim]
            double total = listaReservas.stream()
                    .filter(r -> !(r.getDataFim().isBefore(inicio) || r.getDataInicio().isAfter(fim)))
                    .mapToDouble(Reserva::calcularPreco)
                    .sum();

            view.setResultado(String.format("Total arrecadado: R$ %.2f", total));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erro na conversão das datas. Use o formato dd/MM/yyyy.");
        }
    }
}
