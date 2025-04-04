package Classi;
import java.util.Date;

public class Persona {
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String email;
    private String password;
    public String CodiceFiscale;
    private TipoPersona tipoPersona;
    public Genere Genere;

    // Costruttore di Persona
    public Persona(String nome, String cognome, Date dataNascita, String email, String password, String CodiceFiscale, TipoPersona tipoPersona, Genere Genere) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.CodiceFiscale = CodiceFiscale;
        this.tipoPersona = tipoPersona;
        this.Genere = Genere;
    }

    // Metodo per impostare la password con una lunghezza minima
    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("La password deve essere di almeno 8 caratteri!");
        }
        this.password = password;
    }

    // Metodo per ottenere la password modo "criptato"
    public String getPassword() {
        return "********"; // Per non mostrare la password reale
    }

    public String getCodiceFiscale() {
        return this.CodiceFiscale;
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

    public TipoPersona getTipoPersona() {  // Restituisce l'enum, non Byte!
        return this.tipoPersona;
    }

    public Genere getGenere() {
        return this.Genere;
    }

}
