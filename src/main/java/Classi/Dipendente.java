package Classi;

import java.util.Date;

public class Dipendente extends Persona {
    public tipo_persona tipo_persona;
    private float stipendio;
    private Date data_scadenza_contratto;
    private Date data_assunzione;


    public Dipendente(String nome, String cognome, Date dataNascita, String email, String password, String codiceFiscale, Genere genere, tipo_persona tipopersona, float stipendio, Date data_scadenza_contratto, Date data_assunzione) {
        super(nome, cognome, dataNascita, email, password, codiceFiscale, tipopersona, genere);
        this.stipendio = stipendio;
        this.data_scadenza_contratto = data_scadenza_contratto;
        this.data_assunzione = data_assunzione;

    }

    // Getter e Setter
    public float getStipendio() {
        return stipendio;
    }

    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }

    public Date getData_scadenza_contratto() {
        return data_scadenza_contratto;
    }

    public void setData_scadenza_contratto(Date data_scadenza_contratto) {
        this.data_scadenza_contratto = data_scadenza_contratto;
    }

    public Date getData_assunzione() {
        return data_assunzione;
    }

    public void setData_assunzione(Date data_assunzione) {
        this.data_assunzione = data_assunzione;
    }

}

