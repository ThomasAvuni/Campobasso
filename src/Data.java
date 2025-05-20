public class Data {
    //Attributi
    private int giorno, mese, anno;
    private Ora orario;

    //Costruttore con tutti gli attributi
    public Data(int g, int m, int a, Ora ora){
        giorno = g;
        mese = m;
        anno = a;
        orario = ora;
    }

    //Costruttore senza ore e minuti
    public Data(int g, int m, int a){
        giorno = g;
        mese = m;
        anno = a;
    }

    //Funzione che controlla se due date sono uguali;
    public boolean dateUguali(Data d1, Data d2){
        if(d1.getGiorno() == d2.getGiorno() && d1.getMese() == d2.getMese() && d1.getAnno() == d2.getAnno())
               { return true; }

        return false;
    }

    //Getter
    public int getGiorno() {
        return giorno;
    }

    public int getMese() {
        return mese;
    }

    public int getAnno() {
        return anno;
    }

    // Setter
    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Ora getOrario() {
        return orario;
    }

    public void setOrario(Ora orario) {
        this.orario = orario;
    }

    public String toString(){
        if(getOrario() != null)
            return "[" + getGiorno() + "/" + getMese() + "/" + getAnno() + getOrario().toString() + "]";

        return "[" + getGiorno() + "/" + getMese() + "/" + getAnno() + "]";
    }
    
}
