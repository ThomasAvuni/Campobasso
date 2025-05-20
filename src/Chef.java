
import java.util.Vector;

public class Chef extends Dipendente{
    //Vettore di cibi da preparare
    Vector<Cibo> cibiDaPreparare;
    private int ordini = 0;

    //Costruttore
    
    public Chef(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        cibiDaPreparare = new Vector<Cibo>();
        this.ruolo = "Chef";
    }

    public void Cucina (){
        //Iterazione sui cibi da preparare
        for (int i = 0; i < cibiDaPreparare.size() ; i++){
            if(cibiDaPreparare.elementAt(i) == null){
                continue;
            }
            
            System.out.println("Ordini: " + ordini);
            System.out.println("Cibo: " + cibiDaPreparare.elementAt(i).getNome());
            
            //Controllo se il cibo è un piatto da preparare
            //Se il cibo è un piatto da preparare lo aggiungo alla lista di piatti pronti del cameriere
            if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pasta".toLowerCase())){
                if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("zucchine".toLowerCase())){
                    Sala.getCameriere().AddPiattoPronto(new Piatto("Pasta e zucchine", cibiDaPreparare.elementAt(i).getCliente()));
                }
                
                else if(cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("pomodoro".toLowerCase())){
                    Sala.getCameriere().AddPiattoPronto(new Piatto("Pasta al pomodoro", cibiDaPreparare.elementAt(i).getCliente()));
                }
            }

            else if (cibiDaPreparare.elementAt(i).getNome().toLowerCase().contains("carne".toLowerCase())){
                Sala.getCameriere().AddPiattoPronto(new Piatto("Carne alla griglia", cibiDaPreparare.elementAt(i).getCliente()));
            }
            
            //Dimunisco il numero di ordini
            ordini--;
        }
        if(ordini == 0){
            System.out.println("Non ci sono cibi da preparare");
            //Se non ci sono più ordini il cameriere porta i piatti
            Sala.getCameriere().portaPiatti();
        }
    }

    public void AddCiboInCoda(Cibo cibo) {
        if(cibo == null){
            return;
        }
        if(cibo.getQuantita() >= 1){
            //Itero per la quantità di cibo ordinato
            for (int i = 0; i < cibo.getQuantita(); i++){
                Cibo ciboDaAggiungere = new Cibo(cibo.getNome(), 1, cibo.getCliente());
                //Aggiungo il cibo alla lista di cibi da preparare
                cibiDaPreparare.addElement(ciboDaAggiungere);
                //Aumento il numero di ordini
                ordini++;
            }
            return;
        }
    }

    @Override
    public void calcolaStipendio() {
        //Funzione che setta lo stipendio del cameriere
        setStipendio(3000);
    }

}