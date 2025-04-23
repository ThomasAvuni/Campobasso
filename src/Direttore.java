import java.util.Vector;

public class Direttore{
    private String nome;
    private String cognome;

    Vector<Dipendente> dipendenti;

    public Direttore(String nome, String cognome) {
        dipendenti = new Vector<Dipendente>();
        this.nome = nome;
        this.cognome = cognome;
    }

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
        for(int i = 0; i < dipendenti.size(); i++){
            dipendenti.elementAt(i).calcolaStipendio();
            System.out.println("Stipendio di " + dipendenti.elementAt(i).getNome() + " " + dipendenti.elementAt(i).getCognome() + ": " + dipendenti.elementAt(i).getStipendio() + " euro");
        }
    }

    public void aggiungiDipendente(Dipendente dipendente){
        dipendenti.addElement(dipendente);
    }
}
