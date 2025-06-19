package Classi;
import Classi.Merce;
import Classi.Magazzino;

//La classe Immagazzinata è la classe utilizzata per la visualizzazione dei singoli prodotti all'interno del magazzino.

public class Immagazzinata {
    private int quantita;        // Quantità dell'articolo immagazzinato
    public String Codice_Prodotto;
    private int numeroMagazzino;

    public Immagazzinata(Merce merce, Magazzino magazzino, int quantita) {

        this.quantita = quantita;
        this.Codice_Prodotto = merce.getCodice_Prodotto();
        this.numeroMagazzino = magazzino.getNumeroMagazzino();
    }

    public void setMagazzino(Magazzino magazzino) {
        this.numeroMagazzino = magazzino.getNumeroMagazzino();
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