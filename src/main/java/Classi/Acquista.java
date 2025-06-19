package Classi;

import Classi.Merce;
import Classi.Ordine;

//La classe acquista, come immagazzinata, è utilizzata per visualizzare i singoli prodotti acquistati all'interno degli
//ordini.
public class Acquista {
    public Merce Codice_Prodotto;
    public Ordine Codice_Ordine;
    private int Quantita;
    public float Peso;
    public float Costo;
    private int Numero_Magazzino;

    public Acquista (Merce Codice_Prodotto,  Ordine Codice_Ordine, int Quantita, float Peso,
                     float Costo, int Numero_Magazzino) {
        this.Codice_Prodotto = Codice_Prodotto;
        this.Codice_Ordine = Codice_Ordine;
        this.Quantita = Quantita;
        this.Peso = Peso;
        this.Costo = Costo;
        this.Numero_Magazzino = Numero_Magazzino;
    }
}