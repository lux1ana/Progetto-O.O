package C.DAO;

import Classi.Dipendente;
import Classi.TipoDipendente;
import Classi.Genere;
import Classi.TipoPersona;
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
        String sql = "INSERT INTO Dipendente (CodiceFiscale, Nome, Cognome, Email, Pw, DataDiNascita, TipoPersona, Genere, Stipendio, Data_Assunzione, Data_Scadenza_Contratto, Tipo_Dipendente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dipendente.getCodiceFiscale());
            stmt.setString(2, dipendente.getNome());
            stmt.setString(3, dipendente.getCognome());
            stmt.setString(4, dipendente.getEmail());
            stmt.setString(5, dipendente.getPassword());
            stmt.setDate(6, new java.sql.Date(dipendente.getDataNascita().getTime()));
            stmt.setString(7, dipendente.getTipoPersona().toString());
            stmt.setString(8, dipendente.getGenere().toString());
            stmt.setFloat(9, dipendente.getStipendio());
            stmt.setDate(10, new java.sql.Date(dipendente.getDataAssunzione().getTime()));
            stmt.setDate(11, dipendente.getDataScadenzaContratto() != null ? new java.sql.Date(dipendente.getDataScadenzaContratto().getTime()) : null);
            stmt.setString(12, dipendente.getTipoDipendente().toString());

            stmt.executeUpdate();
            System.out.println("Dipendente aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per ottenere un Dipendente tramite Codice Fiscale
    public Dipendente getDipendenteByCodiceFiscale(String codiceFiscale) {
        String sql = "SELECT * FROM Dipendente WHERE CodiceFiscale = ?";
        Dipendente dipendente = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dipendente = new Dipendente(
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getDate("DataDiNascita"),
                        rs.getString("Email"),
                        rs.getString("Pw"),
                        rs.getString("CodiceFiscale"),
                        Genere.valueOf(rs.getString("Genere")),
                        TipoPersona.valueOf(rs.getString("TipoPersona")),
                        rs.getFloat("Stipendio"),
                        rs.getDate("Data_Scadenza_Contratto"),
                        rs.getDate("Data_Assunzione"),
                        TipoDipendente.valueOf(rs.getString("Tipo_Dipendente").toUpperCase())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dipendente;
    }

    // Metodo per ottenere tutti i Dipendenti
    public List<Dipendente> getTuttiIDipendenti() {
        String sql = "SELECT * FROM Dipendente";
        List<Dipendente> listaDipendenti = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dipendente dipendente = new Dipendente(
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getDate("DataDiNascita"),
                        rs.getString("Email"),
                        rs.getString("Pw"),
                        rs.getString("CodiceFiscale"),
                        Genere.valueOf(rs.getString("Genere")),
                        TipoPersona.valueOf(rs.getString("TipoPersona")),
                        rs.getFloat("Stipendio"),
                        rs.getDate("Data_Scadenza_Contratto"),
                        rs.getDate("Data_Assunzione"),
                        TipoDipendente.valueOf(rs.getString("Tipo_Dipendente").toUpperCase())
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
        String sql = "UPDATE Dipendente SET Stipendio = ? WHERE CodiceFiscale = ?";

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
        String sql = "DELETE FROM Dipendente WHERE CodiceFiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            stmt.executeUpdate();
            System.out.println("Dipendente eliminato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
