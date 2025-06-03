package C.DAO;

import Classi.Spedizione;
import Classi.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Spedizione_DAO {
    private final Connection conn;
    private final Persona_DAO personaDao;  // Dipendenza per recuperare i fattorini completi

    public Spedizione_DAO(Connection conn, Persona_DAO personaDao) {
        this.conn = conn;
        this.personaDao = personaDao;
    }

    // Aggiungi una nuova spedizione
    public void aggiungiSpedizione(Spedizione spedizione) throws SQLException {
        String sql = "INSERT INTO Spedizione (CodiceSpedizione, CodiceFiscale_Fattorino, Tipo_Trasporto, PesoSpedizione, RegioneSpedizione, SpedizioneEffettuata, SpedizioneConclusa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spedizione.codice_spedizione);
            stmt.setString(2, spedizione.CodiceFiscale_Fattorino.getCod_fiscale());  // Usa il CF dal oggetto Persona
            stmt.setString(3, spedizione.Tipo_Trasporto.toString());
            stmt.setFloat(4, spedizione.pesoSpedizione);
            stmt.setString(5, spedizione.regioneSpedizione);
            stmt.setBoolean(6, spedizione.SpedizioneEffettuata);
            stmt.setBoolean(7, spedizione.SpedizioneConclusa);

            stmt.executeUpdate();
        }
    }

    // Ottieni tutte le spedizioni con fattorini completi
    public List<Spedizione> getAllSpedizioni() throws SQLException {
        List<Spedizione> spedizioni = new ArrayList<>();
        String sql = "SELECT * FROM Spedizione";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Recupera il fattorino COMPLETO dal database
                Persona fattorino = personaDao.getPersonaByCodiceFiscale(rs.getString("CodiceFiscale_Fattorino"));

                if (fattorino != null) {
                    Spedizione spedizione = new Spedizione(
                            rs.getString("codice_spedizione"),
                            fattorino,
                            null,  // Da gestire il Tipo_Trasporto se necessario
                            rs.getFloat("PesoSpedizione"),
                            rs.getString("RegioneSpedizione"),
                            rs.getBoolean("SpedizioneEffettuata")
                    );
                    spedizione.SpedizioneConclusa = rs.getBoolean("SpedizioneConclusa");

                    spedizioni.add(spedizione);
                }
            }
        }
        return spedizioni;
    }

    // Ottieni una singola spedizione tramite il codice
    public Spedizione getSpedizioneByCodice(String codice) throws SQLException {
        String sql = "SELECT * FROM Spedizione WHERE codice_spedizione = ?";
        Spedizione spedizione = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codice);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Persona fattorino = personaDao.getPersonaByCodiceFiscale(rs.getString("CodiceFiscale_Fattorino"));

                spedizione = new Spedizione(
                        rs.getString("codice_spedizione"),
                        fattorino,
                        null,
                        rs.getFloat("PesoSpedizione"),
                        rs.getString("RegioneSpedizione"),
                        rs.getBoolean("SpedizioneEffettuata")
                );
                spedizione.SpedizioneConclusa = rs.getBoolean("SpedizioneConclusa");
            }
        }

        return spedizione;
    }

    // Aggiorna lo stato di una spedizione
    public void aggiornaStatoSpedizione(String codiceSpedizione, boolean spedizioneConclusa) throws SQLException {
        String sql = "UPDATE Spedizione SET SpedizioneConclusa = ? WHERE codice_spedizione = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, spedizioneConclusa);
            stmt.setString(2, codiceSpedizione);
            stmt.executeUpdate();
        }
    }

    // Elimina una spedizione
    public void eliminaSpedizione(String codiceSpedizione) throws SQLException {
        String sql = "DELETE FROM Spedizione WHERE codice_spedizione = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceSpedizione);
            stmt.executeUpdate();
        }
    }
}
