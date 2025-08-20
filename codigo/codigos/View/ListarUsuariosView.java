package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListarUsuariosView extends JInternalFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public ListarUsuariosView() {
        setTitle("Lista de Usuários");
        setSize(700, 400);
        setClosable(true);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "Endereço", "Corporativo"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    public void adicionarUsuario(String nome, String cpf, String endereco, boolean corporativo) {
        modelo.addRow(new Object[]{nome, cpf, endereco, corporativo ? "Sim" : "Não"});
    }

    public void limparTabela() {
        modelo.setRowCount(0);
    }
}