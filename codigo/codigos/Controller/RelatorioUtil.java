package Controller;

import javax.swing.*;

import Model.Reserva;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioUtil {

    public static void gerarRelatorioSalasMaisRentaveis(ArrayList<Reserva> reservas) {
        try {
            String inputInicio = JOptionPane.showInputDialog("Digite a data inicial (AAAA-MM-DDTHH:MM):");
            String inputFim = JOptionPane.showInputDialog("Digite a data final (AAAA-MM-DDTHH:MM):");

            LocalDateTime inicio = LocalDateTime.parse(inputInicio);
            LocalDateTime fim = LocalDateTime.parse(inputFim);

            // Filtrar reservas no período
            List<Reserva> reservasNoPeriodo = reservas.stream()
                    .filter(r -> !r.getDataInicio().isBefore(inicio) && !r.getDataFim().isAfter(fim))
                    .collect(Collectors.toList());

            if (reservasNoPeriodo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada no período.");
                return;
            }

            // Mapear arrecadações por sala
            Map<String, Double> arrecadacaoPorSala = new HashMap<>();

            for (Reserva r : reservasNoPeriodo) {
                String codigo = r.getSala().getCodigoSala();
                double valor = r.getSala().calcularPreco(r);
                arrecadacaoPorSala.put(codigo, arrecadacaoPorSala.getOrDefault(codigo, 0.0) + valor);
            }

            // Ordenar por valor arrecadado (decrescente)
            List<Map.Entry<String, Double>> ranking = arrecadacaoPorSala.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors.toList());

            StringBuilder sb = new StringBuilder("Salas que mais arrecadaram:\n\n");
            for (Map.Entry<String, Double> entry : ranking) {
                sb.append("Sala ").append(entry.getKey())
                  .append(" → R$ ").append(String.format("%.2f", entry.getValue()))
                  .append("\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scroll, "Relatório de Arrecadação", JOptionPane.INFORMATION_MESSAGE);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use AAAA-MM-DDTHH:MM");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
