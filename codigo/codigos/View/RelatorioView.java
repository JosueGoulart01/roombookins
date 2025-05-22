package View;

import javax.swing.*;

public class RelatorioView extends JInternalFrame {

    private JButton btnTotalArrecadado;
    private JButton btnSalasMaisReservadas;
    private JButton btnMediaHorasPorCliente;

    public RelatorioView() {
        setTitle("Relatórios");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        btnTotalArrecadado = new JButton("Total arrecadado por período");
        btnTotalArrecadado.setBounds(50, 30, 300, 40);
        add(btnTotalArrecadado);

        btnSalasMaisReservadas = new JButton("Salas mais reservadas no mês");
        btnSalasMaisReservadas.setBounds(50, 90, 300, 40);
        add(btnSalasMaisReservadas);

        btnMediaHorasPorCliente = new JButton("Média de horas reservadas por cliente");
        btnMediaHorasPorCliente.setBounds(50, 150, 300, 40);
        add(btnMediaHorasPorCliente);
    }

    public JButton getBtnTotalArrecadado() {
        return btnTotalArrecadado;
    }

    public JButton getBtnSalasMaisReservadas() {
        return btnSalasMaisReservadas;
    }

    public JButton getBtnMediaHorasPorCliente() {
        return btnMediaHorasPorCliente;
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
