package C.DAO;

import Classi.Magazzino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Magazzino_DAO {
    private Connection conn;

    public Magazzino_DAO(Connection conn) {
        this.conn = conn;
    }

    //Metodo per ottenere tutti i Magazzni
    public List<Magazzino> getAllMagazzini() throws SQLException {
        List<Magazzino> magazzini = new ArrayList<>();
        String query = "SELECT * FROM Magazzino";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Magazzino magazzino = new Magazzino(
                        rs.getString("nome"),
                        rs.getInt("capienzaMassima"),
                        rs.getInt("numeroMagazzino"),
                        rs.getInt("capienzaDisponibile"),
                        rs.getString("numero"),
                        rs.getString("citta"),
                        rs.getString("provincia"),
                        rs.getString("stato"),
                        rs.getString("nomeVia"),
                        rs.getString("regione")
                );
                magazzini.add(magazzino);
            }
        }
        return magazzini;
    }

    //Metodo per inserire un Magazzino all'interno del database
    public void insertMagazzino(Magazzino magazzino) throws SQLException {
        String query = "INSERT INTO Magazzino (nome, capienzaMassima, capienzaDisponibile, numero, citta, provincia," +
                " stato, nomeVia, regione, numeroMagazzino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, magazzino.getNome());
            stmt.setInt(2, magazzino.getCapienzaMassima());
            stmt.setInt(3, magazzino.capienzaDisponibile);
            stmt.setString(4, magazzino.getNumero());
            stmt.setString(5, magazzino.getCitta());
            stmt.setString(6, magazzino.getProvincia());
            stmt.setString(7, magazzino.getStato());
            stmt.setString(8, magazzino.getNomeVia());
            stmt.setString(9, magazzino.getRegione());
            stmt.setInt(10, magazzino.getNumeroMagazzino());
            stmt.executeUpdate();
        }
    }

    //Metodo per ottenere i Magazzini tramite il loro numero identificativo
    public Magazzino getMagazzinoByNumero(int numeroMagazzino) throws SQLException {
        String query = "SELECT * FROM Magazzino WHERE numeroMagazzino = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, numeroMagazzino);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Magazzino(
                        rs.getString("nome"),
                        rs.getInt("capienzaMassima"),
                        rs.getInt("numeroMagazzino"),
                        rs.getInt("capienzaDisponibile"),
                        rs.getString("numero"),
                        rs.getString("citta"),
                        rs.getString("provincia"),
                        rs.getString("stato"),
                        rs.getString("nomeVia"),
                        rs.getString("regione")
                );
            }
        }
        return null; // Restituisce null se il magazzino non viene trovato
    }
}