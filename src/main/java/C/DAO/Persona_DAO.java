package C.DAO;

import Classi.Persona;
import Classi.TipoPersona;
import Classi.Genere;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Persona_DAO {
    private Connection conn;

    // Costruttore per ricevere la connessione
    public Persona_DAO(Connection conn) {
        this.conn = conn;
    }

public void aggiungiPersona(Persona persona) {
    String sql = "INSERT INTO Persona (CodiceFiscale, Nome, Cognome, Email, Pw, DataDiNascita, TipoPersona, Genere) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, persona.getCodiceFiscale());
        stmt.setString(2, persona.getNome());
        stmt.setString(3, persona.getCognome());
        stmt.setString(4, persona.getEmail());
        stmt.setString(5, persona.getPassword()); // Nota: Attenzione a non salvare la password in chiaro!
        stmt.setDate(6, new java.sql.Date(persona.getDataNascita().getTime())); // Converte Date in SQL Date
        stmt.setString(7, persona.getTipoPersona().toString()); // Enum in Stringa
        stmt.setString(8, persona.Genere.toString());

        stmt.executeUpdate();
        System.out.println("Persona aggiunta con successo!");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Metodo per ottenere una Persona tramite Codice Fiscale
public Persona getPersonaByCodiceFiscale(String codiceFiscale) {
    String sql = "SELECT * FROM Persona WHERE CodiceFiscale = ?";
    Persona persona = null;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, codiceFiscale);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            persona = new Persona(
                    rs.getString("Nome"),
                    rs.getString("Cognome"),
                    rs.getDate("DataDiNascita"),
                    rs.getString("Email"),
                    rs.getString("Pw"),
                    rs.getString("CodiceFiscale"),
                    TipoPersona.valueOf(rs.getString("TipoPersona")),
                    Genere.valueOf(rs.getString("Genere"))
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return persona;
}

// Metodo per ottenere tutte le persone nel database
public List<Persona> getTutteLePersone() {
    String sql = "SELECT * FROM Persona";
    List<Persona> listaPersone = new ArrayList<>();

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Persona persona = new Persona(
                    rs.getString("Nome"),
                    rs.getString("Cognome"),
                    rs.getDate("DataDiNascita"),
                    rs.getString("Email"),
                    rs.getString("Pw"),
                    rs.getString("CodiceFiscale"),
                    TipoPersona.valueOf(rs.getString("TipoPersona")),
                    Genere.valueOf(rs.getString("Genere"))
            );
            listaPersone.add(persona);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaPersone;
}

// Metodo per aggiornare un'email
public void aggiornaEmail(String codiceFiscale, String nuovaEmail) {
    String sql = "UPDATE Persona SET Email = ? WHERE CodiceFiscale = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nuovaEmail);
        stmt.setString(2, codiceFiscale);
        stmt.executeUpdate();
        System.out.println("Email aggiornata con successo!");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Metodo per eliminare una persona dal database
public void eliminaPersona(String codiceFiscale) {
    String sql = "DELETE FROM Persona WHERE CodiceFiscale = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, codiceFiscale);
        stmt.executeUpdate();
        System.out.println("Persona eliminata con successo!");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}


