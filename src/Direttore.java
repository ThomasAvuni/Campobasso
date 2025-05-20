import java.util.Vector;

import javafx.application.Platform;

public class Direttore{
    //Attributi
    private String nome;
    private String cognome;

    //Vettore di dipendenti
    Vector<Dipendente> dipendenti;

    //Costruttore
    public Direttore(String nome, String cognome) {
        dipendenti = new Vector<Dipendente>();
        this.nome = nome;
        this.cognome = cognome;
    }

    //Getter
    public Vector<Dipendente> getDipententi(){
        return dipendenti;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void pagaStipendi(){
        //Itero sui dipendenti
        for(int i = 0; i < dipendenti.size(); i++){
            //Calcolo lo stipendio
            dipendenti.elementAt(i).calcolaStipendio();
            String s = "Stipendio di " + dipendenti.elementAt(i).getNome() + " " + dipendenti.elementAt(i).getCognome() + ": " + dipendenti.elementAt(i).getStipendio() + " euro";
            //Mostro il pannello con gli stipendi
            Platform.runLater(() -> MainController.getRC().mostraStipendi(s));
            System.out.println(s);
        }
    }

    //Funzione che aggiunge un dipendente al vettore di dipendenti
    public void aggiungiDipendente(Dipendente dipendente){
        dipendenti.addElement(dipendente);
    }
}
