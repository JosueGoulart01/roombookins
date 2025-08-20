package View;

import java.awt.*;
import javax.swing.*;

public class RelatorioMediaHorasPorClienteView extends JInternalFrame {

    private JLabel tituloLabel;
    private JButton btnGerar;
    private JTextArea resultadoArea;
    private JScrollPane scrollPane;

    public RelatorioMediaHorasPorClienteView() {
        setTitle("Relatório - Média de Horas por Cliente");
        setClosable(true);
        setIconifiable(true);
        setResizable(true); // Agora redimensionável
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(null);

        // Título
        tituloLabel = new JLabel("Média de Horas Reservadas por Cliente");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tituloLabel.setBounds(100, 20, 500, 30);
        add(tituloLabel);

        // Botão
        btnGerar = new JButton("Gerar Relatório");
        btnGerar.setBounds(200, 80, 180, 35);
        btnGerar.setBackground(new Color(0, 122, 204));
        btnGerar.setForeground(Color.WHITE);
        btnGerar.setFocusPainted(false);
        add(btnGerar);

        // Área de resultado com rolagem
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBounds(50, 140, 480, 280);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(scrollPane);
    }

    // Getters para o Controller
    public JButton getBtnGerar() {
        return btnGerar;
    }

    public void setResultado(String texto) {
        resultadoArea.setText(texto);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
