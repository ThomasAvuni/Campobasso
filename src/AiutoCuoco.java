import java.util.Vector;

import javafx.application.Platform;
public class AiutoCuoco extends Chef {
    private int numeroPiattiDaLavare;
    public static Vector<Piatto> piattiDaLavare = new Vector<Piatto>();
    public AiutoCuoco(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        this.ruolo = "Aiuto cuoco";
        this.numeroPiattiDaLavare = 0;
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    public void lavaPiatti(){
        for (int i = 0; i < piattiDaLavare.size() ; i++ ) {
            piattiDaLavare.elementAt(i).lava();
            numeroPiattiDaLavare++;
            piattiDaLavare.removeElementAt(i);
        }
        Platform.runLater(() -> MainController.getRC().mostraPaneLavapiatti("Ci sono " + numeroPiattiDaLavare + " piatti da lavare."));
    }

}
                                                                                                                                                                 