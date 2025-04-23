public class Tavolo {
    private int numero;
    private int posti;
    private boolean occupato;

    public Tavolo(int numero, int posti){
        this.numero = numero;
        this.posti = posti;
        occupato = false;
    }

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
