package Classi;
import java.util.Date;

public class Persona {
    protected String nome;
    protected String cognome;
    private Date dataNascita;
    private String email;
    private String password;
    public String cod_fiscale;
    private tipo_persona tipopersona;
    public Genere Genere;

    // Costruttore di Persona
    public Persona(String nome, String cognome, Date dataNascita, String email, String password, String cod_fiscale, tipo_persona tipopersona, Genere Genere) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.cod_fiscale = cod_fiscale;
        this.tipopersona = tipopersona;
        this.Genere = Genere;
    }
    public Persona(String codfiscale) {
        this.cod_fiscale = codfiscale;
    }
    // Metodo per impostare la password con una lunghezza minima
    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("La password deve essere di almeno 8 caratteri!");
        }
        this.password = password;
    }


    public String getPassword() {
        return this.password;
    }

    public String getCod_fiscale() {
        return this.cod_fiscale;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getDataNascita() {
        return this.dataNascita;
    }

    public tipo_persona getTipoPersona() {  // Restituisce l'enum, non Byte!
        return this.tipopersona;
    }

    public Genere getGenere() {
        return this.Genere;
    }

}
