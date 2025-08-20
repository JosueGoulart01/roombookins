package Model;
public class Endereco {
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String pais;

    public Endereco(String cep, String rua, String numero, String cidade, String pais) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.pais = pais;
        validarCep(cep);
        validarRua(rua);
        validarNumero(numero);
        validarCidade(cidade);
        validarPais(pais);
    }

    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    private boolean validarCep(String cep) {
        return cep != null && cep.matches("\\d{5}-\\d{3}");
    }

    private boolean validarRua(String rua) {
        return rua != null && !rua.isEmpty();
    }

    private boolean validarNumero(String numero) {
        return numero != null && !numero.isEmpty();
    }

    private boolean validarCidade(String cidade) {
        return cidade != null && !cidade.isEmpty();
    }

    private boolean validarPais(String pais) {
        return pais != null && !pais.isEmpty();
    }
}
