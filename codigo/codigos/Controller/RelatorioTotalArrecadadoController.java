package Controller;

import Model.Reserva;
import Service.RelatorioService;
import View.RelatorioTotalArrecadadoView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.*;

public class RelatorioTotalArrecadadoController {

    private final RelatorioTotalArrecadadoView view;
    private final RelatorioService service;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioTotalArrecadadoController(JDesktopPane desktopPane, RelatorioService service) {
        this.view = new RelatorioTotalArrecadadoView();
        this.service = service;

        service.carregarDados();  // garante que usuários, salas e reservas já estão carregados

        desktopPane.add(view);
        view.setVisible(true);

        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        view.getBtnGerar().addActionListener(e -> gerarRelatorio());
    }

    private void gerarRelatorio() {
        String dataInicioStr = view.getDataInicioField().getText().trim();
        String dataFimStr = view.getDataFimField().getText().trim();

        if (dataInicioStr.isEmpty() || dataFimStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha as duas datas.");
            return;
        }

        try {
            LocalDateTime inicio = LocalDate.parse(dataInicioStr, FORMATTER).atStartOfDay();
            LocalDateTime fim = LocalDate.parse(dataFimStr, FORMATTER).atTime(23, 59, 59);

            if (inicio.isAfter(fim)) {
                JOptionPane.showMessageDialog(view, "A data de início deve ser anterior à data de fim.");
                return;
            }

            // agora usamos apenas o service
            List<Reserva> reservasFiltradas = service.buscarReservasNoPeriodo(inicio, fim);

            double total = reservasFiltradas.stream()
                    .mapToDouble(Reserva::calcularPreco)
                    .sum();

            view.setResultado(String.format("Total arrecadado: R$ %.2f", total));

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro na conversão das datas. Use o formato dd/MM/yyyy.\nExemplo válido: 25/12/2025");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erro ao gerar relatório: " + ex.getMessage());
        }
    }
}
