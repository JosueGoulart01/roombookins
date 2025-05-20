package View;

import Controller.MenuController;
import Dao.Dados;
import Model.*;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
    System.out.println("Iniciando aplicação...");

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        ArrayList<Sala> listaSalas = new ArrayList<>();
        ArrayList<Reserva> listaReservas = new ArrayList<>();

        File arquivoSalas = new File("codigo//codigos//Dao//arquivos//sala.txt");
        File arquivoUsuarios = new File("codigo//codigos//Dao//arquivos//usuarios.txt");
        File arquivoReservas = new File("codigo//codigos//Dao//arquivos//reservas.txt");

        System.out.println("Arquivos definidos.");

        try {
            Dados.leituraArquivo(arquivoSalas, arquivoUsuarios, arquivoReservas,
                                           listaSalas, listaUsuarios, listaReservas);
            System.out.println("Leitura concluída.");
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivos: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Abrindo Menu...");
        new MenuController(listaUsuarios, listaSalas, listaReservas,
                           arquivoSalas, arquivoUsuarios, arquivoReservas);
    }
}
