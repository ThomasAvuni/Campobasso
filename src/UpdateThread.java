import javafx.application.Platform;
public class UpdateThread extends Thread{
    //Attributi
    private RistoranteController controller;

    //Costruttore
    public UpdateThread(RistoranteController c) {
        controller = c;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Fermo il thread per 5 millisecondi
                Thread.sleep(5);
                //Creazione di un nuovo thread anonimo
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //Aggiorno l'interfaccia grafica del tempo
                        controller.setTempo(Azienda.tempo.getTempo());
                        if(Azienda.gestionePrenotazioni.getPrimaPrenotazione() == null)
                            return;
                        //Controllo se il tempo è uguale all'orario della prima prenotazione
                        if(Azienda.tempo.getOra().isEqual(Azienda.gestionePrenotazioni.getPrimaPrenotazione().getOraAppuntamento())){
                            //Fermo il tempo
                            Azienda.tempo.setPausa(true);
                            //Apro il pannello di ordinazione
                            controller.setPannelloOrdinazione(Azienda.gestionePrenotazioni.getPrimaPrenotazione(), Azienda.gestionePrenotazioni.getPrimaPrenotazione().getCliente());
                            //Elimino la prenotazione
                            Azienda.gestionePrenotazioni.elimina(Azienda.gestionePrenotazioni.getPrimaPrenotazione());
                        }
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
