package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RelatorioSalasMaisReservadasView extends JInternalFrame {

    private JComboBox<String> comboBoxMes;
    private JTextField textFieldAno;
    private JButton btnGerar;
    private JTable tabelaResultado;
    private DefaultTableModel modeloTabela;

    public RelatorioSalasMaisReservadasView() {
        setTitle("Relatório: Salas Mais Reservadas");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setSize(450, 400);
        setLayout(null);

        JLabel labelMes = new JLabel("Selecione o mês:");
        labelMes.setBounds(20, 20, 120, 25);
        add(labelMes);

        comboBoxMes = new JComboBox<>(new String[]{
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        });
        comboBoxMes.setBounds(150, 20, 250, 25);
        add(comboBoxMes);

        JLabel labelAno = new JLabel("Informe o ano:");
        labelAno.setBounds(20, 60, 120, 25);
        add(labelAno);

        textFieldAno = new JTextField(String.valueOf(java.time.LocalDate.now().getYear()));
        textFieldAno.setBounds(150, 60, 250, 25);
        add(textFieldAno);

        btnGerar = new JButton("Gerar Relatório");
        btnGerar.setBounds(150, 100, 150, 30);
        add(btnGerar);

        modeloTabela = new DefaultTableModel(new Object[]{"Sala", "Total de Reservas"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // impede edição
            }
        };

        tabelaResultado = new JTable(modeloTabela);
        tabelaResultado.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabelaResultado);
        scrollPane.setBounds(20, 150, 380, 190);
        add(scrollPane);
    }

    public int getMesSelecionado() {
        return comboBoxMes.getSelectedIndex() + 1; // Janeiro = 1
    }

    public int getAnoSelecionado() {
        try {
            return Integer.parseInt(textFieldAno.getText());
        } catch (NumberFormatException e) {
            return java.time.LocalDate.now().getYear();
        }
    }

    public JButton getBtnGerar() {
        return btnGerar;
    }

    public void setResultado(String[][] dados) {
        modeloTabela.setRowCount(0);

        if (dados == null || dados.length == 0) {
            modeloTabela.addRow(new Object[]{"Nenhuma sala encontrada para o período selecionado.", ""});
        } else {
            for (String[] linha : dados) {
                modeloTabela.addRow(linha);
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
