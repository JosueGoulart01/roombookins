package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Usuario {

    private String nome;
    private String cpf;
    private boolean corporativo;
    private boolean convidado;
    private Endereco endereco;     

    /**
     * Construtor para usuário corporativo.
     * @param nome  Nome do usuário
     * @param cpf   CPF do usuário
     * @param corporativo Flag indicando que é corporativo (true)
     */
    public Usuario(String nome, String cpf,Endereco endereco, boolean corporativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.corporativo = corporativo;
        this.convidado = false;   
        
    }

    /**
     * Construtor para usuário convidado.
     * @param nome      Nome do convidado
     * @param convidado Flag indicando que é convidado (true)
     */
    public Usuario(String nome, boolean convidado) {
        this.nome = nome;
        this.corporativo = false; 
        this.convidado = convidado;
        this.cpf = null;         
    }

    // Metodos get
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isCorporativo() {
        return corporativo;
    }

    public boolean isConvidado() {
        return convidado;
    }

    // Criei para caso seja usado
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String escritaArquivo() {
        if (convidado) {
            return nome + ";" + true;
        } else {
            return nome + ";" + cpf + ";" +
                   endereco.getCep() + ";" + endereco.getRua() + ";" +
                   endereco.getNumero() + ";" + endereco.getCidade() + ";" +
                   endereco.getPais() + ";" + corporativo;
        }
    }

    public static boolean salvarUsuariosNoArquivo(ArrayList<Usuario> listaUsuarios, String caminhoArquivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
        for (Usuario usuario : listaUsuarios) {
            String linha = usuario.getNome() + ";" +
                           usuario.getCpf() + ";" +
                           usuario.getEndereco().getCep() + ";" +
                           usuario.getEndereco().getRua() + ";" +
                           usuario.getEndereco().getNumero() + ";" +
                           usuario.getEndereco().getCidade() + ";" +
                           usuario.getEndereco().getPais() + ";" +
                           usuario.isCorporativo() + System.lineSeparator();
            writer.write(linha);
        }
        return true; // salvou com sucesso
    } catch (IOException e) {
        System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        return false; // erro ao salvar
    }
}
}
