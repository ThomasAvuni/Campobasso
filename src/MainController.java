import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {

    //Attributi di FXML
    @FXML Button btnInizia;
    @FXML private Pane panAggPrenotazione;
    @FXML private TextField tfCongome;
    @FXML private TextField tfOra;
    @FXML private TextField tfNome;
    @FXML private VBox listaClienti;
    @FXML private Label lbOraSbagliata;
    @FXML private Pane TopBar;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static RistoranteController RC;
    private double x = 0, y = 0;

    public void init(Stage stage){
        //Questo metodo serve per spostare la finestra
        TopBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        TopBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    //Metodo che chiude la finestra
    public void Chiudi (MouseEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Aggiunge un cliente alla lista(VBox) dei clienti
    private void aggingiCliente(Cliente c) {
        if (c != null) {
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

    public void onIniziaClicked(MouseEvent event) throws IOException {
        //Carica la scena del ristorante
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneRistorante.fxml"));
        root = loader.load();
        //Iniziare il Thread de tempo
        Azienda.tempo.start();
        
        RC = loader.getController();
        RC.inizia();
        
        //Creazione della scena
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Ristorante");
        ((RistoranteController)loader.getController()).init(stage);
        //Mostrare la finestra
        stage.show();
    }

    public void onAggPrenotazioneClicked(MouseEvent event) {
        //Salvo in una variabile il nome, cognome e ora inseriti dall'utente
        String nome = tfNome.getText();
        String cognome = tfCongome.getText();
        //Visto che il tempo è espresso in ore:minuti, divido la stringa in due parti dove c'è il ":"
        String[] data = tfOra.getText().split(":");
        //Controllo che il nome, cognome e ora siano stati inseriti
        if(!nome.isEmpty() && !cognome.isEmpty() && !data[0].isEmpty() && !data[1].isEmpty()){
            //Creo un cliente con i dati inseriti
            Cliente c = new Cliente(nome, cognome, new Ora(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
            //Se la prenotazione non è valida, mostro un messaggio di errore
            if(c.getPrenotazione() == null){
                lbOraSbagliata.setText("L'ora della prenotazione non è valida! L'orario d'apertura è 19:00.");
                lbOraSbagliata.setVisible(true);
                return;
            }
            //Se la prenotazione è già occupata, mostro un messaggio di errore
            if(Azienda.gestionePrenotazioni.controllaPrenDoppia(c.getPrenotazione())){
                lbOraSbagliata.setText("Quest'orario è già occupato.");
                lbOraSbagliata.setVisible(true);
                return;
            }

            lbOraSbagliata.setVisible(false);
            //Aggiungo la prenotazione alla lista
            Azienda.gestionePrenotazioni.aggiungiPrenotazione(c.getPrenotazione());
            //Aggiungo il cliente alla lista dei clienti
            aggingiCliente(c);
        }
        tfNome.clear();
        tfCongome.clear();
        tfOra.clear();
    }
    
    public static RistoranteController getRC () {
        return RC; 
    }
}