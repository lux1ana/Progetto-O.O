package Classi;


public class Merce {
    public String Nome_prodotto;
    public String Casa_Produttrice;
    public Float Peso;
    public String Descrizione;
    public Float Prezzo;
    public Tipo_Merce Tipo_Merce;
    public String Codice_Prodotto;

    public Merce(String Nome_prodotto, String Casa_Produttrice, Float Peso, String Descrizione, Float Prezzo, Tipo_Merce Tipo_Merce, String Codice_Prodotto) {
        this.Nome_prodotto = Nome_prodotto;
        this.Casa_Produttrice = Casa_Produttrice;
        this.Peso = Peso;
        this.Descrizione = Descrizione;
        this.Prezzo = Prezzo;
        this.Tipo_Merce = Tipo_Merce;
        this.Codice_Prodotto = Codice_Prodotto;
    }

    public String getCodice_Prodotto() {
        return Codice_Prodotto;
    }
}
