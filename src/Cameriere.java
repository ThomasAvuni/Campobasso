import java.util.Vector;

public class Cameriere extends Dipendente {
    Vector<Piatto> piattiPronti;
    private Tavolo tavolo; 
    public Cameriere(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        piattiPronti = new Vector<Piatto>();
        this.ruolo = "Cameriere";
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    public void prendiOrdine(Cliente cliente, Cibo cibo) {
        if(cibo != null){
            Azienda.getChef().AddCiboInCoda(cibo);
            if(Azienda.gestionePrenotazioni.getPrimaPrenotazione() == null)
                Azienda.getChef().Cucina();
        }
    }

    public void AddPiattoPronto (Piatto piatto) {
        piattiPronti.addElement(piatto);
    }

    public boolean ControllaOrdinazione(String ordinazione){
        if(ordinazione.equalsIgnoreCase("pasta e zucchine") || ordinazione.equalsIgnoreCase("pasta al pomodoro") || ordinazione.equalsIgnoreCase("carne alla griglia")){
            return false;
        }
        MainController.getRC().OrdinazioneSbagliata(new String("Il piatto " + ordinazione + " non e' nel menu!"));
        return true;
    }

    public void portaPiatto() {
        
    }
}
