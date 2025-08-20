package View;

import java.awt.*;
import javax.swing.*;

public class RelatorioTotalArrecadadoView extends JInternalFrame {

    private JLabel tituloLabel;
    private JLabel dataInicioLabel;
    private JLabel dataFimLabel;
    private JLabel resultadoLabel;

    private JTextField dataInicioField;
    private JTextField dataFimField;

    private JButton btnGerar;

    public RelatorioTotalArrecadadoView() {
        setTitle("Relatório - Total Arrecadado");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(null);

        tituloLabel = new JLabel("Total Arrecadado por Período");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tituloLabel.setBounds(80, 20, 400, 30);
        add(tituloLabel);

        dataInicioLabel = new JLabel("Data Início (dd/mm/aaaa):");
        dataInicioLabel.setBounds(50, 80, 180, 25);
        add(dataInicioLabel);

        dataInicioField = new JTextField();
        dataInicioField.setBounds(250, 80, 180, 25);
        add(dataInicioField);

        dataFimLabel = new JLabel("Data Fim (dd/mm/aaaa):");
        dataFimLabel.setBounds(50, 130, 180, 25);
        add(dataFimLabel);

        dataFimField = new JTextField();
        dataFimField.setBounds(250, 130, 180, 25);
        add(dataFimField);

        btnGerar = new JButton("Gerar Relatório");
        btnGerar.setBounds(150, 190, 180, 35);
        add(btnGerar);

        resultadoLabel = new JLabel("Total arrecadado: R$ 0,00");
        resultadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultadoLabel.setForeground(new Color(0, 128, 0));
        resultadoLabel.setBounds(100, 260, 400, 30);
        add(resultadoLabel);
    }
    
    // Getters para o Controller acessar
    public JTextField getDataInicioField() {
        return dataInicioField;
    }

    public JTextField getDataFimField() {
        return dataFimField;
    }

    public JButton getBtnGerar() {
        return btnGerar;
    }

    public void setResultado(String texto) {
        resultadoLabel.setText(texto);
    }
}
