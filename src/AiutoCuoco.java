import java.util.Vector;

import javafx.application.Platform;
public class AiutoCuoco extends Chef {
    public int piattiDaLavare;
    public AiutoCuoco(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        this.ruolo = "Aiuto cuoco";
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    public void lavaPiatti(){
        Platform.runLater(() -> MainController.getRC().mostraPaneLavapiatti("Ci sono " + piattiDaLavare + " piatti da lavare."));
    }

}
                                                                                                                                                                 