    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package Controller;

    import Model.Endereco;
    import Model.Usuario;
    import View.CadastrarUsuarioView;
    import java.util.ArrayList;
    import javax.swing.JDesktopPane;

    /**
     *
     * @author 1529017
     */
    public class CadastrarUsuarioController {
       private CadastrarUsuarioView cadastrarUsuario;
       private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
       String caminhosUsuarios = "codigo//codigos//Dao//arquivos//usuarios.txt";

       public CadastrarUsuarioController(JDesktopPane tela){
           this.cadastrarUsuario = new CadastrarUsuarioView();
           tela.add(cadastrarUsuario);

           int x = (tela.getWidth() - cadastrarUsuario.getWidth()) /2;
           int y = (tela.getHeight() - cadastrarUsuario.getHeight()) /2;
           cadastrarUsuario.setLocation(x,y);

           this.cadastrarUsuario.setVisible(true);
           cadastrarUsuario.getBtnSalvar().addActionListener(e -> {
              String nome = cadastrarUsuario.getLabelNome();
              String cpf = cadastrarUsuario.getLabelCpf();
              String cpe = cadastrarUsuario.getLabelCep();
              String rua = cadastrarUsuario.getLabelRua();
              String numero = cadastrarUsuario.getLabelNumero();
              String cidade = cadastrarUsuario.getLabelCidade();
              String pais  = cadastrarUsuario.getLabelPais();

              Endereco endereco = new Endereco(cpe,rua,numero,cidade,pais);

              Usuario usuario = new Usuario(nome,cpf,endereco,false);
              listaUsuarios.add(usuario);
              boolean sucesso = Usuario.salvarUsuariosNoArquivo(listaUsuarios, caminhosUsuarios);

               if (sucesso) {
               javax.swing.JOptionPane.showMessageDialog(cadastrarUsuario, "Cadastro salvo com sucesso!");
               cadastrarUsuario.dispose();
               } else {
               javax.swing.JOptionPane.showMessageDialog(cadastrarUsuario, "Erro ao salvar cadastro.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
               }
           });
       }

    }
