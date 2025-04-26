import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RistoranteController {
    @FXML Label timeLabel;
    @FXML private VBox listaClienti;
    @FXML private TextField tfOrdinazione;
    @FXML private Button btnOrdina;
    @FXML private Label lbTitoloOrdinazione;
    @FXML private Pane paneOrdinazione;

    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    public void setPannelloOrdinazione(Prenotazione p){
        lbTitoloOrdinazione.setText("Ordinazione di " + p.getNome() + " " + p.getCognome() + ".\nCosa vuole ordinare?");
        paneOrdinazione.setVisible(true);
    }

    public void inizia(){
        UpdateThread updateThread = new UpdateThread(this);
        updateThread.start();
        for(int i = 0; i < Azienda.clienti.size(); i++){
            Cliente c = Azienda.clienti.get(i);
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

    public void ordina(MouseEvent e){
        if(tfOrdinazione.getText().isEmpty()){
            return;
        }

        String ordinazione = tfOrdinazione.getText();


        btnOrdina.setVisible(false);
        tfOrdinazione.setVisible(false);
        lbTitoloOrdinazione.setText("Grazie! Il piatto " + ordinazione + " è in preparazione e arrivererà a breve!.");
    }

}
