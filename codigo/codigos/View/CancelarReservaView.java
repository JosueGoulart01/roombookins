package View;

import java.awt.*;
import javax.swing.*;

public class CancelarReservaView extends JInternalFrame {

    private JTextField txtCpf;
    private JButton btnBuscar;
    private JButton btnCancelar;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaReservas;

    public CancelarReservaView() {
        super("Cancelar Reserva", true, true, true, true);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo CPF
        JLabel lblCpf = new JLabel("CPF do Usuário:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblCpf, gbc);

        txtCpf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtCpf, gbc);

        btnBuscar = new JButton("Buscar Reservas");
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(btnBuscar, gbc);

        // Lista de reservas
        modeloLista = new DefaultListModel<>();
        listaReservas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Botão cancelar
        btnCancelar = new JButton("Cancelar Reserva Selecionada");
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        add(btnCancelar, gbc);
    }

    public String getCpf() {
        return txtCpf.getText().trim();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void atualizarListaReservas(DefaultListModel<String> reservas) {
        modeloLista.clear();
        for (int i = 0; i < reservas.size(); i++) {
            modeloLista.addElement(reservas.get(i));
        }
    }

    public String getReservaSelecionada() {
        return listaReservas.getSelectedValue();
    }
}
