import java.util.Random;
import java.util.Vector;

public class Sala {
    public static final int DIMENSIONE_SALA = 20;
    private static Vector<Tavolo> tavoli;
    private static Cameriere cameriere;
    public static boolean piena = false;

    public static void RiempiSala(){
        Random r = new Random();
        tavoli = new Vector<Tavolo>(DIMENSIONE_SALA);
        for(int i = 0; i < DIMENSIONE_SALA; i++){
            tavoli.addElement(new Tavolo(i + 1, 4));
        }
        cameriere = new Cameriere("Sofia", "Scalia", new Data(1992, 12, 3));
    }

    public static Vector<Tavolo> getTavoli(){
        return tavoli;
    }

    public static Cameriere getCameriere() {
        return cameriere;
    }

    public static void terminaServizio(){
        Azienda.getAiutoCuoco().lavaPiatti();
        Azienda.getDirettore().pagaStipendi();
    }

    public static Vector<Tavolo> getTavoliDisponibili(){
        Vector<Tavolo> v = new Vector<>();
        for(int i = 0; i < tavoli.size() ; i++){
            if(!tavoli.elementAt(i).getOccupato())
                v.addElement(tavoli.elementAt(i));
        }

        return v;
    }
}
