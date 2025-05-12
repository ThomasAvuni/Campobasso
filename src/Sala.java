import java.util.Random;
import java.util.Vector;

public class Sala {
    public static final int DIMENSIONE_SALA = 20;
    private Vector<Tavolo> tavoli;
    private static Cameriere cameriere;
    public static boolean piena = false;

    public void RiempiSala(){
        Random r = new Random();
        tavoli = new Vector<Tavolo>(DIMENSIONE_SALA);
        for(int i = 0; i < DIMENSIONE_SALA; i++){
            tavoli.addElement(new Tavolo(i + 1, r.nextInt(4) + 1));
            System.out.println("Tavolo " + (i + 1) + " con " + tavoli.elementAt(i).getPosti() + " posti");
        }
        cameriere = new Cameriere("Sofia", "Scalia", new Data(1992, 12, 3));
        Azienda.getDirettore().aggiungiDipendente(cameriere);
    }

    public Vector<Tavolo> getTavoli(){
        return tavoli;
    }

    public static Cameriere getCameriere() {
        return cameriere;
    }

    public static void terminaServizio(){
        Azienda.getAiutoCuoco().lavaPiatti();
        Azienda.getDirettore().pagaStipendi();
    }

    public Vector<Tavolo> getTavoliDisponibili(){
        Vector<Tavolo> v = new Vector<>();
        for(int i = 0; i < tavoli.size() ; i++){
            if(!tavoli.elementAt(i).getOccupato())
                v.addElement(tavoli.elementAt(i));
        }

        return v;
    }
}
