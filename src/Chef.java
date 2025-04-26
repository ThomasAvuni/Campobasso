import java.util.Vector;

public class Chef extends Dipendente{
    private Cibo cibo;
    private Piatto piattoPronto;
    Vector<Cibo> cibiDaPreparare;
    public Chef(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        cibiDaPreparare = new Vector<Cibo>();
        this.ruolo = "Chef";
    }
    public void Cucina (){
        for (int i = 0; i < cibiDaPreparare.size() ; i++){
            if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pasta".toLowerCase())){
                if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("zucchine".toLowerCase())){
                    piattoPronto = new Piatto("Pasta e zucchine");
                    piattoPronto.setPronto(true);
                }
                else if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pomodoro".toLowerCase())){
                    piattoPronto= new Piatto("Pasta al pomodoro");
                    piattoPronto.setPronto(true);
                }
            }
            else if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("carne".toLowerCase())){
                piattoPronto = new Piatto("Carne alla griglia");
                piattoPronto.setPronto(true);
                
            }
            else{
            MainController.getRC().OrdinazioneSbagliata(new String("Il piatto ordinato non e' nel menu"));
            }

        }
    }

    public void AddCiboInCoda (Cibo cibo) {
        for (int i = 0; i < cibiDaPreparare.size(); i ++){
            cibiDaPreparare.addElement(this.cibo);
        }
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(3000);
    }
    
    public Piatto getPiattoPronto() {
        return piattoPronto;
    }


}