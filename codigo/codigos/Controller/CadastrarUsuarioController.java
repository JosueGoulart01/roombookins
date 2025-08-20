package Controller;

import Service.UsuarioService;
import View.CadastrarUsuarioView;
import javax.swing.*;

public class CadastrarUsuarioController {

    private final CadastrarUsuarioView cadastrarUsuario;
    private final UsuarioService usuarioService;

    public CadastrarUsuarioController(JDesktopPane tela) {
        this.cadastrarUsuario = new CadastrarUsuarioView();
        tela.add(cadastrarUsuario);

        int x = (tela.getWidth() - cadastrarUsuario.getWidth()) / 2;
        int y = (tela.getHeight() - cadastrarUsuario.getHeight()) / 2;
        cadastrarUsuario.setLocation(x, y);

        this.usuarioService = new UsuarioService();

        cadastrarUsuario.setVisible(true);

        cadastrarUsuario.getBtnSalvar().addActionListener(e -> salvarUsuario());
    }

    private void salvarUsuario() {
        try {
            // Captura os dados dos campos da view
            String nome = cadastrarUsuario.getLabelNome();
            String cpf = cadastrarUsuario.getLabelCpf();
            String cep = cadastrarUsuario.getLabelCep();
            String rua = cadastrarUsuario.getLabelRua();
            String numero = cadastrarUsuario.getLabelNumero();
            String cidade = cadastrarUsuario.getLabelCidade();
            String pais = cadastrarUsuario.getLabelPais();

            // Chama o serviço para cadastrar
            usuarioService.cadastrarUsuario(nome, cpf, cep, rua, numero, cidade, pais, false);

            JOptionPane.showMessageDialog(cadastrarUsuario, "Cadastro salvo com sucesso!");
            cadastrarUsuario.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cadastrarUsuario,
                    "Erro ao salvar cadastro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
