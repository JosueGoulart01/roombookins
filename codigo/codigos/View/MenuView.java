package View;

import javax.swing.*;

public class MenuView extends JFrame {

    private JMenuItem btnCadastrarUsuario;
    private JMenuItem btnCadastrarReserva;
    private JMenuItem btnCadastrarSala;
    private JMenuItem btnCancelarReserva;
    private JMenuItem btnListarReservas;
    private JMenuItem btnGerarRelatorio;
    private JDesktopPane desktopPane;
    private JLabel tituloLabel;

    public MenuView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("RoomBookings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();

        tituloLabel = new JLabel("RoomBookings");
        tituloLabel.setFont(new java.awt.Font("Segoe UI", 0, 36));
        tituloLabel.setBounds(300, 100, 300, 50);
        desktopPane.add(tituloLabel);

        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        btnCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        btnCadastrarReserva = new JMenuItem("Cadastrar Reserva");
        btnCadastrarSala = new JMenuItem("Cadastrar Sala");
        btnCancelarReserva = new JMenuItem("Cancelar Reserva");
        btnListarReservas = new JMenuItem("Listar Reservas");
        btnGerarRelatorio = new JMenuItem("Gerar Relatório");

        menu.add(btnCadastrarUsuario);
        menu.add(btnCadastrarReserva);
        menu.add(btnCadastrarSala);
        menu.add(btnCancelarReserva);
        menu.add(btnListarReservas);
        menu.add(btnGerarRelatorio);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // Getters para o controller
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

    public JMenuItem getBtnGerarRelatorio() {
        return btnGerarRelatorio;
    }

    public JDesktopPane getTela() {
        return desktopPane;
    }

}
