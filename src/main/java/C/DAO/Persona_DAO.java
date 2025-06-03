package C.DAO;

import Classi.Persona;
import Classi.tipo_persona;
import Classi.Genere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Persona_DAO {
    private Connection conn;

    public Persona_DAO(Connection conn) {
        this.conn = conn;
    }

    public void aggiungiPersona(Persona persona) {
        String sql = "INSERT INTO persona (cod_fiscale, nome, cognome, email, pw, data_di_nascita, tipo_persona, genere) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getCod_fiscale());
            stmt.setString(2, persona.getNome());
            stmt.setString(3, persona.getCognome());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getPassword());
            stmt.setDate(6, new java.sql.Date(persona.getDataNascita().getTime()));
            stmt.setString(7, persona.getTipoPersona().toString());
            stmt.setString(8, persona.getGenere().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Persona findByEmail(String email) {
        Persona persona = null;
        String query = "SELECT nome, cognome, data_di_nascita AS dataNascita, email, pw AS password, " +
                "cod_fiscale AS codiceFiscale, tipo_persona AS tipoPersona, genere " +
                "FROM persona WHERE email = ?";

        try (Connection conn = Database_DAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                persona = new Persona(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("dataNascita"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("codiceFiscale"),
                        convertiTipoPersona(rs.getString("tipoPersona")),
                        Genere.valueOf(rs.getString("genere"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public Persona getPersonaByCodiceFiscale(String codiceFiscale) {
        Persona persona = null;
        String sql = "SELECT nome, cognome, data_di_nascita AS dataNascita, email, pw AS password, " +
                "cod_fiscale AS codiceFiscale, tipo_persona AS tipoPersona, genere " +
                "FROM persona WHERE cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                persona = new Persona(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("dataNascita"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("cod_fiscale"),
                        convertiTipoPersona(rs.getString("tipoPersona")),
                        Genere.valueOf(rs.getString("genere"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public List<Persona> getTutteLePersone() {
        List<Persona> listaPersone = new ArrayList<>();
        String sql = "SELECT nome, cognome, data_di_nascita AS dataNascita, email, pw AS password, " +
                "cod_fiscale, tipo_persona AS tipoPersona, genere FROM persona";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("dataNascita"),
                        rs.getString("email"),
                        rs.getString("pw"),
                        rs.getString("cod_fiscale"),
                        convertiTipoPersona(rs.getString("tipoPersona")),
                        Genere.valueOf(rs.getString("genere"))
                );
                listaPersone.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPersone;
    }

    public void aggiornaEmail(String cod_fiscale, String nuovaEmail) {
        String sql = "UPDATE persona SET email = ? WHERE cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuovaEmail);
            stmt.setString(2, cod_fiscale);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminaPersona(String cod_fiscale) {
        String sql = "DELETE FROM persona WHERE cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cod_fiscale);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private tipo_persona convertiTipoPersona(String dbValue) {
        if (dbValue == null) return null;

        try {

            String normalized = dbValue.trim().toUpperCase();
            if (normalized.equals("ADMIN")) {
                return tipo_persona.Dipendente;
            }
            return tipo_persona.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            System.err.println("Valore non previsto per tipo_persona: " + dbValue);
            return null;
        }
    }
}