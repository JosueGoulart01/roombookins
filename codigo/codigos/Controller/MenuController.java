package Controller;

import Dao.ReservaDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.*;
import View.MenuView;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class MenuController {

    private final MenuView menuView;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;
    private final ArrayList<Reserva> listaReservas;

    private final File arquivoSalas;
    private final File arquivoUsuarios;
    private final File arquivoReservas;

    private final SalaDao salaDao;
    private final UsuarioDao usuarioDao;
    private final ReservaDao reservaDao;

    public MenuController() {
        // Inicializa listas
        listaUsuarios = new ArrayList<>();
        listaSalas = new ArrayList<>();
        listaReservas = new ArrayList<>();

        // Arquivos
        arquivoSalas = new File("codigo//codigos//Dao//arquivos//sala.txt");
        arquivoUsuarios = new File("codigo//codigos//Dao//arquivos//usuarios.txt");
        arquivoReservas = new File("codigo//codigos//Dao//arquivos//reservas.txt");

        // DAOs
        salaDao = new SalaDao();
        usuarioDao = new UsuarioDao();
        reservaDao = new ReservaDao();

        // Carrega dados dos arquivos
        carregarDados();

        // Inicializa a view
        menuView = new MenuView();

        // Configura listeners para os botões da interface
        configurarListeners();

        // Mostra a interface
        menuView.setVisible(true);
    }

    private void carregarDados() {
        try {
            listaSalas.addAll(salaDao.carregar(arquivoSalas));
            listaUsuarios.addAll(usuarioDao.carregar(arquivoUsuarios));
            listaReservas.addAll(reservaDao.carregar(arquivoReservas, listaUsuarios, listaSalas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao carregar dados: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void configurarListeners() {
        menuView.getBtnCadastrarUsuario().addActionListener(e ->
            new CadastrarUsuarioController(menuView.getTela())
        );

        menuView.getBtnCadastrarReserva().addActionListener(e ->
            new CadastrarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas,
                    arquivoSalas, arquivoUsuarios, arquivoReservas)
        );

        menuView.getBtnCadastrarSala().addActionListener(e ->
            new CadastrarSalaController(menuView.getTela(), listaSalas, listaUsuarios, listaReservas,
                    arquivoSalas, arquivoUsuarios, arquivoReservas)
        );

        menuView.getBtnCancelarReserva().addActionListener(e ->
            new CancelarReservaController(menuView.getTela(), listaUsuarios, listaSalas, listaReservas,
                    arquivoSalas, arquivoUsuarios, arquivoReservas)
        );

        menuView.getBtnListarReservas().addActionListener(e ->
            new ListarReservasController(menuView.getTela(), listaReservas)
        );

        menuView.getBtnGerarRelatorio().addActionListener(e -> 
            new RelatorioController(menuView.getTela(), listaReservas)
        );
    }
}
