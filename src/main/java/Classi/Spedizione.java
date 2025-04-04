package Classi;

public class Spedizione {
    public String CodiceSpedizione;
    public Persona CodiceFiscale_Fattorino;
    public Fattorino Tipo_Trasporto;
    public boolean SpedizioneEffettuata;
    public float pesoSpedizione;
    public String regioneSpedizione;
    public boolean SpedizioneConclusa;

    public Spedizione(String CodiceSpedizione, Persona CodiceFiscale_Fattorino, Fattorino  Tipo_Trasporto, float pesoSpedizione, String regioneSpedizione, boolean SpedizioneEffettuata) {
        this.CodiceSpedizione = CodiceSpedizione;
        this.CodiceFiscale_Fattorino=CodiceFiscale_Fattorino;
        this.Tipo_Trasporto=Tipo_Trasporto;
        this.pesoSpedizione = pesoSpedizione;
        this.regioneSpedizione = regioneSpedizione;
        this.SpedizioneEffettuata = SpedizioneEffettuata;
        this.SpedizioneConclusa= SpedizioneConclusa;
    }

}
