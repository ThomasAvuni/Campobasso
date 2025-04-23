public class Piatto {
    private String nome;
    private String lavato;
    private int quantita;
    private Tavolo tavolo;
    private boolean pronto;

    public Piatto(String nome){
        setNome(nome);
        pronto = false;
    }

    public void setNome (String nome){
        this.nome = nome;
    }    

    public void setQuantita (int quantita){
        this.quantita= quantita;
    }

    public String getNome() {
        return nome;
    }

    public String getLavato() {
        return lavato;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setLavato(String lavato) {
        this.lavato = lavato;
    }
    public void setTavolo(Tavolo tavolo) {
        this.tavolo = tavolo;
    }

    public Tavolo getTavolo() {
        return tavolo;
    }

    public boolean isPronto() {
        return pronto;
    }

    public boolean getPronto(){
        return pronto;
    }

    public void setPronto(boolean pronto) {
        this.pronto = pronto;
    }

    public void lava (){
        if(lavato == "sporco")
            lavato  = "pulito";
            System.out.println("Il piatto e' stato lavato");
    }

}
