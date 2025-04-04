package Classi;

import java.util.Date;

public class Fattorino extends Dipendente {
    public boolean disponibilita;
    public Tipo_Trasporto veicoloUtilizzabile;

    public Fattorino(String nome, String cognome, Date dataNascita, String email, String password, String codiceFiscale,
                     Genere genere, TipoPersona tipoPersona, float stipendio, Date dataScadenzaContratto,
                     Date dataAssunzione, TipoDipendente tipoDipendente, boolean disponibilita, Tipo_Trasporto veicoloUtilizzabile) {
        super(nome, cognome, dataNascita, email, password, codiceFiscale, genere, tipoPersona, stipendio,
                dataScadenzaContratto, dataAssunzione, tipoDipendente);
        this.disponibilita = disponibilita;
        this.veicoloUtilizzabile = veicoloUtilizzabile;
    }

    public boolean isDisponibile() {
        return disponibilita;
    }

    public Tipo_Trasporto getVeicoloUtilizzabile() {
        return veicoloUtilizzabile;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public void setVeicoloUtilizzabile(Tipo_Trasporto veicoloUtilizzabile) {
        this.veicoloUtilizzabile = veicoloUtilizzabile;
    }
}
