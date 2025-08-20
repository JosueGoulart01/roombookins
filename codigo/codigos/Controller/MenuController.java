package Controller;

import Model.*;
import View.MenuView;
import java.util.ArrayList;

public class MenuController {

    private final MenuView menuView;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;
    private final ArrayList<Reserva> listaReservas;

    public MenuController() {
        listaUsuarios = new ArrayList<>();
        listaSalas = new ArrayList<>();
        listaReservas = new ArrayList<>();

        menuView = new MenuView();
        configurarListeners();
        menuView.setVisible(true);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver JDBC carregado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC NÃO foi encontrado!");
            e.printStackTrace();
        }
    }

    private void configurarListeners() {
        menuView.getBtnCadastrarUsuario().addActionListener(e ->
            new CadastrarUsuarioController(menuView.getTela())
        );

        menuView.getBtnCadastrarReserva().addActionListener(e ->
            new CadastrarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas)
        );

        menuView.getBtnCadastrarSala().addActionListener(e ->
            new CadastrarSalaController(menuView.getTela(), listaSalas, listaUsuarios, listaReservas)
        );

        menuView.getBtnCancelarReserva().addActionListener(e ->
            new CancelarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas)
        );

        menuView.getBtnListarReservas().addActionListener(e ->
            new ListarReservasController(menuView.getTela(), listaReservas, listaUsuarios, listaSalas)
        );

        menuView.getBtnListarUsuarios().addActionListener(e ->
            new ListarUsuariosController(menuView.getTela(), listaUsuarios)
        );

        menuView.getBtnListarSalas().addActionListener(e ->
            new ListarSalasController(menuView.getTela(), listaSalas)
        );

        menuView.getBtnGerarRelatorio().addActionListener(e ->
            new RelatorioController(menuView.getTela(), listaReservas, listaUsuarios, listaSalas)
        );
    }
}
