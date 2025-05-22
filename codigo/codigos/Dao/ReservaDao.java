package Dao;

import Model.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {

    public List<Reserva> carregar(File arquivoReservas, List<Usuario> listaUsuarios, List<Sala> listaSalas) throws IOException {
        List<Reserva> listaReservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoReservas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Reserva r = parseLinhaReserva(linha, listaUsuarios, listaSalas);
                if (r != null) listaReservas.add(r);
            }
        }
        return listaReservas;
    }

    public void salvar(List<Reserva> listaReservas, File arquivoReservas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoReservas, false))) { // false para sobrescrever
            for (Reserva reserva : listaReservas) {
                bw.write(reserva.escritaArquivo());
                bw.newLine();
            }
        }
    }

    private Reserva parseLinhaReserva(String linha, List<Usuario> usuarios, List<Sala> salas) {
        String[] partes = linha.split(";");
        if (partes.length < 5) return null;

        String cpf = partes[1];
        String codigoSala = partes[2];
        LocalDateTime dataInicio = LocalDateTime.parse(partes[3]);
        LocalDateTime dataFim = LocalDateTime.parse(partes[4]);

        Usuario usuario = usuarios.stream()
            .filter(u -> cpf.equals(u.getCpf()))
            .findFirst().orElse(null);

        Sala sala = salas.stream()
            .filter(s -> codigoSala.equals(s.getCodigoSala()))
            .findFirst().orElse(null);

        if (usuario != null && sala != null) {
            return new Reserva(usuario, sala, dataInicio, dataFim);
        }
        return null;
    }
}
