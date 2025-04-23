import java.util.Vector;
public class AiutoCuoco extends Chef {
    
    public static Vector<Piatto> piattiDaLavare = new Vector<Piatto>();
    public AiutoCuoco(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        this.ruolo = "Aiuto cuoco";
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    public void lavaPiatti(){
        for (int i = 0; i < piattiDaLavare.size() ; i++ ) {
            piattiDaLavare.elementAt(i).lava();
            piattiDaLavare.removeElementAt(i);
        }
    
    }

}
