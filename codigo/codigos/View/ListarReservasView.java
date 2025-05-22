package View;

import Model.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarReservasView extends JInternalFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public ListarReservasView(List<Reserva> listaReservas) {
        setTitle("Lista de Reservas");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(600, 400);

        String[] colunas = {"Nome", "CPF", "Sala", "Início", "Fim"};
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);
        tabela.setEnabled(false);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll);

        for (Reserva r : listaReservas) {
            Object[] linha = {
                r.getUsuario().getNome(),
                r.getUsuario().getCpf(),
                r.getSala().getCodigoSala(),
                r.getDataInicio(),
                r.getDataFim()
            };
            modelo.addRow(linha);
        }
    }
}
