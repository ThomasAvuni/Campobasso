import java.util.Vector;

public class Azienda {
    public static GestionePrenotazioni gestionePrenotazioni;
    private static Direttore direttore;
    private static Chef chef;
    private static AiutoCuoco aiutoCuoco;
    public static Vector<Cliente> clienti;
    public static ThreadTempo tempo;
    public static Sala sala;

    public void inizia(){
        gestionePrenotazioni = new GestionePrenotazioni();
        direttore = new Direttore("Vincenzo", "Siciliano");
        chef = new Chef("Gianmarco", "Tocco", new Data(1998, 12, 3));
        aiutoCuoco = new AiutoCuoco("Francesco", "Marzano", new Data(1990, 5, 20));
        clienti = new Vector<Cliente>();
        tempo = new ThreadTempo();
        direttore.aggiungiDipendente(aiutoCuoco);
        direttore.aggiungiDipendente(chef);
    
        sala = new Sala();
        sala.RiempiSala();
    }

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