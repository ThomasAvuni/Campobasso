
import java.util.Vector;

public class Chef extends Dipendente{
    Vector<Cibo> cibiDaPreparare;
    private int ordini = 0;

    public Chef(String nome, String cognome, Data dataNascita) {
        super(nome, cognome, dataNascita);
        cibiDaPreparare = new Vector<Cibo>();
        this.ruolo = "Chef";
    }

    public void Cucina (){
        for (int i = 0; i < cibiDaPreparare.size() ; i++){
            if(cibiDaPreparare.elementAt(i) == null){
                continue;
            }
            
            System.out.println("Ordini: " + ordini);
            System.out.println("Cibo: " + cibiDaPreparare.elementAt(i).getNome());
            
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

            ordini--;
        }
        if(ordini == 0){
            System.out.println("Non ci sono cibi da preparare");
            Sala.getCameriere().portaPiatti();
        }
    }

    public void AddCiboInCoda(Cibo cibo) {
        if(cibo == null){
            return;
        }
        if(cibo.getQuantita() >= 1){
            for (int i = 0; i < cibo.getQuantita(); i++){
                Cibo ciboDaAggiungere = new Cibo(cibo.getNome(), 1, cibo.getCliente());
                cibiDaPreparare.addElement(ciboDaAggiungere);
                ordini++;
            }
            return;
        }
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(3000);
    }

}