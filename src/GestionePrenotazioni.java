
import java.util.Vector;

public class GestionePrenotazioni {
    private Node head;

    private int numeroPrenotazioni;
    
    public GestionePrenotazioni(){
        head = null;
        numeroPrenotazioni = 0;
    }

    public Vector<Prenotazione> getPrenotazioni() {
        Vector<Prenotazione> prenotazioni = new Vector<Prenotazione>();
        Node p = head;
        while (p != null) {
            prenotazioni.addElement(p.getDati());
            p = p.getLink();
        }
        return prenotazioni;
    }

    public Prenotazione getUltimaPrenotazione() {
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p.getLink() != null) {
            p = p.getLink();
        }
        return p.getDati();
    }

    public void inserisciInTesta(Prenotazione p){
        Node n = new Node(p);
        n.setLink(head);
        head = n;
        numeroPrenotazioni++;
    }

    public void inserisciInCoda(Prenotazione p){
        Node h = head;
        Node n = new Node(p);
        if(h == null)
            inserisciInTesta(p);
        else{
            while(h.getLink() != null)
                h = h.getLink();
            
            n.setLink(null);
            h.setLink(n);
            numeroPrenotazioni++;
        }
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione){
        if(prenotazione == null)
            return;

        if(head == null)
            inserisciInTesta(prenotazione);
        else{
            if(prenotazione.isBefore(head.getDati()))
                inserisciInTesta(prenotazione);
            else{
                Node p = head;
                Node pp = head.getLink();
                Node n = new Node(prenotazione);

                while(pp != null && pp.getDati().isBefore(prenotazione)){
                    p = pp;
                    pp = p.getLink();
                }
                if (pp == null) {
                    inserisciInCoda(prenotazione);
                }
                else{
                    n.setLink(pp);
                    p.setLink(n);
                    numeroPrenotazioni++;
                }
            }
        }
    }

    private void eliminaInTesta(){
        if(head == null)
            return;
        head = head.getLink();
        numeroPrenotazioni--;
    }

    private void eliminaInCoda(){
        if(head == null)
            return;
        if(head.getLink() == null)
            eliminaInTesta();
        else
        {
            Node p = head, pp = head.getLink();
            while(pp.getLink() != null)
            {
                p = pp;
                pp = pp.getLink();
            }
            p.setLink(null);
            numeroPrenotazioni--;
        }
    }

    public void elimina(Prenotazione prenotazione){
        if(head == null)
            return;
        if(head.getDati().isEqual(prenotazione)){
            eliminaInTesta();
        }
        else{
            Node p = head, pp = head.getLink();
            while(pp.getLink() != null && !pp.getDati().isEqual(prenotazione)){
                p = pp;
                pp = pp.getLink();
            }
            if(pp.getDati().isEqual(prenotazione)){
                p.setLink(pp.getLink());
                
                numeroPrenotazioni--;
            }
        }
    }

    public int getNumeroPrenotazioni() {
        return numeroPrenotazioni;
    }

}
