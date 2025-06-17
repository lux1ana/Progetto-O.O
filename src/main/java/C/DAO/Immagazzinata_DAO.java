package C.DAO;

import Classi.Immagazzinata;
import Classi.Merce;
import Classi.Magazzino;
import Classi.ProdottoTabella;
import C.DAO.Database_DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Immagazzinata_DAO {
    private final Connection connection;
    private final Merce_DAO merceDAO;
    private final Magazzino_DAO magazzinoDAO;

    // Costruttore (riceve le istanze dei DAO necessari)
    public Immagazzinata_DAO(Connection connection, Merce_DAO merceDAO, Magazzino_DAO magazzinoDAO) {
        this.connection = connection;
        this.merceDAO = null;
        this.magazzinoDAO = null;
    }


    public List<Immagazzinata> getTutteImmagazzinate() throws SQLException {
        List<Immagazzinata> lista = new ArrayList<>();
        String query = "SELECT * FROM Immagazzinata";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String codiceProdotto = rs.getString("Codice_Prodotto");
                int numeroMagazzino = rs.getInt("Numero_Magazzino");
                int quantità = rs.getInt("quantita");


                Merce merce = merceDAO.getMerceByCodice(codiceProdotto);
                Magazzino magazzino = magazzinoDAO.getMagazzinoByNumero(numeroMagazzino);

                if (merce != null && magazzino != null) {
                    lista.add(new Immagazzinata(merce, magazzino, quantità));
                }
            }
        }
        return lista;
    }

    public List<ProdottoTabella> getProdottiPerMagazzino(int numeroMagazzino) throws SQLException {
        List<ProdottoTabella> lista = new ArrayList<>();

        String query = "SELECT m.Nome_prodotto, i.quantità, m.Codice_Prodotto " +
                "FROM Immagazzinata i " +
                "JOIN Merce m ON i.Codice_Prodotto = m.Codice_Prodotto " +
                "WHERE i.Numero_Magazzino = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroMagazzino);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdottoTabella p = new ProdottoTabella(
                        rs.getString("Nome_prodotto"),
                        rs.getInt("quantita"),
                        rs.getString("Codice_Prodotto")
                );
                lista.add(p);
            }
        }

        return lista;
    }

    public int getDisponibilita(String codiceProdotto) throws SQLException {
        String sql = "SELECT SUM(quantità) FROM immagazzinata WHERE codice_prodotto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codiceProdotto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // restituisce 0 se il risultato è null
            } else {
                return 0;
            }
        }
    }

    public void aggiornaDisponibilita(String codiceProdotto, int nuovaquantita) throws SQLException {
        String sql = "UPDATE Immagazzinata SET quantità = ? WHERE TRIM(LOWER(codice_prodotto)) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nuovaquantita);
            stmt.setString(2, codiceProdotto.trim().toLowerCase());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nessuna riga aggiornata: codice prodotto non trovato.");
            }
        }
        System.out.println("Updating: codice = [" + codiceProdotto + "], quantità = " + nuovaquantita);

    }


}
