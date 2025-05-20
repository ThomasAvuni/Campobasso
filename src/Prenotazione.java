public class Prenotazione {
    //Attributi
    private Ora oraAppuntamento;
    private String nome, cognome;
    Cliente cliente;
    

    //Costruttore privato
    private Prenotazione(Ora data, String nome, String cognome){
        this.oraAppuntamento = data;
        this.nome = nome;
        this.cognome = cognome;
        this.oraAppuntamento = data;
    }

    //Metodo statico per creare una prenotazione che controlla se l'ora è valida
    public static Prenotazione creaPrenotazione(Ora ora, String nome, String cognome){
        if(ora.isBefore(ThreadTempo.ORARIO_APERTURA)){
            System.out.println("La prenotazione di " + nome + " " + cognome + " non e' valida!");
            return null;
        }

        return new Prenotazione(ora, nome, cognome);
    }

    //Getter
    public Ora getOraAppuntamento() {
        return oraAppuntamento;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    //Metodo che controlla se due prenotazioni sono uguali
    public boolean isEqual(Prenotazione p){
        if(this.getNome().equals(p.getNome()) && this.getCognome().equals(p.getCognome()) && this.getOraAppuntamento().equals(p.getOraAppuntamento())){
            return true;
        }
        return false;
    }

    // Setter for cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //Funzione che controlla se un appuntamento 'a' viene prima di un altro
    public boolean isBefore(Prenotazione a) {

        if (a == null) {
            System.out.println("Prenotazione non valida");
            return false;
        }

        return this.getOraAppuntamento().isBefore(a.getOraAppuntamento());
    }

    @Override
    public String toString() {
        if(getOraAppuntamento() != null)
            return "(" + getNome() + " " + getCognome() + ", " + getOraAppuntamento().toString() +")";

        return "(" + getNome() + " " + getCognome() + ", " + getOraAppuntamento().toString() + ")";
    }

}
