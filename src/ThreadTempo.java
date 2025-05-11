public class ThreadTempo extends Thread{
    //Attrubuti della classe
    private int ms;
    private Ora ora;
    private Data data;
    public static final Ora ORARIO_APERTURA = new Ora(19, 00, 00);
    private boolean pausa = false;

    public ThreadTempo() {
        super();
        ora = ORARIO_APERTURA;
        data = new Data(1, 1, 2025);
    }
    
    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean getPausa() {
        return pausa;
    }

    //Getter
    public Ora getOra(){
        return ora;
    }
 
    public String getTempo() {
        return "[" + getOra().ora + ":" + getOra().minuti + "]";
    }

    //Funzione che calcola minuti, secondi e ore
    @Override
    public void run() {
        //Richiamare il metodo run della superclasse
        super.run();
        while(true){
            if(pausa){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                }
                continue;
            }
            else{
            
            //Incremento i millisecondi
            ms++;
            //Quando i millisecondi sono maggiori di 10 si incrementano i secondi
            if(ms >= 5){
                ora.secondi++;
                ms = 0;
            }
            //Se i secondi sono maggiori di 60 si incrementano i minuti
            if(ora.secondi >= 60){
                ora.minuti++;
                ora.secondi = 0;
            }

            //Se i minuti sono maggiori di 60 si incrementano le ore
            if(ora.minuti >= 60){
                ora.ora++;
                ora.minuti = 0;
            }

            if(ora.ora >= 23){
                System.out.println("Nuova giornata");
                ora = new Ora(19, 00, 00);
                data.setGiorno(data.getGiorno() + 1);
                ms = 0;
                Azienda.getDirettore().pagaStipendi();
                continue;
            }

            if(data.getGiorno() > 31){
                System.out.println("Nuovo mese");
                data.setMese(data.getMese() + 1);
                data.setGiorno(1);
            }

            //Fermare il thread 
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {

            }
        }
        }
    }
}