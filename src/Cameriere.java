import java.util.Vector;

import javafx.application.Platform;

public class Cameriere extends Dipendente {
    //Vettore di piatti pronti
    Vector<Piatto> piattiPronti;
    //Costruttore
    public Cameriere(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        piattiPronti = new Vector<Piatto>();
        this.ruolo = "Cameriere";
    }

    //Funzione che setta lo stipendio del cameriere
    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    //Funzione che aggiunge un cibo ordinato alla coda di preparazione
    public void prendiOrdine(Cliente cliente, Cibo cibo) {
        if(cibo != null){
            Azienda.getChef().AddCiboInCoda(cibo);
        }
    }

    //Funzione che aggiunge un piatto al vettore di piatti pronti
    public void AddPiattoPronto(Piatto piatto) {
        piattiPronti.addElement(piatto);
    }

    //Funzione che controlla se il piatto ordinato è nel menu
    public boolean ControllaOrdinazione(String ordinazione){
        if(ordinazione.equalsIgnoreCase("pasta e zucchine") || ordinazione.equalsIgnoreCase("pasta al pomodoro") || ordinazione.equalsIgnoreCase("carne alla griglia")){
            return false;
        }
        MainController.getRC().OrdinazioneSbagliata(new String("Il piatto " + ordinazione + " non e' nel menu!"));
        return true;
    }

    public void portaPiatti() {
        //Creazione di un thread anonimo per la consegna dei piatti
        new Thread(){
            public void run() {
                //Iterazione sui piatti pronti
                for (int i = 0; i < piattiPronti.size(); i++){
                    //Variabile di supporto per il piato
                    Piatto piatto = piattiPronti.elementAt(i);
                    try {
                        //Fermo il thread per il tempo di preparazione del piatto
                        Thread.sleep(piatto.getTempoDiPreparazione() * 1000);
                        //Mostro l'interfaccia grafica per la consegna del piatto
                        Platform.runLater(() -> MainController.getRC().portaPiatto(piatto));
                        Thread.sleep(3500);
                        //Dopo 3 secondi nascondo il piatto
                        Platform.runLater(() -> MainController.getRC().nascondiPortaPiatto());
                    } catch (InterruptedException e) {
                    }
                }
                try{
                    Thread.sleep(1500);
                    Azienda.getAiutoCuoco().lavaPiatti();
                    Thread.sleep(1500);
                    Platform.runLater(() -> MainController.getRC().chiudiPaneLavapiatti());
                    Thread.sleep(1500);
                    Platform.runLater(() -> MainController.getRC().mostraPaneLavapiatti("Ho lavato tutti i piatti."));
                    Thread.sleep(1500);
                    Platform.runLater(() -> MainController.getRC().chiudiPaneLavapiatti());
                    //Quando ho finito di portare i piatti, aspetto 2 secondi e poi chiudo il servizio
                    Thread.sleep(2000);
                    Sala.terminaServizio();
                    Platform.runLater(() -> MainController.getRC().mostraPaneChiusura());
                }
                catch(InterruptedException e){

                }
            }
        }.start();
    }
}
