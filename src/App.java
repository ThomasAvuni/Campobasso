import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    public static void main(String[] args) throws Exception { 
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception { // Metodo principale JavaFX
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml")); // Carica il file FXML
        Scene scene = new Scene(loader.load(), 1024, 768); // Crea la scena
        stage.setTitle("Ristorante");
        scene.setFill(Color.TRANSPARENT);   //Finestra trasparente e senza bordo
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        ((MainController)loader.getController()).init(stage); // Inizializza il controller
        stage.show(); //Mosta la finestra
        Azienda a = new Azienda();
        a.inizia();// Inizializza l'azienda
    }
}
