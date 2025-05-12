import java.util.Vector;

import javafx.application.Platform;

public class Cameriere extends Dipendente {
    Vector<Piatto> piattiPronti;
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
        }
    }

    public void AddPiattoPronto(Piatto piatto) {
        piattiPronti.addElement(piatto);
    }

    public boolean ControllaOrdinazione(String ordinazione){
        if(ordinazione.equalsIgnoreCase("pasta e zucchine") || ordinazione.equalsIgnoreCase("pasta al pomodoro") || ordinazione.equalsIgnoreCase("carne alla griglia")){
            return false;
        }
        MainController.getRC().OrdinazioneSbagliata(new String("Il piatto " + ordinazione + " non e' nel menu!"));
        return true;
    }

    public void portaPiatti() {
        new Thread(){
            public void run() {
                for (int i = 0; i < piattiPronti.size(); i++){
                    Piatto piatto = piattiPronti.elementAt(i);
                    try {
                        Thread.sleep(piatto.getTempoDiPreparazione() * 1000);
                        Platform.runLater(() -> MainController.getRC().portaPiatto(piatto));
                        Thread.sleep(3500);
                        Platform.runLater(() -> MainController.getRC().nascondiPortaPiatto());
                    } catch (InterruptedException e) {
                    }
                }
                Platform.runLater(() -> MainController.getRC().setLBPortaPiatto("Tutti i piatti sono stati portati.\nIl servizio è finito."));
            }
        }.start();
    }
}
