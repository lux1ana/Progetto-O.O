package C.DAO;

import Classi.Magazziniere;
import Classi.Genere;
import Classi.tipo_persona;
import Classi.Magazzino;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Magazziniere_DAO {
    private Connection conn;

    public Magazziniere_DAO(Connection conn) {
        this.conn = conn;
    }

    // Metodo per aggiungere un Magazziniere al database
    public void aggiungiMagazziniere(Magazziniere magazziniere) {
        String sql = "INSERT INTO Magazziniere (CodiceFiscale, Nome, Cognome, Email, Pw, DataDiNascita, TipoPersona, Genere, NumeroMagazzino, Veicolo_Utilizzabile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, magazziniere.getCod_fiscale());
            stmt.setString(2, magazziniere.getNome());
            stmt.setString(3, magazziniere.getCognome());
            stmt.setString(4, magazziniere.getEmail());
            stmt.setString(5, magazziniere.getPassword());
            stmt.setDate(6, new Date(magazziniere.getDataNascita().getTime()));
            stmt.setString(7, magazziniere.getTipoPersona().toString());
            stmt.setString(8, magazziniere.getGenere().toString());
            stmt.setInt(9, magazziniere.numeroMagazzino.getNumeroMagazzino());
            stmt.setString(10, magazziniere.Veicolo_Utilizzabile.toString());

            stmt.executeUpdate();
            System.out.println("Magazziniere aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per ottenere tutti i magazzinieri
    public List<Magazziniere> getAllMagazzinieri() {
        List<Magazziniere> magazzinieri = new ArrayList<>();
        String sql = "SELECT * FROM Magazziniere";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Magazziniere magazziniere = new Magazziniere(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("datNascita"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("CodiceFiscale"),
                        tipo_persona.valueOf(rs.getString("tipoPersona")),
                        Genere.valueOf(rs.getString("genere")),
                        new Magazzino(rs.getInt("numeroMagazzino"))

                );
                magazzinieri.add(magazziniere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazzinieri;
    }

    // Metodo per ottenere un Magazziniere per codice fiscale
    public Magazziniere getMagazziniereByCodiceFiscale(String codiceFiscale) {
        Magazziniere magazziniere = null;
        String sql = "SELECT * FROM Magazziniere WHERE CodiceFiscale = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    magazziniere = new Magazziniere(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("dataNascita"),
                            rs.getString("email"),
                            rs.getString("Password"),
                            rs.getString("CodiceFiscale"),
                            tipo_persona.valueOf(rs.getString("tipoPersona")),
                            Genere.valueOf(rs.getString("Genere")),
                            new Magazzino(rs.getInt("numeroMagazzino"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazziniere;
    }
}
