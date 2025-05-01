import java.util.Vector;

public class Chef extends Dipendente{
    Vector<Cibo> cibiDaPreparare;
    public Chef(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        cibiDaPreparare = new Vector<Cibo>();
        this.ruolo = "Chef";
    }
    public void Cucina (){
        for (int i = 0; i < cibiDaPreparare.size() ; i++){
            if(cibiDaPreparare.elementAt(i) == null){
                System.out.println("Il cibo da preparare e' nullo");
                continue;
            }
            else
                System.out.println("Il cibo da preparare e' " + cibiDaPreparare.elementAt(i).getNome());
            if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pasta".toLowerCase())){
                if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("zucchine".toLowerCase())){
                    Sala.getCameriere().AddPiattoPronto(new Piatto("Pasta e zucchine"));
                }
                else if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pomodoro".toLowerCase())){
                    Sala.getCameriere().AddPiattoPronto(new Piatto("Pasta al pomodoro"));
                }
            }
            else if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("carne".toLowerCase())){
                Sala.getCameriere().AddPiattoPronto(new Piatto("Carne alla griglia"));
                
            }
            else{
                MainController.getRC().OrdinazioneSbagliata(new String("Il piatto " + cibiDaPreparare.elementAt(i).getNome() + " non e' nel menu!"));
            }

        }
    }

    public void AddCiboInCoda(Cibo cibo) {
        cibiDaPreparare.addElement(cibo);
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(3000);
    }

}