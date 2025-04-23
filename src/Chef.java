

public class Chef extends Dipendente{
    private Cibo cibo;
    private Piatto piattoPronto;
    public Chef(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        this.ruolo = "Chef";
    }
    
    // cucina
    public void cucina(){
        if(cibo.getNome().toLowerCase().contains("pasta".toLowerCase())){
            if(cibo.getNome().toLowerCase().contains("zucchine".toLowerCase())){
                piattoPronto = new Piatto("Pasta e zucchine");
                piattoPronto.setPronto(true);
            }
            else if(cibo.getNome().toLowerCase().contains("pomodoro".toLowerCase())){
                piattoPronto= new Piatto("Pasta al pomodoro");
                piattoPronto.setPronto(true);
            }
        }
        else if (cibo.getNome().toLowerCase().contains("carne".toLowerCase())){
            piattoPronto = new Piatto("Carne alla griglia");
            piattoPronto.setPronto(true);
        }
        else{
            System.out.println("Piatto non disponbile");
        }
    }

    public void setCibo(Cibo cibo) {
        this.cibo = cibo;
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(3000);
    }
    
    public Piatto getPiattoPronto() {
        return piattoPronto;
    }
}