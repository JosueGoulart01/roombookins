package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListarSalasView extends JInternalFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public ListarSalasView() {
        setTitle("Lista de Salas");
        setSize(600, 400);
        setClosable(true);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Código", "Capacidade", "Tipo", "Endereço"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    public void adicionarSala(String codigo, int capacidade, String tipo, String endereco) {
        modelo.addRow(new Object[]{codigo, capacidade, tipo, endereco});
    }

    public void limparTabela() {
        modelo.setRowCount(0);
    }
}