public class Node {
    //Attributi
    private Prenotazione Dati;
    private Node Link;

    //Costruttore
    public Node(Prenotazione dati){
        Dati = dati;
        Link = null;
    }

    //getter e setter
    public Prenotazione getDati (){
        return Dati;
    }
    public void setLink (Node link){
        Link = link;
    }
    public Node getLink (){
        return Link;
    }

}
