public class Node {
    private Prenotazione Dati;
    private Node Link;

    public Node(Prenotazione dati){
        Dati = dati;
        Link = null;
    }
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
