public class Cameriere extends Dipendente {
    private Tavolo tavolo; 
    public Cameriere(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        this.ruolo = "Cameriere";
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(1500);
    }

    public void prendiOrdine(Cliente cliente, Cibo cibo) {
        this.tavolo = cliente.getTavolo();
        Azienda.getChef().setCibo(cibo);
        Azienda.getChef().cucina();
    }

    public void portaPiatto() {
        if(Azienda.getChef().getPiattoPronto() != null){
            System.out.println("Porto il piatto " + Azienda.getChef().getPiattoPronto().getNome() + " al tavolo " + tavolo.getNumero());
            Azienda.getChef().getPiattoPronto().setLavato("sporco");
            AiutoCuoco.piattiDaLavare.addElement(Azienda.getChef().getPiattoPronto());
        }
    }
}
