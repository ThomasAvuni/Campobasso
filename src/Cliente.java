import java.util.Random;

public class Cliente{
    private String nome, cognome;
    private Prenotazione prenotazione;
    private Tavolo tavolo;
    private Random random;

    public Cliente(String nome, String cognome, Ora oraPrenotazione) {
        this.nome = nome;
        this.cognome = cognome;
        random = new Random();
        prenotazione = Prenotazione.creaPrenotazione(oraPrenotazione, this.nome, this.cognome);
        if(prenotazione != null){
            prenotazione.setCliente(this);
            Azienda.clienti.addElement(this);
            AssegnaTavolo();
        }
        else{
            tavolo = new Tavolo(0, 0);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Tavolo getTavolo() {
        return tavolo;
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void AssegnaTavolo(){
        if(Azienda.sala.getTavoliDisponibili().size() > 0){
            int i = random.nextInt(Azienda.sala.getTavoliDisponibili().size());
            tavolo = Azienda.sala.getTavoliDisponibili().elementAt(i);
            Azienda.sala.getTavoliDisponibili().elementAt(i).setOccupato(true);
        }
        else{
            tavolo = new Tavolo(0, 0);
            Sala.piena = true;
            System.out.println("Tutti i tavoli sono pieni");
        }
    }
}