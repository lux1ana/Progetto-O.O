package Classi;


public class Merce {
    public String Nome_prodotto;
    public String Casa_Produttrice;
    public Float peso_oggetto_kg;
    public String Descrizione;
    public Float costo_$;
    public tipologia tipologia;
    public String Codice_Prodotto;

    public Merce(String Nome_prodotto, String Casa_Produttrice, Float peso_oggetto_kg,
                 String Descrizione, Float costo_$, tipologia tipologia, String Codice_Prodotto) {
        this.Nome_prodotto = Nome_prodotto;
        this.Casa_Produttrice = Casa_Produttrice;
        this.peso_oggetto_kg = peso_oggetto_kg;
        this.Descrizione = Descrizione;
        this.costo_$ = costo_$;
        this.tipologia = tipologia;
        this.Codice_Prodotto = Codice_Prodotto;
    }

    public String getCodice_Prodotto() {
        return Codice_Prodotto;
    }

    public String  getNome_Prodotto() {
        return Nome_prodotto;
    }
}