package Dao;

import Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection cn;

    public UsuarioDao(){
        BancoDados.getInstancia();
        cn = BancoDados.getConexao();

    }

    public List<Usuario> carregar() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String comandoCarregar = "SELECT * FROM Usuario";

        try (PreparedStatement ps = cn.prepareStatement(comandoCarregar)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String cep = rs.getString("cep");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String cidade = rs.getString("cidade");
                String pais = rs.getString("pais");
                boolean corporativo = rs.getBoolean("corporativo");

                Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
                Usuario usuario = new Usuario(nome, cpf, endereco, corporativo);

                listaUsuarios.add(usuario);
            }
        }

        return listaUsuarios;
    }

    public void salvar(List<Usuario> listaUsuarios) throws SQLException {
        String comandoSalvar = "INSERT INTO Usuario (cpf, nome, cep, rua, numero, cidade, pais, corporativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(comandoSalvar)) {
            for (Usuario usuario : listaUsuarios) {
                ps.setString(1, usuario.getCpf());
                ps.setString(2, usuario.getNome());
                ps.setString(3, usuario.getEndereco().getCep());
                ps.setString(4, usuario.getEndereco().getRua());
                ps.setString(5, usuario.getEndereco().getNumero());
                ps.setString(6, usuario.getEndereco().getCidade());
                ps.setString(7, usuario.getEndereco().getPais());
                ps.setBoolean(8, usuario.isCorporativo());

                ps.executeUpdate();
            }
        }
    }
}
