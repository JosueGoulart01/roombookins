package Model;

public class Recurso {
    private int id;
    private String nome;
    private String categoria;

    public Recurso(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {  // Adicione isto caso vá usar banco de dados
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
    