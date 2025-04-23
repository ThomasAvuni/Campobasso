public class Prenotazione {
    //Attributi
    private Data dataAppuntamento;
    private String nome, cognome;
    Cliente cliente;
    

    //Costruttore
    private Prenotazione(Data data, String nome, String cognome){
        this.dataAppuntamento = data;
        this.nome = nome;
        this.cognome = cognome;
    }

    public static Prenotazione creaPrenotazione(Data data, String nome, String cognome){
        if(data.getOrario().isBefore(ThreadTempo.ORARIO_APERTURA)){
            System.out.println("La prenotazione di " + nome + " " + cognome + " non e' valida!");
            return null;
        }

        return new Prenotazione(data, nome, cognome);
    }

    //Getter
    public Data getDataAppuntamento() {
        return dataAppuntamento;
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

    public boolean isEqual(Prenotazione p){
        if(this.getNome().equals(p.getNome()) && this.getCognome().equals(p.getCognome()) && this.getDataAppuntamento().equals(p.getDataAppuntamento())){
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

        Data data = this.getDataAppuntamento();
        Data altraData = a.getDataAppuntamento();

        if (data.getAnno() < altraData.getAnno() || 
            (data.getAnno() == altraData.getAnno() && data.getMese() < altraData.getMese()) || 
            (data.getAnno() == altraData.getAnno() && data.getMese() == altraData.getMese() && data.getGiorno() < altraData.getGiorno()) || 
            (data.getAnno() == altraData.getAnno() && data.getMese() == altraData.getMese() && data.getGiorno() == altraData.getGiorno() && (data != null && data.getOrario().isBefore(altraData.getOrario())))) 
        {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if(dataAppuntamento.getOrario() != null)
            return "(" + getNome() + " " + getCognome() + ", " + getDataAppuntamento().toString() +")";

        return "(" + getNome() + " " + getCognome() + ", " + getDataAppuntamento().toString() + ")";
    }

}
