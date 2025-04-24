import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RistoranteController {
        @FXML Label timeLabel;
        @FXML private VBox listaClienti;

    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    public void inizia(){
        ThreadTempo t = new ThreadTempo();
        t.start();
        UpdateThread updateThread = new UpdateThread(this);
        updateThread.start();
        for(int i = 0; i < Azienda.clienti.size(); i++){
            Cliente c = Azienda.clienti.get(i);
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

}
