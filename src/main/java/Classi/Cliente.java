package Classi;

import java.util.Date;

public class Cliente extends Persona {
    private String nomeVia;
    private int numero;
    private String città;
    private String regione;
    private String stato;
    private String provincia;
    private String telefono;

    public Cliente(String nome, String cognome, Date dataNascita, String email, String password, String codiceFiscale, Genere genere, tipo_persona tipopersona, String nomeVia, int numero, String citta, String regione, String stato, String provincia, String telefono) {
        super(nome, cognome, dataNascita, email, password, codiceFiscale, tipopersona, genere);
        this.nomeVia = nomeVia;
        this.numero = numero;
        this.città = città;
        this.regione = regione;
        this.stato = stato;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNomeVia() {
        return nomeVia;
    }

    public int getNumero() {
        return numero;
    }

    public String getCitta() {
        return città;
    }

    public String getRegione() {
        return regione;
    }

    public String getStato() {
        return stato;
    }

    public String getProvincia() {
        return provincia;
    }
}