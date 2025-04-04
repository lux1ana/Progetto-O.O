package Classi;

import java.util.Date;


public class Magazziniere extends Persona {
    public Tipo_Trasporto Veicolo_Utilizzabile;
    public Magazzino numeroMagazzino;


    public Magazziniere(String nome, String cognome, Date dataNascita, String email, String password, String CodiceFiscale, TipoPersona tipoPersona, Genere genere, Magazzino numeroMagazzino) {

        super(nome, cognome, dataNascita, email, password, CodiceFiscale, tipoPersona, genere);

        this.numeroMagazzino = numeroMagazzino;
    }

    public int getNumeroMagazzino() {
        return numeroMagazzino.numeroMagazzino;
    }
}