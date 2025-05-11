public class Cibo {
    private String nome;
    private int quantita;
    private Cliente cliente;

    public Cibo(String nome){
        this.nome = nome;
        this.quantita = 1;
    }

    public Cibo(String nome, int quantita){
        this.nome = nome;
        this.quantita = quantita;
    }

    public Cibo(String nome, int quantita, Cliente cliente){
        this.nome = nome;
        this.quantita = quantita;
        this.cliente = cliente;
    }

    public void setQuantita (int quantita){
        this.quantita= quantita;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
