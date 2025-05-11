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
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {

    @FXML Button btnInizia;
    @FXML private Pane panAggPrenotazione;
    @FXML private TextField tfCongome;
    @FXML private TextField tfOra;
    @FXML private TextField tfNome;
    @FXML private VBox listaClienti;
    @FXML private Label lbOraSbagliata;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static RistoranteController RC;

    private void aggingiCliente(Cliente c) {
        if (c != null) {
            Label label = new Label(c.getNome() + " " + c.getCognome() + " " + c.getPrenotazione().getOraAppuntamento().toString());
            listaClienti.getChildren().add(label);
        }
    }

    public void onIniziaClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneRistorante.fxml"));
        root = loader.load();
        Azienda.tempo.start();

        RC = loader.getController();
        RC.inizia();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ristorante");
        stage.show();
    }

    public void onAggPrenotazioneClicked(MouseEvent event) {
        String nome = tfNome.getText();
        String cognome = tfCongome.getText();
        String[] data = tfOra.getText().split(":");
        if(!nome.isEmpty() && !cognome.isEmpty() && !data[0].isEmpty() && !data[1].isEmpty()){
            Cliente c = new Cliente(nome, cognome, new Ora(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
            if(c.getPrenotazione() == null){
                lbOraSbagliata.setVisible(true);
                return;
            }

            lbOraSbagliata.setVisible(false);
            Azienda.gestionePrenotazioni.aggiungiPrenotazione(c.getPrenotazione());
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