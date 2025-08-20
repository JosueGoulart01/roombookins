package Controller;

import Dao.ReservaDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import Service.RelatorioService;
import View.RelatorioTotalArrecadadoView;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioTotalArrecadadoController {

    private final RelatorioTotalArrecadadoView view;
    private final ReservaDao reservaDao;
    private final ArrayList<Reserva> listaReservas;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;
    private final RelatorioService relatorioService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioTotalArrecadadoController(JDesktopPane desktopPane) {
        this.view = new RelatorioTotalArrecadadoView();
        this.listaReservas = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaSalas = new ArrayList<>();
        this.reservaDao = new ReservaDao();
        this.relatorioService = new RelatorioService(listaReservas, listaUsuarios, listaSalas);

        relatorioService.carregarDados();

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

            List<Reserva> reservasFiltradas = reservaDao.buscarReservasNoPeriodo(inicio, fim, listaUsuarios, listaSalas);

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
