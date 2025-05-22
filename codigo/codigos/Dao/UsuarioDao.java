package Dao;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    public List<Usuario> carregar(File arquivoUsuarios) throws IOException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Usuario u = parseLinhaUsuario(linha);
                if (u != null) listaUsuarios.add(u);
            }
        }
        return listaUsuarios;
    }

    public void salvar(List<Usuario> listaUsuarios, File arquivoUsuarios) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoUsuarios, false))) { // false para sobrescrever
            for (Usuario usuario : listaUsuarios) {
                bw.write(usuario.escritaArquivo());
                bw.newLine();
            }
        }
    }

    private Usuario parseLinhaUsuario(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 2) {
            return new Usuario(partes[0], true); // convidado
        } else if (partes.length >= 8) {
            Endereco endereco = new Endereco(partes[2], partes[3], partes[4], partes[5], partes[6]);
            boolean corporativo = Boolean.parseBoolean(partes[7]);
            return new Usuario(partes[0], partes[1], endereco, corporativo);
        }
        return null;
    }
}
