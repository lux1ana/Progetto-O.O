package C.DAO;

import Classi.Dipendente;
import Classi.Genere;
import Classi.tipo_persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dipendente_DAO {
    private Connection conn;

    public Dipendente_DAO(Connection conn) {
        this.conn = conn;
    }

    // Metodo per aggiungere un Dipendente nel database
    public void aggiungiDipendente(Dipendente dipendente) {
        String sql = "INSERT INTO Dipendente (cod_fiscale, nome, cognome, email, pw, data_di_nascita, tipo_persona, genere, stipendio, data_assunzione, data_scadenza_contratto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dipendente.getCod_fiscale());
            stmt.setString(2, dipendente.getNome());
            stmt.setString(3, dipendente.getCognome());
            stmt.setString(4, dipendente.getEmail());
            stmt.setString(5, dipendente.getPassword());
            stmt.setDate(6, new java.sql.Date(dipendente.getDataNascita().getTime()));
            stmt.setString(7, dipendente.getTipoPersona().toString());
            stmt.setString(8, dipendente.getGenere().toString());
            stmt.setFloat(9, dipendente.getStipendio());
            stmt.setDate(10, new java.sql.Date(dipendente.getData_assunzione().getTime()));
            stmt.setDate(11, dipendente.getData_scadenza_contratto() != null ? new java.sql.Date(dipendente.getData_scadenza_contratto().getTime()) : null);

            stmt.executeUpdate();
            System.out.println("Dipendente aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per ottenere un Dipendente tramite Codice Fiscale
    public Dipendente getDipendenteByCodiceFiscale(String codiceFiscale) {
        String sql = "SELECT * FROM Persona WHERE tipo_persona = 'Fattorino' OR 'Magazziniere' AND cod_fiscale = ?";
        Dipendente dipendente = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dipendente = new Dipendente(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_di_nascita"),
                        rs.getString("email"),
                        rs.getString("pw"),
                        rs.getString("cod_fiscale"),
                        Genere.valueOf(rs.getString("Genere")),
                        tipo_persona.valueOf(rs.getString("tipo_persona")),
                        rs.getFloat("stipendio"),
                        rs.getDate("data_scadenza_contratto"),
                        rs.getDate("data_assunzione")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dipendente;
    }

    // Metodo per ottenere tutti i Dipendenti
    public List<Dipendente> getTuttiIDipendenti() {
        String sql = "SELECT * FROM Persona WHERE tipo_persona = 'Fattorino' OR 'Magazziniere'";
        List<Dipendente> listaDipendenti = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dipendente dipendente = new Dipendente(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_di_nascita"),
                        rs.getString("email"),
                        rs.getString("pw"),
                        rs.getString("cod_fiscale"),
                        Genere.valueOf(rs.getString("Genere")),
                        tipo_persona.valueOf(rs.getString("tipo_persona")),
                        rs.getFloat("stipendio"),
                        rs.getDate("data_scadenza_contratto"),
                        rs.getDate("data_assunzione")
                );

                listaDipendenti.add(dipendente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDipendenti;
    }

    // Metodo per aggiornare lo stipendio di un dipendente
    public void aggiornaStipendio(String codiceFiscale, float nuovoStipendio) {
        String sql = "UPDATE Persona SET Stipendio = ? WHERE tipo_persona = 'Fattorino' OR 'Magazziniere' AND cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, nuovoStipendio);
            stmt.setString(2, codiceFiscale);
            stmt.executeUpdate();
            System.out.println("Stipendio aggiornato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per eliminare un dipendente
    public void eliminaDipendente(String codiceFiscale) {
        String sql = "DELETE FROM Persona WHERE tipo_persona = 'Fattorino' OR 'Magazziniere' AND cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            stmt.executeUpdate();
            System.out.println("Dipendente eliminato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
