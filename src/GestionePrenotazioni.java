
public class GestionePrenotazioni {
    //Attributi
    private Node head;
    private int numeroPrenotazioni;
    
    //Costruttore
    public GestionePrenotazioni(){
        head = null;
        numeroPrenotazioni = 0;
    }

    //Funzione che restituisce la prima prenotazione(head)
    public Prenotazione getPrimaPrenotazione(){
        if(head == null)
            return null;
        return head.getDati();
    }

    public Prenotazione getUltimaPrenotazione(){
        if(head == null)
            return null;
        Node h = head;
        //Itero fino all'ultimo nodo
        while(h.getLink() != null)
            h = h.getLink(); //Vado avanti nella lista
        //Restituisco l'ultimo nodo
        return h.getDati();
    }

    public boolean controllaPrenDoppia(Prenotazione p){
        Node h = head;
        //Itero la lista
        while(h != null){
            //Variabile di supporto
            Prenotazione p1 = h.getDati();
            //Controllo se la prenotazione è uguale a quella passata come parametro e ritorno true
            if(p1.getOraAppuntamento().isEqual(p.getOraAppuntamento()))
                return true;
            //Vado avanti nella lista
            h = h.getLink();
        }
        //Se non trovo nessuna prenotazione uguale ritorno false
        return false;
    }

    private void inserisciInTesta(Prenotazione p){
        //Creo un nodo con la prenotazione passata come parametro
        Node n = new Node(p);
        //Imposto il nodo come head
        n.setLink(head);
        head = n;
        numeroPrenotazioni++;
    }

    private void inserisciInCoda(Prenotazione p){
        Node h = head;
        Node n = new Node(p);
        //Se la head è null, inserisco in testa
        if(h == null)
            inserisciInTesta(p);
        else{
            //Altrimenti itero fino all'ultimo nodo
            while(h.getLink() != null)
                h = h.getLink();
            
            //Imposto il nodo come ultimo
            n.setLink(null);
            h.setLink(n);
            numeroPrenotazioni++;
        }
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione){
        if(prenotazione == null)
            return;

        //Se la head è null, inserisco in testa
        if(head == null)
            inserisciInTesta(prenotazione);
        else{
            //Se la prenotazione passata come parametro è prima della head, inserisco in testa
            if(prenotazione.isBefore(head.getDati()))
                inserisciInTesta(prenotazione);
            else{
                Node p = head;
                Node pp = head.getLink();
                Node n = new Node(prenotazione);

                //Itero fino a quanto il nodo successivo è diverso da null e la prenotazione passata come parametro è prima della prenotazione successiva
                while(pp != null && pp.getDati().isBefore(prenotazione)){
                    p = pp;
                    pp = p.getLink();
                }
                //Se la prenotazione passata come parametro è dopo l'ultima prenotazione, inserisco in coda
                if (pp == null) {
                    inserisciInCoda(prenotazione);
                }
                else{
                    //
                    n.setLink(pp);
                    p.setLink(n);
                    numeroPrenotazioni++;
                }
            }
        }
    }

    //Funzione che elimina la prima prenotazione
    private void eliminaInTesta(){
        if(head == null)
            return;
        head = head.getLink();
        numeroPrenotazioni--;
    }

    public void elimina(Prenotazione prenotazione){
        if(head == null)
            return;
        //Controllo se la prenotazione da eliminare è la prima
        if(head.getDati().isEqual(prenotazione)){
            eliminaInTesta();
        }
        else{
            //Altrimenti itero fino a trovare la prenotazione da eliminare
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
