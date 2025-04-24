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
                        controller.setTempo(ThreadTempo.getTempo());
                        if(Azienda.gestionePrenotazioni.getUltimaPrenotazione() == null)
                            return;
                        if(ThreadTempo.getOra().isEqual(Azienda.gestionePrenotazioni.getUltimaPrenotazione().getOraAppuntamento())){
                            controller.setPannelloOrdinazione();
                        }
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
