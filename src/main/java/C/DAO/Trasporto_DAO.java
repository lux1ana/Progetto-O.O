package C.DAO;

import Classi.Trasporto;
import Classi.tipologia_trasporto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Trasporto_DAO {
    private Connection conn;

    public Trasporto_DAO(Connection conn) {
        this.conn = conn;
    }

    // Aggiungi un nuovo trasporto
    public boolean aggiungiTrasporto(Trasporto trasporto) throws SQLException {
        String query = "INSERT INTO Trasporto (targa, marca, anno_immatricolazione, tipologia_trasporto, peso_max_trasportabile_kg, disponibilità) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, trasporto.targa);
            stmt.setString(2, trasporto.marca);
            stmt.setDate(3, new java.sql.Date(trasporto.anno_immatricolazione.getTime()));  // Converti java.util.Date → java.sql.Date
            stmt.setString(4, trasporto.tipologia_trasporto.name());  // Usa .name() se è un enum
            stmt.setFloat(5, trasporto.peso_max_trasportabile_kg);
            stmt.setBoolean(6, trasporto.disponibilità);

            return stmt.executeUpdate() > 0;
        }
    }

    // Cerca trasporto per targa
    public Trasporto getTrasportoByTarga(String targa) throws SQLException {
        String query = "SELECT * FROM Trasporto WHERE targa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, targa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Trasporto(
                        rs.getString("marca"),
                        rs.getDate("anno_immatricolazione"),
                        tipologia_trasporto.valueOf(rs.getString("tipologia_trasporto")),
                        rs.getFloat("peso_max_trasportabile_kg"),
                        rs.getString("targa"),
                        rs.getBoolean("disponibilità")
                );
            }
        }
        return null;
    }

    // Ottieni tutti i trasporti disponibili
    public List<Trasporto> getTrasportiDisponibili() throws SQLException {
        List<Trasporto> lista = new ArrayList<>();
        String query = "SELECT * FROM Trasporto WHERE disponibilità = TRUE";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Trasporto(
                        rs.getString("marca"),
                        rs.getDate("anno_immatricolazione"),
                        tipologia_trasporto.valueOf(rs.getString("tipologia_trasporto")),
                        rs.getFloat("peso_max_trasportabile_kg"),
                        rs.getString("targa"),
                        rs.getBoolean("disponibilità")
                ));
            }
        }
        return lista;
    }

    // Aggiorna disponibilità
    public boolean aggiornaDisponibilita(String targa, boolean nuovaDisponibilita) throws SQLException {
        String query = "UPDATE Trasporto SET disponibilità = ? WHERE targa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, nuovaDisponibilita);
            stmt.setString(2, targa);
            return stmt.executeUpdate() > 0;
        }
    }
}