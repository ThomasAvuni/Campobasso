import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        stage.setTitle("Ristorante");
        stage.setScene(new Scene(loader.load(), 1024, 768));
        stage.show();
        Azienda a = new Azienda();
        a.inizia();
    }
}