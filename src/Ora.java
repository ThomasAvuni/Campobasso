public class Ora {
    public int ora, minuti, secondi;
    
    public Ora(int ora, int minuti, int secondi){
        this.ora = ora;
        this.minuti = minuti;
        this.secondi = secondi;
    }

    public Ora(int ora, int minuti){
        this.ora = ora;
        this.minuti = minuti;
    }

    public Ora(Ora ora){
        this.ora = ora.ora;
        this.minuti = ora.minuti;
        this.secondi = ora.secondi;
    }

    public boolean isBefore(Ora other) {
        if(this.ora < other.ora || (this.ora == other.ora && this.minuti < other.minuti) || 
            (this.ora == other.ora && this.minuti == other.minuti && this.secondi < other.secondi)){
                return true;
            }
        return false;
    }

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
