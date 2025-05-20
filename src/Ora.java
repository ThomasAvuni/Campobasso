public class Ora {
    //Attributi
    public int ora, minuti, secondi;
    
    //Costruttore
    public Ora(int ora, int minuti, int secondi){
        this.ora = ora;
        this.minuti = minuti;
        this.secondi = secondi;
    }

    //Costruttore senza secondi
    public Ora(int ora, int minuti){
        this.ora = ora;
        this.minuti = minuti;
    }

    //Costruttore con un oggetto Ora
    public Ora(Ora ora){
        this.ora = ora.ora;
        this.minuti = ora.minuti;
        this.secondi = ora.secondi;
    }

    //Metodo che controlla se l'ora è prima di un'altra
    public boolean isBefore(Ora other) {
        if(this.ora < other.ora || (this.ora == other.ora && this.minuti < other.minuti) || 
            (this.ora == other.ora && this.minuti == other.minuti && this.secondi < other.secondi)){
                return true;
            }
        return false;
    }

    //Metodo che controlla se l'ora è uguale ad un'altra
    public boolean isEqual(Ora other) {
        if(this.ora == other.ora && this.minuti == other.minuti){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + ora + ":" + minuti + "]";
    }
}
