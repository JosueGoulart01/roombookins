package View;

import javax.swing.*;

public class MenuView extends javax.swing.JFrame {

    private JMenuItem btnCadastrarUsuario;
    private JMenuItem btnCadastrarReserva;
    private JMenuItem btnCadastrarSala;
    private JMenuItem btnCancelarReserva;
    private JMenuItem btnListarReservas;
    private JMenuItem btnGerarRelatorio;  // novo botão
    private JDesktopPane jDesktopPane1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenuBar jMenuBar2;
    private JMenu tituloMenu;
    private JMenu jMenu4;

    public MenuView() {
        initComponents();
    }

    private void initComponents() {
        jDesktopPane1 = new JDesktopPane();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jMenuBar2 = new JMenuBar();
        tituloMenu = new JMenu();
        btnCadastrarUsuario = new JMenuItem();
        btnCadastrarReserva = new JMenuItem();
        btnCadastrarSala = new JMenuItem();
        btnCancelarReserva = new JMenuItem();
        btnListarReservas = new JMenuItem();
        btnGerarRelatorio = new JMenuItem();  // inicialização do novo botão
        jMenu4 = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("RoomBookings");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36));
        jLabel2.setText("RoomBookings");

        jDesktopPane1.setLayer(jLabel1, JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, JLayeredPane.DEFAULT_LAYER);

        GroupLayout jDesktopPane1Layout = new GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap(207, Short.MAX_VALUE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                    .addGap(147))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(146)
                    .addComponent(jLabel2)
                    .addContainerGap(218, Short.MAX_VALUE))
        );

        tituloMenu.setText("Menu");

        btnCadastrarUsuario.setText("Cadastrar Usuário");
        tituloMenu.add(btnCadastrarUsuario);

        btnCadastrarReserva.setText("Cadastrar Reserva");
        tituloMenu.add(btnCadastrarReserva);

        btnCadastrarSala.setText("Cadastrar Sala");
        tituloMenu.add(btnCadastrarSala);

        btnCancelarReserva.setText("Cancelar Reserva");
        tituloMenu.add(btnCancelarReserva);

        btnListarReservas.setText("Listar Reservas");
        tituloMenu.add(btnListarReservas);

        btnGerarRelatorio.setText("Gerar Relatório");  // texto do novo botão
        tituloMenu.add(btnGerarRelatorio);  // adiciona ao menu

        jMenuBar2.add(tituloMenu);
        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MenuView().setVisible(true));
    }

    // Getters para o controller acessar
    public JMenuItem getBtnCadastrarUsuario() {
        return btnCadastrarUsuario;
    }

    public JMenuItem getBtnCadastrarReserva() {
        return btnCadastrarReserva;
    }

    public JMenuItem getBtnCadastrarSala() {
        return btnCadastrarSala;
    }

    public JMenuItem getBtnCancelarReserva() {
        return btnCancelarReserva;
    }

    public JMenuItem getBtnListarReservas() {
        return btnListarReservas;
    }

    public JMenuItem getBtnGerarRelatorio() {  // getter do novo botão
        return btnGerarRelatorio;
    }

    public JDesktopPane getTela() {
        return jDesktopPane1;
    }
}
