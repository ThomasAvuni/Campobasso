import javafx.application.Platform;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RistoranteController {
    @FXML private Label timeLabel;
    @FXML private VBox listaClienti;
    @FXML private TextField tfOrdinazione;
    @FXML private TextField tfQuantita;
    @FXML private Button btnOrdina;
    @FXML private Label lbTitoloOrdinazione;
    @FXML private Pane paneOrdinazione;
    @FXML private Pane paneRingraziamenti;
    @FXML private Label lbOrdinazioneSbagliata;
    @FXML private Label lbPiattoPronto;
    @FXML private Pane panePiattoPronto;
    @FXML private Button btnChiudi;
    @FXML private Pane panePortaPiatto;
    @FXML private Label lbPortaPiatto;
    @FXML private Pane TopBar;


    private Cliente cliente;
    private UpdateThread updateThread;
    private double x = 0, y = 0;

    public void init(Stage stage){
        TopBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        TopBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    public void Chiudi (MouseEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    public void OrdinazioneSbagliata (String a) {
        lbOrdinazioneSbagliata.setText(a);
        lbOrdinazioneSbagliata.setVisible(true);
    }

    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    public void setLBPortaPiatto(String piatto) {
        lbPortaPiatto.setText(piatto);
        panePortaPiatto.setVisible(true);
        lbPortaPiatto.setVisible(true);
    }

    public void portaPiatto(Piatto piatto) {
        
        lbPortaPiatto.setText("Ho portato il piatto " + piatto.getNome() + " al " + piatto.getCliente().getTavolo() + ".");
        panePortaPiatto.setVisible(true);
        lbPortaPiatto.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),panePortaPiatto);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        
        fadeIn.play();
    }

    public void nascondiPortaPiatto() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2),panePortaPiatto);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

    public void setPannelloOrdinazione(Prenotazione p, Cliente c){
        cliente = c;
        lbTitoloOrdinazione.setText("Ordinazione di " + p.getNome() + " " + p.getCognome() + ", al " + c.getTavolo() + ".\nCosa vuole ordinare?");
        paneOrdinazione.setVisible(true);
        tfOrdinazione.setVisible(true);
        tfQuantita.setVisible(true);
        btnOrdina.setVisible(true);
    }

    public void inizia(){
        updateThread = new UpdateThread(this);
        updateThread.start();
        for(int i = 0; i < Azienda.clienti.size(); i++){
            Cliente c = Azienda.clienti.get(i);
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

    public void prova(){
        System.out.println("Prova");
    }

    public void ordina(MouseEvent e){
        
        if(tfOrdinazione.getText().isEmpty()){
            return;
        }
        
        if(tfQuantita.getText().isEmpty()){
            tfQuantita.setText("1");
        }
        
        String ordinazione = tfOrdinazione.getText();
        boolean ordinazioneSbagliata = Sala.getCameriere().ControllaOrdinazione(ordinazione);
        if(!ordinazioneSbagliata){
            if(Integer.parseInt(tfQuantita.getText()) > cliente.getTavolo().getPosti()){
                lbOrdinazioneSbagliata.setText("Non puoi ordinare più piatti di quanti posti ci siano al tavolo!");
                lbOrdinazioneSbagliata.setVisible(true);
                return;
            }

            lbTitoloOrdinazione.setText("Grazie! Il piatto " + ordinazione + " è in preparazione e arrivererà a breve!");
            Sala.getCameriere().prendiOrdine(cliente, new Cibo(ordinazione, Integer.parseInt(tfQuantita.getText()), cliente));
            lbOrdinazioneSbagliata.setVisible(false);
            tfOrdinazione.clear();
            tfOrdinazione.setVisible(false);
            tfQuantita.clear();
            tfQuantita.setVisible(false);
            btnOrdina.setVisible(false);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    Azienda.tempo.setPausa(false);
                    if(Azienda.gestionePrenotazioni.getPrimaPrenotazione() == null)
                        Azienda.getChef().Cucina();
                    Platform.runLater(() -> paneOrdinazione.setVisible(false));
                } catch (InterruptedException ex) {

                }
            }).start();

        }

    }

}
