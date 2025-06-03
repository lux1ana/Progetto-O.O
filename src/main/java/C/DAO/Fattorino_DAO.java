package C.DAO;

import Classi.Fattorino;
import Classi.Genere;
import Classi.tipo_persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Classi.tipologia_trasporto.*;

public class Fattorino_DAO {
    private Connection conn;

    public Fattorino_DAO(Connection conn) {
        this.conn = conn;
    }

    // Metodo per aggiungere un Fattorino al database
    public void aggiungiFattorino(Fattorino fattorino) {
        String sql = "INSERT INTO Persona  (cod_fiscale, nome, cognome, email, pw, data_di_nascita, tipo_persona, genere, stipendio, data_assunzione, data_scadenza_contratto, disponibilità, veicolo_utilizzabile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE tipo_persona = 'Fattorino' OR 'Magazziniere'";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fattorino.getCod_fiscale());
            stmt.setString(2, fattorino.getNome());
            stmt.setString(3, fattorino.getCognome());
            stmt.setString(4, fattorino.getEmail());
            stmt.setString(5, fattorino.getPassword());
            stmt.setDate(6, new Date(fattorino.getDataNascita().getTime()));
            stmt.setString(7, fattorino.getTipoPersona().toString());
            stmt.setString(8, fattorino.getGenere().toString());
            stmt.setFloat(9, fattorino.getStipendio());
            stmt.setDate(10, new Date(fattorino.getData_assunzione().getTime()));
            stmt.setDate(11, fattorino.getData_scadenza_contratto() != null ? new Date(fattorino.getData_scadenza_contratto().getTime()) : null);
            stmt.setBoolean(12, fattorino.isDisponibile());
            stmt.setString(13, fattorino.getVeicolo_utilizzabile().toString());
            stmt.executeUpdate();
            System.out.println("Fattorino aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per ottenere tutti i fattorini
    public List<Fattorino> getAllFattorini() {
        List<Fattorino> fattorini = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE tipo_persona = 'Fattorino'";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fattorino fattorino = new Fattorino(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_di_nascita"),
                        rs.getString("email"),
                        rs.getString("pw"),
                        rs.getString("cod_fiscale"),
                        Genere.valueOf(rs.getString("genere")),
                        tipo_persona.valueOf(rs.getString("tipo_persona")),
                        rs.getFloat("stipendio"),
                        rs.getDate("data_assunzione"),
                        rs.getDate("data_scadenza_contratto"),
                        rs.getBoolean("disponibilità"),
                        valueOf(rs.getString("veicolo_utilizzabile"))

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
        String sql = "SELECT * FROM Persona WHERE tipo_persona = 'Fattorino' AND  cod_fiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fattorino = new Fattorino(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("data_di_nascita"),
                            rs.getString("email"),
                            rs.getString("pw"),
                            rs.getString("cod_fiscale"),
                            Genere.valueOf(rs.getString("genere")),
                            tipo_persona.valueOf(rs.getString("tipo_persona")),
                            rs.getFloat("stipendio"),
                            rs.getDate("data_assunzione"),
                            rs.getDate("data_scadenza_contratto"),
                            rs.getBoolean("disponibilità"),
                            valueOf(rs.getString("veicolo_utilizzabile"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fattorino;
    }

    public List<Fattorino> getFattoriniDisponibili() {
        List<Fattorino> fattoriniDisponibili = new ArrayList<>();
        String sql = "SELECT * FROM Persona  WHERE tipo_persona = 'Fattorino' AND  disponibilità = true";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fattorino fattorino = new Fattorino(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_di_nascita"),
                        rs.getString("email"),
                        rs.getString("pw"),
                        rs.getString("cod_fiscale"),
                        Genere.valueOf(rs.getString("genere")),
                        tipo_persona.valueOf(rs.getString("tipo_persona")),
                        rs.getFloat("stipendio"),
                        rs.getDate("data_assunzione"),
                        rs.getDate("data_scadenza_contratto"),
                        rs.getBoolean("disponibilità"),
                        valueOf(rs.getString("veicolo_utilizzabile"))
                );
                fattoriniDisponibili.add(fattorino);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fattoriniDisponibili;
    }

}
