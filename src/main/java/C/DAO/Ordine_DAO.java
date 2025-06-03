package C.DAO;

import Classi.Ordine;
import Classi.Persona;
import Classi.Spedizione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ordine_DAO {
    private Connection conn;

    public Ordine_DAO(Connection conn) {
        this.conn = conn;
    }

    // Recupera tutti gli ordini dal database
    public List<Ordine> getAllOrdini() throws SQLException {
        List<Ordine> listaOrdini = new ArrayList<>();
        String query = "SELECT * FROM Ordine";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ordine ordine = creaOrdineDaResultSet(rs);
                listaOrdini.add(ordine);
            }
        }
        return listaOrdini;
    }

    // Inserisce un nuovo ordine nel database
    public void insertOrdine(Ordine ordine) throws SQLException {
        String query = "INSERT INTO Ordine (codice_ordine, data_ordine, Nome_Via, Numero, città, Provincia, Stato, Ordine_Effettuato, Peso, Costo, cod_fiscale_cliente, codice_spedizione, num_prodotti) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ordine.getCodice_ordine());
            stmt.setDate(2, new java.sql.Date(ordine.getData_ordine().getTime()));
            stmt.setString(3, ordine.getNome_Via());
            stmt.setInt(4, ordine.getNumero());
            stmt.setString(5, ordine.getCittà());
            stmt.setString(6, ordine.getProvincia());
            stmt.setString(7, ordine.getStato());
            stmt.setBoolean(8, ordine.isOrdine_Effettuato());
            stmt.setFloat(9, ordine.getPeso());
            stmt.setDouble(10, ordine.getCosto());
            stmt.setString(11, ordine.getCod_fiscale_cliente().getCod_fiscale());
            stmt.setString(12, ordine.getCodice_spedizione().getCodice_spedizione());
            stmt.setInt(13, ordine.getNum_prodotti()); // qui mancava!

            stmt.executeUpdate();
        }
    }

    // Recupera un ordine tramite codice
    public Ordine getOrdineByCodice(String codiceOrdine) throws SQLException {
        String query = "SELECT * FROM Ordine WHERE codice_ordine = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codiceOrdine);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return creaOrdineDaResultSet(rs);
            }
        }
        return null;
    }

    // Recupera ordini tramite codice fiscale cliente
    public List<Ordine> getOrdiniByCF(String codiceFiscaleCliente) throws SQLException {
        List<Ordine> listaOrdini = new ArrayList<>();
        String query = "SELECT * FROM Ordine WHERE cod_fiscale_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codiceFiscaleCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaOrdini.add(creaOrdineDaResultSet(rs));
            }
        }
        return listaOrdini;
    }

    // Recupera ordini compresi tra due date (inclusi)
    public List<Ordine> getOrdiniByData(Date dataInizio, Date dataFine) throws SQLException {
        List<Ordine> listaOrdini = new ArrayList<>();
        String query = "SELECT * FROM Ordine WHERE data_ordine BETWEEN ? AND ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, dataInizio);
            stmt.setDate(2, dataFine);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaOrdini.add(creaOrdineDaResultSet(rs));
            }
        }
        return listaOrdini;
    }

    // Recupera ordini non ancora effettuati
    public List<Ordine> getOrdiniNonEffettuati() throws SQLException {
        List<Ordine> listaOrdini = new ArrayList<>();
        String query = "SELECT * FROM Ordine WHERE ordine_effettuato = false";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaOrdini.add(creaOrdineDaResultSet(rs));
            }
        }
        return listaOrdini;
    }

    // Crea un oggetto Ordine a partire da un ResultSet
    private Ordine creaOrdineDaResultSet(ResultSet rs) throws SQLException {
        return new Ordine(
                rs.getString("codice_ordine"),
                rs.getDate("data_ordine"),
                rs.getString("Nome_Via"),
                rs.getInt("Numero"),
                rs.getString("città"),
                rs.getString("Provincia"),
                rs.getString("Stato"),
                rs.getBoolean("Ordine_Effettuato"),
                rs.getFloat("Peso"),
                rs.getDouble("Costo"),
                new Persona(rs.getString("cod_fiscale_cliente")),
                new Spedizione(rs.getString("codice_spedizione")),
                rs.getInt("num_prodotti")
        );
    }
}

