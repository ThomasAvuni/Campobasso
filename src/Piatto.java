public class Piatto {
    //Attributi
    private String nome;
    private String lavato;
    private int quantita;
    private Cliente cliente;
    private boolean pronto;

    //Costruttore
    public Piatto(String nome){
        setNome(nome);
        pronto = false;
    }

    //Costruttore con cliente
    public Piatto(String nome, Cliente cliente){
        setNome(nome);
        pronto = false;
        this.cliente = cliente;
    }

    //Getter e setter
    public void setNome (String nome){
        this.nome = nome;
    }    

    public void setQuantita (int quantita){
        this.quantita= quantita;
    }

    public String getNome() {
        return nome;
    }

    //Funzione che restituisce il tempo di preparazione del piatto
    public int getTempoDiPreparazione() {
        if(nome.equalsIgnoreCase("pasta e zucchine") || nome.equalsIgnoreCase("pasta al pomodoro")){
            return 7;
        }
        else if(nome.equalsIgnoreCase("carne alla griglia")){
            return 5;
        }
        return 0;
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
    public void setCliente(Cliente c) {
        this.cliente = c;
    }

    public Cliente getCliente() {
        return this.cliente;
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
