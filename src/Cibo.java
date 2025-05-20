public class Cibo {
    //Attributi
    private String nome;
    private int quantita;
    private Cliente cliente;

    //Costruttore
    public Cibo(String nome){
        this.nome = nome;
        this.quantita = 1;
    }

    //Costruttore con quantità
    public Cibo(String nome, int quantita){
        this.nome = nome;
        this.quantita = quantita;
    }

    //Costruttore con cliente
    public Cibo(String nome, int quantita, Cliente cliente){
        this.nome = nome;
        this.quantita = quantita;
        this.cliente = cliente;
    }

    //Getter e Setter
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
