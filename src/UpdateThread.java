import javafx.application.Platform;
public class UpdateThread extends Thread{

    private RistoranteController controller;
    private Prenotazione p = Prenotazione.creaPrenotazione(new Ora(19, 10),"Thomas", "Rossi");

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
                        controller.setTempo(ThreadTempo.getTempo());
                        // if(Azienda.gestionePrenotazioni.getUltimaPrenotazione() == null)
                        //     return;
                        if(ThreadTempo.getOra().isEqual(p.getOraAppuntamento())){
                            controller.setPannelloOrdinazione();
                        }
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
