package Service;

import Dao.UsuarioDao;
import Model.Endereco;
import Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final List<Usuario> cacheUsuarios;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
        this.cacheUsuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(String nome, String cpf, String cep, String rua, String numero, String cidade, String pais, boolean corporativo) throws SQLException {
        Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
        Usuario usuario = new Usuario(nome, cpf, endereco, corporativo);

        cacheUsuarios.add(usuario);
        salvar();
    }

   public void salvar() throws SQLException {
    try {
        usuarioDao.salvar(cacheUsuarios);
        System.out.println("Usuários salvos com sucesso.");
        cacheUsuarios.clear();
    } catch (SQLException e) {
        System.err.println("Erro ao salvar usuários: " + e.getMessage());
        throw e;
    }
}

}