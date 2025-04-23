public class Dipendente {
    private String nome, cognome;
    protected String ruolo;
    private Data dataNascita;
    private int stipendio;

    public Dipendente(String nome, String cognome, Data dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }

    public Data getDataNascita() {
        return dataNascita;
    }

    public int getStipendio() {
        return stipendio;
    }

    public String getRuolo(){
        return ruolo;
    }

    protected void setStipendio(int stipendio){
        this.stipendio = stipendio;
    }

    public void calcolaStipendio(){
        //Da implementare per sottoclasse
        // stipendio = 9*durata/h
        // chef 3000 | aiuto 1500 | lavapiatti 1100
    }

    public void mostraDettagli(){
        System.out.println("Ruolo: " + ruolo);
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("Data di Nascita: " + dataNascita);
    }
}
