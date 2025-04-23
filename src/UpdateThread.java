import javafx.application.Platform;
public class UpdateThread extends Thread{

    private MainController controller;

    public UpdateThread(MainController c) {
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
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
