package Dao;

import Model.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Dados {

    public static void leituraArquivo(File arquivoSalas, File arquivoUsuarios, File arquivoReservas,
                                     ArrayList<Sala> listaSalas, ArrayList<Usuario> listaUsuarios,
                                     ArrayList<Reserva> listaReservas) {
        try (
            BufferedReader lerSala = new BufferedReader(new FileReader(arquivoSalas));
            BufferedReader lerUsuario = new BufferedReader(new FileReader(arquivoUsuarios));
            BufferedReader lerReserva = new BufferedReader(new FileReader(arquivoReservas))
        ) {
            String linhaSala;
            while ((linhaSala = lerSala.readLine()) != null) {
                String[] partes = linhaSala.split(";");
                if (partes.length >= 8) {
                    String codigo = partes[0];
                    int capacidade = Integer.parseInt(partes[1]);
                    int tipo = Integer.parseInt(partes[2]);
                    String cep = partes[3];
                    String rua = partes[4];
                    String numero = partes[5];
                    String cidade = partes[6];
                    String pais = partes[7];
                    Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
                    switch (tipo) {
                        case 1 -> listaSalas.add(new SalaPremium(codigo, capacidade, tipo, endereco));
                        case 2 -> listaSalas.add(new SalaVip(codigo, capacidade, tipo, endereco));
                        case 3 -> listaSalas.add(new SalaStandard(codigo, capacidade, tipo, endereco));
                        default -> System.out.println("Tipo de sala desconhecido no arquivo.");
                    }
                }
            }

            String linhaUsuario;
            while ((linhaUsuario = lerUsuario.readLine()) != null) {
                String[] partes = linhaUsuario.split(";");
                if (partes.length == 2) {
                    // Usuário convidado
                    String nome = partes[0];
                    listaUsuarios.add(new Usuario(nome, true));
                } else if (partes.length >= 8) {
                    // Usuário comum ou corporativo
                    String nome = partes[0];
                    String cpf = partes[1];
                    String cep = partes[2];
                    String rua = partes[3];
                    String numero = partes[4];
                    String cidade = partes[5];
                    String pais = partes[6];
                    boolean corporativo = Boolean.parseBoolean(partes[7]);
                    Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
                    listaUsuarios.add(new Usuario(nome, cpf, endereco, corporativo));
                } else {
                    System.out.println("Formato de linha de usuário inválido: " + linhaUsuario);
                }
            }

            String linhaReserva;
            while ((linhaReserva = lerReserva.readLine()) != null) {
                String[] partes = linhaReserva.split(";");
                if (partes.length == 5) {
                    String cpf = partes[1];
                    String codigoSala = partes[2];
                    LocalDateTime dataInicio = LocalDateTime.parse(partes[3]);
                    LocalDateTime dataFim = LocalDateTime.parse(partes[4]);

                    Usuario usuario = null;
                    for (Usuario u : listaUsuarios) {
                        if (cpf.equals(u.getCpf())) {
                            usuario = u;
                            break;
                        }
                    }

                    Sala sala = null;
                    for (Sala s : listaSalas) {
                        if (s.getCodigoSala().equals(codigoSala)) {
                            sala = s;
                            break;
                        }
                    }

                    if (usuario != null && sala != null) {
                        Reserva reserva = new Reserva(usuario, sala, dataInicio, dataFim);
                        listaReservas.add(reserva);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro geral na leitura: " + e.getMessage());
        }
    }

    public static void escritaArquivos(ArrayList<Sala> listaSalas, ArrayList<Usuario> listaUsuarios,
                                       ArrayList<Reserva> listaReservas, File arquivoSalas,
                                       File arquivoUsuarios, File arquivoReservas) throws IOException {

        // Salva salas
        try (BufferedWriter bwSalas = new BufferedWriter(new FileWriter(arquivoSalas))) {
            for (Sala sala : listaSalas) {
                // A classe Sala deve ter o método escritaArquivo() que retorna a linha para salvar
                bwSalas.write(sala.escritaArquivo());
                bwSalas.newLine();
            }
        }

        // Salva usuários
        try (BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter(arquivoUsuarios))) {
            for (Usuario usuario : listaUsuarios) {
                // A classe Usuario deve ter o método escritaArquivo()
                bwUsuarios.write(usuario.escritaArquivo());
                bwUsuarios.newLine();
            }
        }

        // Salva reservas
        try (BufferedWriter bwReservas = new BufferedWriter(new FileWriter(arquivoReservas))) {
            for (Reserva reserva : listaReservas) {
                // A classe Reserva tem método escritaArquivo()
                bwReservas.write(reserva.escritaArquivo());
                bwReservas.newLine();
            }
        }
    }
}
