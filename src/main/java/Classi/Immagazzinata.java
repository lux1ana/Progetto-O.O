package Classi;
public class Immagazzinata {
    private int quantita;        // Quantità dell'articolo immagazzinato
    public String Codice_Prodotto;
    private int numeroMagazzino;

    // Costruttore che accetta direttamente gli oggetti Merce e Magazzino
    public Immagazzinata(Merce merce, Magazzino magazzino, int quantita) {

        this.quantita = quantita;
        this.Codice_Prodotto = merce.getCodice_Prodotto();  // Prendiamo il codice prodotto dalla merce
        this.numeroMagazzino = magazzino.getNumeroMagazzino(); // Prendiamo il numero magazzino
    }

    public void setMagazzino(Magazzino magazzino) {
        this.numeroMagazzino = magazzino.getNumeroMagazzino(); // Aggiorniamo anche il numero magazzino
    }

    public int getQuantita() {
        return quantita;
    }

    public String getCodice_Prodotto() {
        return Codice_Prodotto;
    }

    public int getNumeroMagazzino() {
        return numeroMagazzino;
    }
}