package C.DAO;

import Classi.Merce;
import Classi.Tipo_Merce;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Merce_DAO {
    private Connection conn;

    public Merce_DAO(Connection conn) {
        this.conn = conn;
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
                        rs.getFloat("Peso"),
                        rs.getString("Descrizione"),
                        rs.getFloat("Prezzo"),
                        Tipo_Merce.valueOf(rs.getString("Tipo_Merce")),
                        rs.getString("Codice_Prodotto")
                );
                listaMerce.add(merce);
            }
        }
        return listaMerce;
    }

    // Metodo per inserire una nuova merce nel database
    public void insertMerce(Merce merce) throws SQLException {
        String query = "INSERT INTO Merce (Nome_prodotto, Casa_Produttrice, Peso, Descrizione, Prezzo, Tipo_Merce, Codice_Prodotto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merce.Nome_prodotto);
            stmt.setString(2, merce.Casa_Produttrice);
            stmt.setFloat(3, merce.Peso);
            stmt.setString(4, merce.Descrizione);
            stmt.setFloat(5, merce.Prezzo);
            stmt.setString(6, merce.Tipo_Merce.name());
            stmt.setString(7, merce.Codice_Prodotto);

            stmt.executeUpdate();
        }
    }
    //ricerca di un prodotto tramite codice
    public Merce getMerceByCodice(String codiceProdotto) throws SQLException {
        String query = "SELECT * FROM Merce WHERE Codice_Prodotto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codiceProdotto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Merce(
                        rs.getString("Nome_prodotto"),
                        rs.getString("Casa_Produttrice"),
                        rs.getFloat("Peso"),
                        rs.getString("Descrizione"),
                        rs.getFloat("Prezzo"),
                        Tipo_Merce.valueOf(rs.getString("Tipo_Merce")),
                        rs.getString("Codice_Prodotto")
                );
            }
        }
        return null; // Se non trova nulla
    }
}
