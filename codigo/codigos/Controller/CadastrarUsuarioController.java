package Controller;

import Dao.UsuarioDao;
import Model.Endereco;
import Model.Usuario;
import View.CadastrarUsuarioView;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class CadastrarUsuarioController {
    private final CadastrarUsuarioView cadastrarUsuario;
    private ArrayList<Usuario> listaUsuarios;
    private final String caminhosUsuarios = "codigo//codigos//Dao//arquivos//usuarios.txt";
    private final File arquivoUsuarios = new File(caminhosUsuarios);
    private final UsuarioDao usuarioDao;

    public CadastrarUsuarioController(JDesktopPane tela) {
        this.cadastrarUsuario = new CadastrarUsuarioView();
        tela.add(cadastrarUsuario);

        int x = (tela.getWidth() - cadastrarUsuario.getWidth()) / 2;
        int y = (tela.getHeight() - cadastrarUsuario.getHeight()) / 2;
        cadastrarUsuario.setLocation(x, y);

        this.usuarioDao = new UsuarioDao();

        cadastrarUsuario.setVisible(true);

        cadastrarUsuario.getBtnSalvar().addActionListener(e -> {
            try {
                // Carrega os usuários existentes para manter os dados antigos
                listaUsuarios = (ArrayList<Usuario>) usuarioDao.carregar(arquivoUsuarios);
            } catch (Exception ex) {
                // Se não conseguir carregar, inicializa lista vazia para não travar
                listaUsuarios = new ArrayList<>();
            }

            String nome = cadastrarUsuario.getLabelNome();
            String cpf = cadastrarUsuario.getLabelCpf();
            String cep = cadastrarUsuario.getLabelCep();
            String rua = cadastrarUsuario.getLabelRua();
            String numero = cadastrarUsuario.getLabelNumero();
            String cidade = cadastrarUsuario.getLabelCidade();
            String pais = cadastrarUsuario.getLabelPais();

            Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
            Usuario usuario = new Usuario(nome, cpf, endereco, false);

            listaUsuarios.add(usuario);

            try {
                usuarioDao.salvar(listaUsuarios, arquivoUsuarios);
                JOptionPane.showMessageDialog(cadastrarUsuario, "Cadastro salvo com sucesso!");
                cadastrarUsuario.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(cadastrarUsuario, "Erro ao salvar cadastro: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
