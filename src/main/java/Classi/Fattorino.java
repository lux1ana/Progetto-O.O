package Classi;

import java.util.Date;

public class Fattorino extends Dipendente {
    public boolean disponibilità;
    public tipologia_trasporto veicolo_utilizzabile;

    public Fattorino(String nome, String cognome, Date data_di_nascita, String email, String pw, String cod_fiscale,
                     Genere genere, tipo_persona tipo_persona, float stipendio, Date dataScadenzaContratto,
                     Date dataAssunzione, boolean disponibilità, tipologia_trasporto veicolo_utilizzabile) {
        super(nome, cognome, data_di_nascita, email, pw, cod_fiscale, genere, tipo_persona, stipendio,
                dataScadenzaContratto, dataAssunzione);
        this.disponibilità = disponibilità;
        this.veicolo_utilizzabile = veicolo_utilizzabile;
    }
    @Override
    public String toString() {
        return nome + " " + cognome + " (" + cod_fiscale + ")";
    }

    public boolean isDisponibile() {
        return disponibilità;
    }

    public tipologia_trasporto getVeicolo_utilizzabile() {
        return veicolo_utilizzabile;
    }

    public void setDisponibilità(boolean disponibilità) {
        this.disponibilità = disponibilità;
    }

    public void setVeicolo_utilizzabile(tipologia_trasporto veicolo_utilizzabile) {
        this.veicolo_utilizzabile = veicolo_utilizzabile;
    }
}
