package Classi;

import java.util.Date;

public class Dipendente extends Persona {
    public TipoDipendente TipoDipendente;
    private float stipendio;
    private Date dataScadenzaContratto;
    private Date dataAssunzione;
    private TipoDipendente tipoDipendente;

    public Dipendente(String nome, String cognome, Date dataNascita, String email, String password, String codiceFiscale, Genere genere, TipoPersona tipoPersona, float stipendio, Date dataScadenzaContratto, Date dataAssunzione, TipoDipendente tipoDipendente) {
        super(nome, cognome, dataNascita, email, password, codiceFiscale, tipoPersona, genere);
        this.stipendio = stipendio;
        this.dataScadenzaContratto = dataScadenzaContratto;
        this.dataAssunzione = dataAssunzione;
        this.tipoDipendente = tipoDipendente;
    }

    // Getter e Setter
    public float getStipendio() {
        return stipendio;
    }

    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }

    public Date getDataScadenzaContratto() {
        return dataScadenzaContratto;
    }

    public void setDataScadenzaContratto(Date dataScadenzaContratto) {
        this.dataScadenzaContratto = dataScadenzaContratto;
    }

    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(Date dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public TipoDipendente getTipoDipendente() {
        return tipoDipendente;
    }

    public void setTipoDipendente(TipoDipendente tipoDipendente) {
        this.tipoDipendente = tipoDipendente;
    }
}

