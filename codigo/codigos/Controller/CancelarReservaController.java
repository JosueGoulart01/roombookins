package Controller;

import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import Service.CancelarReservaService;
import View.CancelarReservaView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CancelarReservaController {

    private final CancelarReservaView view;
    private final CancelarReservaService service;
    private final HashMap<String, Reserva> mapaReservas = new HashMap<>();

    public CancelarReservaController(JDesktopPane desktopPane,
                                     ArrayList<Usuario> listaUsuarios,
                                     ArrayList<Sala> listaSalas,
                                     ArrayList<Reserva> listaReservas) {
        this.view = new CancelarReservaView();
        this.service = new CancelarReservaService(listaUsuarios, listaSalas, listaReservas);

        service.carregarDados();

        desktopPane.add(view);
        view.setVisible(true);
        view.setLocation(
                (desktopPane.getWidth() - view.getWidth()) / 2,
                (desktopPane.getHeight() - view.getHeight()) / 2
        );

        view.getBtnBuscar().addActionListener(e -> {
            String cpf = view.getCpf();
            ArrayList<Reserva> reservasUsuario = service.buscarReservasPorCpf(cpf);
            DefaultListModel<String> modelo = new DefaultListModel<>();
            mapaReservas.clear();

            for (Reserva r : reservasUsuario) {
                String chave = r.getSala().getCodigoSala() + " - " + r.getDataInicio();
                modelo.addElement(chave);
                mapaReservas.put(chave, r);
            }

            view.atualizarListaReservas(modelo);

            if (modelo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nenhuma reserva encontrada para o CPF informado.");
            }
        });

        view.getBtnCancelar().addActionListener(e -> {
            String selecionada = view.getReservaSelecionada();
            if (selecionada == null) {
                JOptionPane.showMessageDialog(view, "Selecione uma reserva para cancelar.");
                return;
            }

            Reserva reserva = mapaReservas.get(selecionada);
            double estorno = service.cancelarReserva(reserva);
            if (estorno >= 0) {
                JOptionPane.showMessageDialog(view,
                        String.format("Reserva cancelada!\nEstorno: R$ %.2f", estorno));
                view.getBtnBuscar().doClick(); // Atualiza a lista
            } else {
                JOptionPane.showMessageDialog(view,
                        "Erro ao cancelar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
