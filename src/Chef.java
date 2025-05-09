
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
                continue;
            }
            
            System.out.println("Preparazione del piatto " + cibiDaPreparare.elementAt(i).getNome());

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
        }
    }

    public void AddCiboInCoda(Cibo cibo) {
        if(cibo == null){
            return;
        }
        if(cibo.getQuantita() >= 1){
            for (int i = 0; i < cibo.getQuantita(); i++){
                Cibo ciboDaAggiungere = new Cibo(cibo.getNome(), 1);
                cibiDaPreparare.addElement(ciboDaAggiungere);
            }
            return;
        }
    }

    @Override
    public void calcolaStipendio() {
        setStipendio(3000);
    }

}