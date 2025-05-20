import java.util.Random;
import java.util.Vector;

public class Sala {
    //Attributi
    public static final int DIMENSIONE_SALA = 20;
    private Vector<Tavolo> tavoli;
    private static Cameriere cameriere;
    public static boolean piena = false;

    public void RiempiSala(){
        //Inizializzo il random
        Random r = new Random();
        //Inizializzo il vettore di tavoli
        tavoli = new Vector<Tavolo>(DIMENSIONE_SALA);
        //Inizializzo i tavoli
        for(int i = 0; i < DIMENSIONE_SALA; i++){
            tavoli.addElement(new Tavolo(i + 1, r.nextInt(4) + 1));
            System.out.println("Tavolo " + (i + 1) + " con " + tavoli.elementAt(i).getPosti() + " posti");
        }
        //Inizializzo il cameriere
        cameriere = new Cameriere("Sofia", "Scalia", new Data(1992, 12, 3));
        //Aggiungo il cameriere alla lista dei dipendenti
        Azienda.getDirettore().aggiungiDipendente(cameriere);
    }

    //Getter
    public Vector<Tavolo> getTavoli(){
        return tavoli;
    }

    public static Cameriere getCameriere() {
        return cameriere;
    }

    public static void terminaServizio(){
        Azienda.getDirettore().pagaStipendi();
    }

    //Funzione che ritorna un vettore di tavoli disponibili
    public Vector<Tavolo> getTavoliDisponibili(){
        Vector<Tavolo> v = new Vector<>();
        for(int i = 0; i < tavoli.size() ; i++){
            if(!tavoli.elementAt(i).getOccupato())
                v.addElement(tavoli.elementAt(i));
        }

        return v;
    }
}
