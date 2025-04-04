package C.DAO;

import Classi.Spedizione;
import Classi.Persona;
import Classi.Tipo_Trasporto;
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
            stmt.setString(1, spedizione.CodiceSpedizione);
            stmt.setString(2, spedizione.CodiceFiscale_Fattorino.getCodiceFiscale());  // Usa il CF dal oggetto Persona
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
                            rs.getString("CodiceSpedizione"),
                            fattorino,  // Oggetto Persona con tutti i campi
                            null,        // Esempio: eventuale oggetto Trasporto (da gestire se necessario)
                            rs.getFloat("PesoSpedizione"),
                            rs.getString("RegioneSpedizione"),
                            rs.getBoolean("SpedizioneEffettuata")
                    );
                    spedizioni.add(spedizione);
                }
            }
        }
        return spedizioni;
    }

    // Aggiorna lo stato di una spedizione
    public void aggiornaStatoSpedizione(String codiceSpedizione, boolean spedizioneConclusa) throws SQLException {
        String sql = "UPDATE Spedizione SET SpedizioneConclusa = ? WHERE CodiceSpedizione = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, spedizioneConclusa);
            stmt.setString(2, codiceSpedizione);
            stmt.executeUpdate();
        }
    }

    // Elimina una spedizione
    public void eliminaSpedizione(String codiceSpedizione) throws SQLException {
        String sql = "DELETE FROM Spedizione WHERE CodiceSpedizione = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceSpedizione);
            stmt.executeUpdate();
        }
    }
}