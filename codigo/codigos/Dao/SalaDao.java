package Dao;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDao {

    public List<Sala> carregar(File arquivoSalas) throws Exception {
        List<Sala> listaSalas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoSalas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Sala sala = parseLinhaSala(linha);
                if (sala != null) listaSalas.add(sala);
            }
        }
        return listaSalas;
    }

    public void salvar(List<Sala> listaSalas, File arquivoSalas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSalas, false))) { // false para sobrescrever
            for (Sala sala : listaSalas) {
                bw.write(sala.escritaArquivo());
                bw.newLine();
            }
        }
    }

    private Sala parseLinhaSala(String linha) throws Exception {
        String[] partes = linha.split(";");
        if (partes.length < 8) return null;

        String codigo = partes[0];
        int capacidade = Integer.parseInt(partes[1]);
        int tipo = Integer.parseInt(partes[2]);
        Endereco endereco = new Endereco(partes[3], partes[4], partes[5], partes[6], partes[7]);

        switch (tipo) {
            case 1:
                return new SalaPremium(codigo, capacidade, tipo, endereco);
            case 2:
                return new SalaVip(codigo, capacidade, tipo, endereco);
            case 3:
                return new SalaStandard(codigo, capacidade, tipo, endereco);
            default:
                return null;
        }
    }
}
