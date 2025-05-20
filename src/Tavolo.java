public class Tavolo {
    //Attributi
    private int numero;
    private int posti;
    private boolean occupato;

    //Costruttore
    public Tavolo(int numero, int posti){
        this.numero = numero;
        this.posti = posti;
        occupato = false;
    }

    //Getter e setter
    public int getNumero(){
        return this.numero;
    } 

    public boolean getOccupato(){
        return occupato;
    }

    public void setOccupato(boolean occupato){
        this.occupato = occupato;
    }

    public int getPosti(){
        return this.posti;
    }

    @Override
    public String toString() {
        return "Tavolo: " + getNumero() + " di posti: " + getPosti();
    }

}
