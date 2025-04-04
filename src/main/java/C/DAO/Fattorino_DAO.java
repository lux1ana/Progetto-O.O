package C.DAO;

import Classi.Fattorino;
import Classi.Tipo_Trasporto;
import Classi.TipoDipendente;
import Classi.Genere;
import Classi.TipoPersona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Classi.Tipo_Trasporto.*;

public class Fattorino_DAO {
    private Connection conn;

    public Fattorino_DAO(Connection conn) {
        this.conn = conn;
    }

    // Metodo per aggiungere un Fattorino al database
    public void aggiungiFattorino(Fattorino fattorino) {
        String sql = "INSERT INTO Fattorino (CodiceFiscale, Nome, Cognome, Email, Pw, DataDiNascita, TipoPersona, Genere, Stipendio, Data_Assunzione, Data_Scad, disponibilita, veicoloUtilizzabile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fattorino.getCodiceFiscale());
            stmt.setString(2, fattorino.getNome());
            stmt.setString(3, fattorino.getCognome());
            stmt.setString(4, fattorino.getEmail());
            stmt.setString(5, fattorino.getPassword());
            stmt.setDate(6, new Date(fattorino.getDataNascita().getTime()));
            stmt.setString(7, fattorino.getTipoPersona().toString());
            stmt.setString(8, fattorino.getGenere().toString());
            stmt.setFloat(9, fattorino.getStipendio());
            stmt.setDate(10, new Date(fattorino.getDataAssunzione().getTime()));
            stmt.setDate(11, fattorino.getDataScadenzaContratto() != null ? new Date(fattorino.getDataScadenzaContratto().getTime()) : null);
            stmt.setBoolean(12, fattorino.isDisponibile());
            stmt.setString(13, fattorino.getVeicoloUtilizzabile().toString());
            stmt.executeUpdate();
            System.out.println("Fattorino aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per ottenere tutti i fattorini
    public List<Fattorino> getAllFattorini() {
        List<Fattorino> fattorini = new ArrayList<>();
        String sql = "SELECT * FROM Fattorino";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fattorino fattorino = new Fattorino(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("dataNascita"),
                        rs.getString("email"),
                        rs.getString("passord"),
                        rs.getString("codiceFiscale"),
                        Genere.valueOf(rs.getString("genere")),
                        TipoPersona.valueOf(rs.getString("tipoPersona")),
                        rs.getFloat("Stipendio"),
                        rs.getDate("Data_Assunzione"),
                        rs.getDate("Data_Scad"),
                        TipoDipendente.valueOf(rs.getString("tipoDipendente")),
                        rs.getBoolean("disponibilita"),
                        valueOf(rs.getString("veicoloUtilizzabile"))

                );
                fattorini.add(fattorino);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fattorini;
    }

    // Metodo per ottenere un Fattorino per codice fiscale
    public Fattorino getFattorinoByCodiceFiscale(String codiceFiscale) {
        Fattorino fattorino = null;
        String sql = "SELECT * FROM Fattorino WHERE CodiceFiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fattorino = new Fattorino(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("dataNascita"),
                            rs.getString("email"),
                            rs.getString("passord"),
                            rs.getString("codiceFiscale"),
                            Genere.valueOf(rs.getString("genere")),
                            TipoPersona.valueOf(rs.getString("tipoPersona")),
                            rs.getFloat("Stipendio"),
                            rs.getDate("Data_Assunzione"),
                            rs.getDate("Data_Scad"),
                            TipoDipendente.valueOf(rs.getString("tipoDipendente")),
                            rs.getBoolean("disponibilita"),
                            valueOf(rs.getString("veicoloUtilizzabile"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fattorino;
    }
}
