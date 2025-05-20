package View;

import java.awt.*;
import javax.swing.*;

public class CancelarReservaView extends JInternalFrame {

    private JTextField txtCpf;
    private JTextField txtCodigoSala;
    private JButton btnCancelar;

    public CancelarReservaView() {
        super("Cancelar Reserva", true, true, true, true);
        setSize(400, 200);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCpf = new JLabel("CPF do Usuário:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblCpf, gbc);

        txtCpf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtCpf, gbc);

        JLabel lblCodigoSala = new JLabel("Código da Sala:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblCodigoSala, gbc);

        txtCodigoSala = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtCodigoSala, gbc);

        btnCancelar = new JButton("Cancelar Reserva");
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnCancelar, gbc);
    }

    public String getCpf() {
        return txtCpf.getText().trim();
    }

    public String getCodigoSala() {
        return txtCodigoSala.getText().trim();
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}
