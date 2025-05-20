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
    //Attributi di FXML
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
    @FXML private Pane paneChiusura;
    @FXML private Pane paneStipendi;
    @FXML private VBox listaStipendi;
    @FXML private Pane paneLavapiatti;
    @FXML private Label lbLavapiatti;

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

    public void mostraStipendi(String s){
        //Creazione di un nuovo Label con il testo passato come parametro
        Label label = new Label(s);
        //Lo aggiungo alla VBox degli stipendi
        listaStipendi.getChildren().add(label);
        //Mostro il pannello degli stipendi
        paneStipendi.setVisible(true);
        //Animazione di fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5),paneStipendi);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0); 
        
        fadeIn.play();
    }

    public void Chiudi (MouseEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    //Mostra il pannello che avvisa di aver fatto un ordinazione sbagliata
    public void OrdinazioneSbagliata (String a) {
        lbOrdinazioneSbagliata.setText(a);
        lbOrdinazioneSbagliata.setVisible(true);
    }

    //Funzione che setta il tempo nell'interfaccia grafica
    public void setTempo(String tempo) {
        timeLabel.setText(tempo);
    }

    //Mostra il pannello di chiusura
    public void mostraPaneChiusura() {
        paneChiusura.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5),paneChiusura);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0); 
        
        fadeIn.play();
    }

    public void portaPiatto(Piatto piatto) {
        //Setto il testo del label con il nome del piatto e il tavolo del cliente
        lbPortaPiatto.setText("Ho portato il piatto " + piatto.getNome() + " al " + piatto.getCliente().getTavolo() + ".");
        //Mostro il pannello con il piatto
        panePortaPiatto.setVisible(true);
        lbPortaPiatto.setVisible(true);

        //Animazione di fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5),panePortaPiatto);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0); 
        
        fadeIn.play();
    }

    //Nasconde il pannello con il piatto
    public void nascondiPortaPiatto() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5),panePortaPiatto);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

    public void setPannelloOrdinazione(Prenotazione p, Cliente c){
        //Setto il titolo dell'ordinazione con il nome e cognome del cliente e il tavolo
        cliente = c;
        lbTitoloOrdinazione.setText("Ordinazione di " + p.getNome() + " " + p.getCognome() + ", al " + c.getTavolo() + ".\nCosa vuole ordinare?");
        paneOrdinazione.setVisible(true);
        tfOrdinazione.setVisible(true);
        tfQuantita.setVisible(true);
        btnOrdina.setVisible(true);
    }

    //Mostra il pannello del lavapiatti
    public void mostraPaneLavapiatti(String s) {
        lbLavapiatti.setText(s);
        paneLavapiatti.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5),paneLavapiatti);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0); 
        
        fadeIn.play();
    }

    //Chiude il pannello del lavapiatti
    public void chiudiPaneLavapiatti() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5),paneLavapiatti);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
        fadeOut.setOnFinished(event -> {
            paneLavapiatti.setVisible(false);
        });
    }

    public void inizia(){
        //Inizializza updateThread
        updateThread = new UpdateThread(this);
        updateThread.start();
        //Aggiungo i clienti alla lista
        for(int i = 0; i < Azienda.clienti.size(); i++){
            Cliente c = Azienda.clienti.get(i);
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

    public void ordina(MouseEvent e){
        //Controllo che il campo di ordinazione non sia vuoto
        if(tfOrdinazione.getText().isEmpty()){
            return;
        }
        
        //Se il campo della quantità è vuoto, lo setto a 1
        if(tfQuantita.getText().isEmpty()){
            tfQuantita.setText("1");
        }
        
        //Salvo il valore del campo di ordinazione in una variabile
        String ordinazione = tfOrdinazione.getText();
        //Controllo che l'ordinazione sia valida
        boolean ordinazioneSbagliata = Sala.getCameriere().ControllaOrdinazione(ordinazione);
        //Se è valida proseguo
        if(!ordinazioneSbagliata){
            //Se la quantità ordinata è maggiore del numero di posti al tavolo del cliente rispondo con un messaggio di errore
            if(Integer.parseInt(tfQuantita.getText()) > cliente.getTavolo().getPosti()){
                lbOrdinazioneSbagliata.setText("Non puoi ordinare più piatti di quanti posti ci siano al tavolo!");
                lbOrdinazioneSbagliata.setVisible(true);
                return;
            }

            lbTitoloOrdinazione.setText("Grazie! Il piatto " + ordinazione + " è in preparazione e arrivererà a breve!");
            //Aggiungo il piatto alla lista di cibi da preparare
            Sala.getCameriere().prendiOrdine(cliente, new Cibo(ordinazione, Integer.parseInt(tfQuantita.getText()), cliente));
            //Nascondo il pannello di ordinazione
            lbOrdinazioneSbagliata.setVisible(false);
            tfOrdinazione.clear();
            tfOrdinazione.setVisible(false);
            tfQuantita.clear();
            tfQuantita.setVisible(false);
            btnOrdina.setVisible(false);
            //Creo un nuovo thread anonimo
            new Thread(() -> {
                try {
                    //Aspetto 3 secondi, poi riattivo il tempo
                    Thread.sleep(3000);
                    Azienda.tempo.setPausa(false);
                    //Se non ci sono più prenotazioni, il cuoco cucina
                    if(Azienda.gestionePrenotazioni.getPrimaPrenotazione() == null)
                        Azienda.getChef().Cucina();
                    //Nascodno il pannello di ordinazione
                    Platform.runLater(() -> paneOrdinazione.setVisible(false));
                } catch (InterruptedException ex) {
                }
            }).start();
        }
    }
}
