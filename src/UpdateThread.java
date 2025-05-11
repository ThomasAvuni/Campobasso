import javafx.application.Platform;
public class UpdateThread extends Thread{

    private RistoranteController controller;

    public UpdateThread(RistoranteController c) {
        controller = c;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.setTempo(Azienda.tempo.getTempo());
                        if(Azienda.gestionePrenotazioni.getPrimaPrenotazione() == null)
                            return;
                        if(Azienda.tempo.getOra().isEqual(Azienda.gestionePrenotazioni.getPrimaPrenotazione().getOraAppuntamento())){
                            Azienda.tempo.setPausa(true);
                            controller.setPannelloOrdinazione(Azienda.gestionePrenotazioni.getPrimaPrenotazione(), Azienda.gestionePrenotazioni.getPrimaPrenotazione().getCliente());
                            Azienda.gestionePrenotazioni.elimina(Azienda.gestionePrenotazioni.getPrimaPrenotazione());
                        }
                        
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
