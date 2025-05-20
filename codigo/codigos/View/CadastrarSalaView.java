//View para cadastrar sala
package View;

import javax.swing.*;

public class CadastrarSalaView extends JInternalFrame {

    private JTextField txtCodigoSala, txtCapacidade, txtCep, txtRua, txtNumero, txtCidade, txtPais;
    private JComboBox<String> comboTipo;
    private JButton btnSalvar;

    public CadastrarSalaView() {
        super("Cadastrar Sala", true, true, true, true);
        setSize(400, 400);
        setLayout(null);

        JLabel lblCodigoSala = new JLabel("Código da Sala:");
        lblCodigoSala.setBounds(20, 20, 100, 25);
        add(lblCodigoSala);
        txtCodigoSala = new JTextField();
        txtCodigoSala.setBounds(130, 20, 200, 25);
        add(txtCodigoSala);

        JLabel lblCapacidade = new JLabel("Capacidade:");
        lblCapacidade.setBounds(20, 60, 100, 25);
        add(lblCapacidade);
        txtCapacidade = new JTextField();
        txtCapacidade.setBounds(130, 60, 200, 25);
        add(txtCapacidade);

        JLabel lblTipo = new JLabel("Tipo da Sala:");
        lblTipo.setBounds(20, 100, 100, 25);
        add(lblTipo);
        comboTipo = new JComboBox<>(new String[]{"Premium", "Vip", "Standard"});
        comboTipo.setBounds(130, 100, 200, 25);
        add(comboTipo);

        JLabel lblCep = new JLabel("CEP:");
        lblCep.setBounds(20, 140, 100, 25);
        add(lblCep);
        txtCep = new JTextField();
        txtCep.setBounds(130, 140, 200, 25);
        add(txtCep);

        JLabel lblRua = new JLabel("Rua:");
        lblRua.setBounds(20, 180, 100, 25);
        add(lblRua);
        txtRua = new JTextField();
        txtRua.setBounds(130, 180, 200, 25);
        add(txtRua);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(20, 220, 100, 25);
        add(lblNumero);
        txtNumero = new JTextField();
        txtNumero.setBounds(130, 220, 200, 25);
        add(txtNumero);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(20, 260, 100, 25);
        add(lblCidade);
        txtCidade = new JTextField();
        txtCidade.setBounds(130, 260, 200, 25);
        add(txtCidade);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(20, 300, 100, 25);
        add(lblPais);
        txtPais = new JTextField();
        txtPais.setBounds(130, 300, 200, 25);
        add(txtPais);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 340, 100, 30);
        add(btnSalvar);

        setVisible(true);
    }

    public JTextField getTxtCodigoSala() { return txtCodigoSala; }
    public JTextField getTxtCapacidade() { return txtCapacidade; }
    public JComboBox<String> getComboTipo() { return comboTipo; }
    public JTextField getTxtCep() { return txtCep; }
    public JTextField getTxtRua() { return txtRua; }
    public JTextField getTxtNumero() { return txtNumero; }
    public JTextField getTxtCidade() { return txtCidade; }
    public JTextField getTxtPais() { return txtPais; }
    public JButton getBtnSalvar() { return btnSalvar; }
}
