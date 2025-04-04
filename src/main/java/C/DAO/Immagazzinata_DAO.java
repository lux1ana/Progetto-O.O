package C.DAO;

import Classi.Immagazzinata;
import Classi.Merce;
import Classi.Magazzino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Immagazzinata_DAO {
    private final Connection connection;
    private final Merce_DAO merceDAO;  // Dipendenza da Merce_DAO (istanza)
    private final Magazzino_DAO magazzinoDAO;  // Dipendenza da Magazzino_DAO (istanza)

    // Costruttore (riceve le istanze dei DAO necessari)
    public Immagazzinata_DAO(Connection connection, Merce_DAO merceDAO, Magazzino_DAO magazzinoDAO) {
        this.connection = connection;
        this.merceDAO = merceDAO;
        this.magazzinoDAO = magazzinoDAO;
    }

    // Metodo per ottenere tutte le relazioni Immagazzinata
    public List<Immagazzinata> getTutteImmagazzinate() throws SQLException {
        List<Immagazzinata> lista = new ArrayList<>();
        String query = "SELECT * FROM Immagazzinata";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String codiceProdotto = rs.getString("Codice_Prodotto");
                int numeroMagazzino = rs.getInt("Numero_Magazzino");
                int quantita = rs.getInt("Quantita");

                // Usa i metodi delle istanze merceDAO e magazzinoDAO
                Merce merce = merceDAO.getMerceByCodice(codiceProdotto);
                Magazzino magazzino = magazzinoDAO.getMagazzinoByNumero(numeroMagazzino);

                if (merce != null && magazzino != null) {
                    lista.add(new Immagazzinata(merce, magazzino, quantita));
                }
            }
        }
        return lista;
    }
}