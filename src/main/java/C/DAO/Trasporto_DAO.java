package C.DAO;

import Classi.Trasporto;
import Classi.Tipo_Trasporto;
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
        String query = "INSERT INTO Trasporto (Targa, Marca, annoImmatricolazione, Mezzo_di_Trasporto, Peso_Massimo_Trasportabile, Disponibilita) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, trasporto.Targa);
            stmt.setString(2, trasporto.Marca);
            stmt.setDate(3, new java.sql.Date(trasporto.annoImmatricolazione.getTime()));  // Converti java.util.Date → java.sql.Date
            stmt.setString(4, trasporto.Mezzo_di_Trasporto.name());  // Usa .name() se è un enum
            stmt.setFloat(5, trasporto.Peso_Massimo_Trasportabile);
            stmt.setBoolean(6, trasporto.Disponibilita);

            return stmt.executeUpdate() > 0;
        }
    }

    // Cerca trasporto per targa
    public Trasporto getTrasportoByTarga(String targa) throws SQLException {
        String query = "SELECT * FROM Trasporto WHERE Targa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, targa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Trasporto(
                        rs.getString("Marca"),
                        rs.getDate("annoImmatricolazione"),  // Restituisce java.sql.Date (compatibile con java.util.Date)
                        Tipo_Trasporto.valueOf(rs.getString("Mezzo_di_Trasporto")),  // Converti String → Enum
                        rs.getFloat("Peso_Massimo_Trasportabile"),
                        rs.getString("Targa"),
                        rs.getBoolean("Disponibilita")
                );
            }
        }
        return null;
    }

    // Ottieni tutti i trasporti disponibili
    public List<Trasporto> getTrasportiDisponibili() throws SQLException {
        List<Trasporto> lista = new ArrayList<>();
        String query = "SELECT * FROM Trasporto WHERE Disponibilita = TRUE";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Trasporto(
                        rs.getString("Marca"),
                        rs.getDate("annoImmatricolazione"),
                        Tipo_Trasporto.valueOf(rs.getString("Mezzo_di_Trasporto")),
                        rs.getFloat("Peso_Massimo_Trasportabile"),
                        rs.getString("Targa"),
                        rs.getBoolean("Disponibilita")
                ));
            }
        }
        return lista;
    }

    // Aggiorna disponibilità (es. dopo un noleggio)
    public boolean aggiornaDisponibilita(String targa, boolean nuovaDisponibilita) throws SQLException {
        String query = "UPDATE Trasporto SET Disponibilita = ? WHERE Targa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, nuovaDisponibilita);
            stmt.setString(2, targa);
            return stmt.executeUpdate() > 0;
        }
    }
}