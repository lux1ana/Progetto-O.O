package C.DAO;

import Classi.Merce;
import Classi.tipologia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Merce_DAO {
    private Connection conn;

    public Merce_DAO() {
        try {
            this.conn = Database_DAO.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database");
        }
    }

    // Metodo per recuperare tutte le merci dal database
    public List<Merce> getAllMerce() throws SQLException {
        List<Merce> listaMerce = new ArrayList<>();
        String query = "SELECT * FROM Merce";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Merce merce = new Merce(
                        rs.getString("Nome_prodotto"),
                        rs.getString("Casa_Produttrice"),
                        rs.getFloat("peso_oggetto_kg"),
                        rs.getString("Descrizione"),
                        rs.getFloat("costo_$"),
                        tipologia.parseTipologia(rs.getString("tipologia")), // ✅ QUI
                        rs.getString("Codice_Prodotto")
                );
                listaMerce.add(merce);
            }
        }
        return listaMerce;
    }

    // Metodo per inserire una nuova merce nel database
    public void insertMerce(Merce merce) throws SQLException {
        String query = "INSERT INTO Merce (Nome_prodotto, Casa_Produttrice, peso_oggetto_kg, Descrizione, costo_$, Tipo_Merce, Codice_Prodotto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merce.Nome_prodotto);
            stmt.setString(2, merce.Casa_Produttrice);
            stmt.setFloat(3, merce.peso_oggetto_kg);
            stmt.setString(4, merce.Descrizione);
            stmt.setFloat(5, merce.costo_$);
            stmt.setString(6, merce.tipologia.name());
            stmt.setString(7, merce.Codice_Prodotto);

            stmt.executeUpdate();
        }
    }

    // Ricerca di un prodotto tramite codice
    public Merce getMerceByCodice(String codiceProdotto) throws SQLException {
        String query = "SELECT * FROM Merce WHERE Codice_Prodotto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codiceProdotto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Merce(
                        rs.getString("Nome_prodotto"),
                        rs.getString("Casa_Produttrice"),
                        rs.getFloat("peso_oggetto_kg"),
                        rs.getString("Descrizione"),
                        rs.getFloat("costo_$"),
                        tipologia.parseTipologia(rs.getString("tipologia")),
                        rs.getString("Codice_Prodotto")
                );
            }
        }
        return null;
    }

    // Metodo per recuperare le merci filtrate per categoria (tipologia)
    public List<Merce> getMerceByCategoria(tipologia categoria) throws SQLException {
        List<Merce> listaMerce = new ArrayList<>();
        String query = "SELECT * FROM Merce WHERE Tipo_Merce = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, categoria.name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Merce merce = new Merce(
                            rs.getString("Nome_prodotto"),
                            rs.getString("Casa_Produttrice"),
                            rs.getFloat("peso_oggetto_kg"),
                            rs.getString("Descrizione"),
                            rs.getFloat("costo_$"),
                            tipologia.parseTipologia(rs.getString("tipologia")),
                            rs.getString("Codice_Prodotto")
                    );
                    listaMerce.add(merce);
                }
            }
        }

        return listaMerce;
    }
}
