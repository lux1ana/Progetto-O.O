package Classi;

import java.util.Date;

public class Ordine {
    public String Codice_Ordine;
    public Date Data_Ordine;
    public String nomeVia;
    public int Numero;
    public String Citta;
    public String Provincia;
    public String Stato;
    public boolean Ordine_Effettuato;
    public float Peso;
    public float Costo;
    public Persona Codice_FiscaleCliente;
    public Spedizione Codice_Spedizione;

    public Ordine (String Codice_Ordine, Date Data_Ordine, String nomeVia, int Numero, String Citta, String Provincia, String Stato, boolean Ordine_Effettuato, float Peso, float Costo, Persona Codice_FiscaleCliente, Spedizione Codice_Spedizione) {
        this.Codice_Ordine = Codice_Ordine;
        this.Data_Ordine = Data_Ordine;
        this.nomeVia = nomeVia;
        this.Numero = Numero;
        this.Citta = Citta;
        this.Provincia = Provincia;
        this.Stato = Stato;
        this.Ordine_Effettuato = Ordine_Effettuato;
        this.Peso = Peso;
        this.Costo = Costo;
        this.Codice_FiscaleCliente = Codice_FiscaleCliente;
        this.Codice_Spedizione = Codice_Spedizione;
    }

}
