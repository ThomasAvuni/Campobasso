public class Cibo {
    private String nome;
    private int quantita;

    public Cibo(String nome){
        this.nome = nome;
    }

    public Cibo(String nome, int quantita){
        this.nome = nome;
        this.quantita = quantita;
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
}
