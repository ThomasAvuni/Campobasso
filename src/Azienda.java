import java.util.Vector;

public class Azienda {
    public static GestionePrenotazioni gestionePrenotazioni = new GestionePrenotazioni();
    private static Direttore direttore = new Direttore("Vincenzo", "Siciliano");
    private static Chef chef = new Chef("Gianmarco", "Tocco", new Data(1998, 12, 3));
    private static AiutoCuoco aiutoCuoco = new AiutoCuoco("Francesco", "Marzano", new Data(1990, 5, 20));
    public static Vector<Cliente> clienti = new Vector<Cliente>();

    public static Chef getChef() {
        return chef;
    }

    public static AiutoCuoco getAiutoCuoco() {
        return aiutoCuoco;
    }

    public static Direttore getDirettore(){
        return direttore; 
    }

}