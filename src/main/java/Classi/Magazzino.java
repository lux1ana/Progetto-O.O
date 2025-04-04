package Classi;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Magazzino {
    // Mappa per collegare le regioni ai numeri di magazzino
    private static final Map<String, Integer> REGIONE_TO_MAGAZZINO = new HashMap<>();

    static {
        REGIONE_TO_MAGAZZINO.put("Campania", 1);
        REGIONE_TO_MAGAZZINO.put("Puglia", 1);
        REGIONE_TO_MAGAZZINO.put("Basilicata", 1);
        REGIONE_TO_MAGAZZINO.put("Calabria", 1);

        REGIONE_TO_MAGAZZINO.put("Lazio", 2);
        REGIONE_TO_MAGAZZINO.put("Abruzzo", 2);
        REGIONE_TO_MAGAZZINO.put("Molise", 2);

        REGIONE_TO_MAGAZZINO.put("Emilia Romagna", 3);
        REGIONE_TO_MAGAZZINO.put("Toscana", 3);
        REGIONE_TO_MAGAZZINO.put("Marche", 3);
        REGIONE_TO_MAGAZZINO.put("Umbria", 3);

        REGIONE_TO_MAGAZZINO.put("Lombardia", 4);
        REGIONE_TO_MAGAZZINO.put("Piemonte", 4);
        REGIONE_TO_MAGAZZINO.put("Valle D'Aosta", 4);
        REGIONE_TO_MAGAZZINO.put("Friuli Venezia Giulia", 4);
        REGIONE_TO_MAGAZZINO.put("Liguria", 4);
        REGIONE_TO_MAGAZZINO.put("Trentino Alto Adige", 4);
        REGIONE_TO_MAGAZZINO.put("Veneto", 4);
    }

    private String nome;
    private String codiceFiscale;
    private int capienzaMassima;
    public int capienzaDisponibile;
    private String numero;
    private String citta;
    private String provincia;
    private String stato;
    private String nomeVia;
    private String regione;
    public int numeroMagazzino;

    // Costruttore della classe Magazzino
    public Magazzino(String nome,int numeroMagazzino, int capienzaMassima, int capienzaDisponibile, String numero, String citta, String provincia, String stato, String nomeVia, String regione) {
        this.nome = nome;
        this.capienzaMassima = capienzaMassima;
        this.capienzaDisponibile = capienzaDisponibile;
        this.numero = numero;
        this.citta = citta;
        this.provincia = provincia;
        this.stato = stato;
        this.regione = regione;
        this.nomeVia = nomeVia;

        // Assegnare il numero del magazzino in base alla regione
        this.numeroMagazzino = REGIONE_TO_MAGAZZINO.getOrDefault(regione, 0);  // 0 se la regione non è trovata
    }


    // Metodo per aggiungere oggetti al magazzino
    public void aggiungiOggetti() {
        Scanner scanner = new Scanner(System.in);  // Creiamo un oggetto Scanner per leggere l'input da tastiera

        System.out.print("Quanti oggetti si vogliono aggiungere? ");
        int numeroOggetti = scanner.nextInt();  // Legge il numero di oggetti da aggiungere

        if (capienzaDisponibile == 0) {
            System.out.println("Il magazzino è pieno, non puoi aggiungere altri oggetti.");
            return;  // Termina il metodo se il magazzino è pieno
        }

        if (numeroOggetti > capienzaDisponibile) {
            System.out.println("Non c'è abbastanza spazio nel magazzino per aggiungere " + numeroOggetti + " oggetti.");
            return;  // Termina il metodo se non c'è abbastanza spazio
        }

        // Se c'è abbastanza spazio, aggiungiamo gli oggetti e aggiorniamo la capienza disponibile
        capienzaDisponibile -= numeroOggetti;  // Riduciamo la capienza disponibile

        System.out.println("Aggiunti " + numeroOggetti + " oggetti al magazzino. Spazio restante: " + capienzaDisponibile);
    }

    public int getNumeroMagazzino() {
        return numeroMagazzino;
    }

    public void setNumeroMagazzino(int numeroMagazzino) {
        this.numeroMagazzino = numeroMagazzino;
    }

    public Magazzino(int numeroMagazzino) {
        this.numeroMagazzino = numeroMagazzino;
    }

    public String getNome() {
        return nome;
    }

    public int getCapienzaMassima() {
        return capienzaMassima;
    }

    public String getNumero() {
        return numero;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getStato() {
        return stato;
    }

    public String getNomeVia() {
        return nomeVia;
    }
    public String getRegione(){
        return regione;
    }
}