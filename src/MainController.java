import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML Label timeLabel;
    @FXML Button btnInizia;

    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    public void onIniziaClicked(MouseEvent event) {

        ThreadTempo t = new ThreadTempo();
        t.start();
        UpdateThread updateThread = new UpdateThread(this);
        updateThread.start();
        btnInizia.setVisible(false);
    }
}