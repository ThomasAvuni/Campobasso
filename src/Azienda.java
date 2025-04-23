import java.util.Scanner;

public class Azienda {
    static GestionePrenotazioni gestionePrenotazioni = new GestionePrenotazioni();
    private static Direttore direttore = new Direttore("Vincenzo", "Siciliano");
    private static Chef chef = new Chef("Gianmarco", "Tocco", new Data(1998, 12, 3));
    private static AiutoCuoco aiutoCuoco = new AiutoCuoco("Francesco", "Marzano", new Data(1990, 5, 20));

    public static Chef getChef() {
        return chef;
    }

    public static AiutoCuoco getAiutoCuoco() {
        return aiutoCuoco;
    }

    public static Direttore getDirettore(){
        return direttore; 
    }

    private void stampaMenu(){
        System.out.println("Menu: ");
        System.out.println("Primo: Pasta al pomodoro");
        System.out.println("Primo: Pasta e zucchine");
        System.out.println("Secondo: Carne alla griglia");
    }

    public void Esegui(){
        Scanner scanner = new Scanner(System.in);
        Sala.RiempiSala();
        direttore.aggiungiDipendente(getAiutoCuoco());
        direttore.aggiungiDipendente(Sala.getCameriere());
        direttore.aggiungiDipendente(getChef());
        System.out.println("────────────────────────────────");
        System.out.println("Direttore: " + direttore.getNome() + " " + direttore.getCognome());
        System.out.println("────────────────────────────────");
        System.out.println("Dipendenti:");
        for(int i = 0; i < direttore.getDipententi().size(); i++){
            System.out.println("────────────────────────────────");
            direttore.getDipententi().elementAt(i).mostraDettagli();
        }
        System.out.println("────────────────────────────────");

        System.out.println("Inserire le prenotazioni:");
        System.out.println("────────────────────────────────");
        String ripeti = "n";
        do { 
            System.out.println("Inserisci nome e cognome:");
            String nome = scanner.nextLine();
            String cognome = scanner.nextLine();
            System.out.println("Inserisci la data della prenotazione:");
            System.out.println("Giorno:");
            int giorno = scanner.nextInt();
            System.out.println("Mese:");
            int mese = scanner.nextInt();
            System.out.println("Ora:");
            int ora = scanner.nextInt();
            System.out.println("Minuti:");
            int minuti = scanner.nextInt();
            scanner.nextLine();
            Cliente cliente = new Cliente(nome, cognome, new Data(giorno, mese, 2025, new Ora(ora, minuti, 0)));
            System.out.println("Vuoi continuare inserire un'altra prenotazione? (s/n)");
            ripeti = scanner.nextLine();
        } while (ripeti.equalsIgnoreCase("s"));
        ThreadTempo tempo = new ThreadTempo();
        tempo.start();
        System.out.println("Inizio servizio");
        while(true){
            System.out.print(ThreadTempo.getTempo() + "\r");
            if(gestionePrenotazioni.getPrenotazioni().isEmpty()){
                System.out.println("Non ci sono prenotazioni in corso...");
                Sala.terminaServizio();
                break;
            }
            for(int i = 0; i < gestionePrenotazioni.getPrenotazioni().size(); i++){
                Prenotazione p = gestionePrenotazioni.getPrenotazioni().elementAt(i);
                if(p.getDataAppuntamento().getOrario().isEqual(ThreadTempo.getOra())){
                    System.out.println("Prenotazione in corso per " + p.getNome() + " " + p.getCognome() + " alle ore " + ThreadTempo.getOra().toString());
                    stampaMenu();
                    System.out.println("Cosa vuoi ordinare?");
                    String scelta = scanner.nextLine();
                    Sala.getCameriere().prendiOrdine(p.getCliente(), new Cibo(scelta));
                    System.out.println("Ordine preso per " + p.getNome() + " " + p.getCognome() + " alle ore " + ThreadTempo.getOra().toString());
                    System.out.println("Il piatto sara' pronto a breve");
                    continue;
                }

                int minutiAttuali = p.getDataAppuntamento().getOrario().minuti;
                int minutiCottura = minutiAttuali + 5;
                if(ThreadTempo.getOra().minuti == minutiCottura){
                    Sala.getCameriere().portaPiatto();
                    gestionePrenotazioni.elimina(p);
                    System.out.println("In attesa di altre prenotazioni...");
                }
            }
        }
    }
}