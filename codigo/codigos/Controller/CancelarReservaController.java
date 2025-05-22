package Controller;

import Dao.ReservaDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import View.CancelarReservaView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class CancelarReservaController {

    private CancelarReservaView cancelarReservaView;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Sala> listaSalas;
    private ArrayList<Reserva> listaReservas;
    private File arquivoSalas, arquivoUsuarios, arquivoReservas;

    // DAO de reservas para salvar após cancelamento
    private ReservaDao reservaDao;

    public CancelarReservaController(JDesktopPane desktopPane,
                                    ArrayList<Usuario> listaUsuarios,
                                    ArrayList<Sala> listaSalas,
                                    ArrayList<Reserva> listaReservas,
                                    File arquivoSalas,
                                    File arquivoUsuarios,
                                    File arquivoReservas) {
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
        this.listaReservas = listaReservas;
        this.arquivoSalas = arquivoSalas;
        this.arquivoUsuarios = arquivoUsuarios;
        this.arquivoReservas = arquivoReservas;

        this.reservaDao = new ReservaDao();

        cancelarReservaView = new CancelarReservaView();
        desktopPane.add(cancelarReservaView);
        cancelarReservaView.setVisible(true);
        cancelarReservaView.setLocation(
            (desktopPane.getWidth() - cancelarReservaView.getWidth()) / 2,
            (desktopPane.getHeight() - cancelarReservaView.getHeight()) / 2
        );

        cancelarReservaView.getBtnCancelar().addActionListener(e -> cancelarReserva());
    }

    private void cancelarReserva() {
        String cpf = cancelarReservaView.getCpf();
        String codigoSala = cancelarReservaView.getCodigoSala();

        if (cpf.isEmpty() || codigoSala.isEmpty()) {
            JOptionPane.showMessageDialog(cancelarReservaView, "Informe CPF e Código da Sala.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Reserva reservaEncontrada = null;
        for (Reserva r : listaReservas) {
            if (r.getUsuario().getCpf().equals(cpf) && r.getSala().getCodigoSala().equals(codigoSala)) {
                reservaEncontrada = r;
                break;
            }
        }

        if (reservaEncontrada == null) {
            JOptionPane.showMessageDialog(cancelarReservaView, "Nenhuma reserva encontrada para o CPF e código da sala informados.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double estorno = reservaEncontrada.removeReserva(listaReservas);

        try {
            // Usa DAO para salvar só as reservas, pois só elas mudaram
            reservaDao.salvar(listaReservas, arquivoReservas);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(cancelarReservaView, "Erro ao salvar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(cancelarReservaView, "Reserva cancelada com sucesso!\nValor do estorno: R$ " + estorno);
        cancelarReservaView.dispose();
    }
}
