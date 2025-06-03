package Classi;

public class Spedizione {
    public String codice_spedizione;
    public Persona CodiceFiscale_Fattorino;
    public Fattorino Tipo_Trasporto;
    public boolean SpedizioneEffettuata;
    public float pesoSpedizione;
    public String regioneSpedizione;
    public boolean SpedizioneConclusa;

    public Spedizione(String codice_spedizione, Persona CodiceFiscale_Fattorino, Fattorino  Tipo_Trasporto, float pesoSpedizione, String regioneSpedizione, boolean SpedizioneEffettuata) {
        this.codice_spedizione = codice_spedizione;
        this.CodiceFiscale_Fattorino=CodiceFiscale_Fattorino;
        this.Tipo_Trasporto=Tipo_Trasporto;
        this.pesoSpedizione = pesoSpedizione;
        this.regioneSpedizione = regioneSpedizione;
        this.SpedizioneEffettuata = SpedizioneEffettuata;
        this.SpedizioneConclusa= SpedizioneConclusa;
    }
    public Spedizione(String codicespedizione) {
        this.codice_spedizione = codicespedizione;
    }

    public String getCodice_spedizione() {
        return codice_spedizione;
    }
}
