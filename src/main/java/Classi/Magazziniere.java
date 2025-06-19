package Classi;

import java.util.Date;


public class Magazziniere extends Persona {
    public tipologia_trasporto Veicolo_Utilizzabile;
    public Magazzino numeroMagazzino;


    public Magazziniere(String nome, String cognome, Date dataNascita, String email, String password,
                        String CodiceFiscale, tipo_persona tipopersona, Genere genere, Magazzino numeroMagazzino) {

        super(nome, cognome, dataNascita, email, password, CodiceFiscale, tipopersona, genere);

        this.numeroMagazzino = numeroMagazzino;
    }

    public int getNumeroMagazzino() {
        return numeroMagazzino.numeroMagazzino;
    }
}