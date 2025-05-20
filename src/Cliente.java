import java.util.Random;

public class Cliente{
    //Attributi
    private String nome, cognome;
    private Prenotazione prenotazione;
    private Tavolo tavolo;
    private Random random;

    //Costruttore
    public Cliente(String nome, String cognome, Ora oraPrenotazione) {
        this.nome = nome;
        this.cognome = cognome;
        random = new Random();
        //Creare una prenotazione
        prenotazione = Prenotazione.creaPrenotazione(oraPrenotazione, this.nome, this.cognome);
        //Controllo se la prenotazione è valida
        if(prenotazione != null){
            //Setto il cliente della prenotazione
            prenotazione.setCliente(this);
            //Aggiungo il cliente alla lista di clienti
            Azienda.clienti.addElement(this);
            //Gli assegno un tavolo
            AssegnaTavolo();
        }
        else{
            //Altrimenti creo un tavolo vuoto
            tavolo = new Tavolo(0, 0);
        }
    }

    //Getter e Setter
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
        //Itero sui tavoli disponibili
        if(Azienda.sala.getTavoliDisponibili().size() > 0){
            //Genero un numero casuale tra 0 e il numero di tavoli disponibili
            int i = random.nextInt(Azienda.sala.getTavoliDisponibili().size());
            //Assegno il tavolo al cliente
            tavolo = Azienda.sala.getTavoliDisponibili().elementAt(i);
            //Setto il tavolo come occupato
            Azienda.sala.getTavoliDisponibili().elementAt(i).setOccupato(true);
        }
        else{
            tavolo = new Tavolo(0, 0);
            Sala.piena = true;
            System.out.println("Tutti i tavoli sono pieni");
        }
    }
}