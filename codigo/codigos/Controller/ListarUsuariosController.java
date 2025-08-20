package Controller;

import Model.Usuario;
import View.ListarUsuariosView;
import java.util.List;
import javax.swing.*;

public class ListarUsuariosController {

    private final ListarUsuariosView view;

    public ListarUsuariosController(JDesktopPane tela, List<Usuario> usuarios) {
        this.view = new ListarUsuariosView();
        tela.add(view);
        view.setVisible(true);

        int x = (tela.getWidth() - view.getWidth()) / 2;
        int y = (tela.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        preencherTabela(usuarios);
    }

    private void preencherTabela(List<Usuario> usuarios) {
        view.limparTabela();

        for (Usuario usuario : usuarios) {
            String endereco = usuario.getEndereco().getRua() + ", " + usuario.getEndereco().getNumero()
                    + " - " + usuario.getEndereco().getCidade();
            view.adicionarUsuario(usuario.getNome(), usuario.getCpf(), endereco, usuario.isCorporativo());
        }
    }
}