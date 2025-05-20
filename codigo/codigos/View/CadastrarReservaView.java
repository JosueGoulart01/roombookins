package View;

import javax.swing.*;

public class CadastrarReservaView extends JInternalFrame {

    private JTextField txtCpfUsuario;
    private JTextField txtCodigoSala;
    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JButton btnSalvar;

    public CadastrarReservaView() {
        super("Cadastrar Reserva", true, true, true, true);
        setSize(400, 250);
        setLayout(null);

        JLabel lblCpfUsuario = new JLabel("CPF Usuário:");
        lblCpfUsuario.setBounds(20, 20, 100, 25);
        add(lblCpfUsuario);
        txtCpfUsuario = new JTextField();
        txtCpfUsuario.setBounds(130, 20, 200, 25);
        add(txtCpfUsuario);

        JLabel lblCodigoSala = new JLabel("Código da Sala:");
        lblCodigoSala.setBounds(20, 60, 100, 25);
        add(lblCodigoSala);
        txtCodigoSala = new JTextField();
        txtCodigoSala.setBounds(130, 60, 200, 25);
        add(txtCodigoSala);

        JLabel lblDataInicio = new JLabel("Data Início (AAAA-MM-DDTHH:MM):");
        lblDataInicio.setBounds(20, 100, 200, 25);
        add(lblDataInicio);
        txtDataInicio = new JTextField();
        txtDataInicio.setBounds(220, 100, 110, 25);
        add(txtDataInicio);

        JLabel lblDataFim = new JLabel("Data Fim (AAAA-MM-DDTHH:MM):");
        lblDataFim.setBounds(20, 140, 200, 25);
        add(lblDataFim);
        txtDataFim = new JTextField();
        txtDataFim.setBounds(220, 140, 110, 25);
        add(txtDataFim);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(140, 180, 100, 30);
        add(btnSalvar);

        setVisible(true);
    }

    public JTextField getTxtCpfUsuario() { return txtCpfUsuario; }
    public JTextField getTxtCodigoSala() { return txtCodigoSala; }
    public JTextField getTxtDataInicio() { return txtDataInicio; }
    public JTextField getTxtDataFim() { return txtDataFim; }
    public JButton getBtnSalvar() { return btnSalvar; }
}
