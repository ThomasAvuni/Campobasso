import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RistoranteController {
    @FXML Label timeLabel;
    @FXML Label lbOrdineSbagliato;
    @FXML private VBox listaClienti;
    @FXML private TextField tfOrdinazione;
    @FXML private TextField tfQuantita;
    @FXML private Button btnOrdina;
    @FXML private Label lbTitoloOrdinazione;
    @FXML private Pane paneOrdinazione;
    @FXML private Pane paneRingraziamenti;

    private Cliente cliente;

    public void OrdinazioneSbagliata (String a) {
        lbOrdineSbagliato.setText(a);
        lbOrdineSbagliato.setVisible(true);
    }

    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    public void setPannelloOrdinazione(Prenotazione p, Cliente c){
        lbTitoloOrdinazione.setText("Ordinazione di " + p.getNome() + " " + p.getCognome() + ".\nCosa vuole ordinare?");
        paneOrdinazione.setVisible(true);
        tfOrdinazione.setVisible(true);
        tfQuantita.setVisible(true);
        btnOrdina.setVisible(true);
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

    // Pane Ringraziamenti, Pane Ordinazione sbagliata

    public void ordina(MouseEvent e){
        if(tfOrdinazione.getText().isEmpty()){
            return;
        }
        
        if(tfQuantita.getText().isEmpty()){
            tfQuantita.setText("1");
        }

        String ordinazione = tfOrdinazione.getText();

        lbTitoloOrdinazione.setText("Grazie! Il piatto " + ordinazione + " è in preparazione e arrivererà a breve!");
        tfOrdinazione.clear();
        tfOrdinazione.setVisible(false);
        tfQuantita.clear();
        tfQuantita.setVisible(false);
        btnOrdina.setVisible(false);

        Sala.getCameriere().prendiOrdine(cliente, new Cibo(ordinazione, Integer.parseInt(tfQuantita.getText())));
    }

}
