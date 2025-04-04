package C.DAO;

import Classi.Cliente;
import java.sql.*;

public class Cliente_DAO {
    private Connection conn;

    public Cliente_DAO(Connection conn) {
        this.conn = conn;
    }

    // Metodo per inserire un cliente nel database
    public void inserisciCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, cognome, dataNascita, email, password, codiceFiscale, genere, tipoPersona, nomeVia, numero, citta, regione, stato, provincia, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCognome());
            stmt.setDate(3, new java.sql.Date(cliente.getDataNascita().getTime()));
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getPassword());
            stmt.setString(6, cliente.getCodiceFiscale());
            stmt.setString(7, cliente.getGenere().toString());
            stmt.setString(8, cliente.getTipoPersona().toString());
            stmt.setString(9, cliente.getNomeVia());
            stmt.setInt(10, cliente.getNumero());
            stmt.setString(11, cliente.getCitta());
            stmt.setString(12, cliente.getRegione());
            stmt.setString(13, cliente.getStato());
            stmt.setString(14, cliente.getProvincia());
            stmt.setString(15, cliente.getTelefono());

            stmt.executeUpdate();
            System.out.println("Cliente inserito con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per recuperare un cliente tramite codice fiscale
    public Cliente getClienteByCodiceFiscale(String codiceFiscale) {
        String sql = "SELECT * FROM Cliente WHERE codiceFiscale = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("dataNascita"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("codiceFiscale"),
                        Classi.Genere.valueOf(rs.getString("genere")),
                        Classi.TipoPersona.valueOf(rs.getString("tipoPersona")),
                        rs.getString("nomeVia"),
                        rs.getInt("numero"),
                        rs.getString("citta"),
                        rs.getString("regione"),
                        rs.getString("stato"),
                        rs.getString("provincia"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodo per aggiornare i dati di un cliente
    public void aggiornaCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, cognome = ?, dataNascita = ?, email = ?, password = ?, genere = ?, tipoPersona = ?, nomeVia = ?, numero = ?, citta = ?, regione = ?, stato = ?, provincia = ?, telefono = ? WHERE codiceFiscale = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCognome());
            stmt.setDate(3, new java.sql.Date(cliente.getDataNascita().getTime()));
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getPassword());
            stmt.setString(6, cliente.getGenere().toString());
            stmt.setString(7, cliente.getTipoPersona().toString());
            stmt.setString(8, cliente.getNomeVia());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getCitta());
            stmt.setString(11, cliente.getRegione());
            stmt.setString(12, cliente.getStato());
            stmt.setString(13, cliente.getProvincia());
            stmt.setString(14, cliente.getTelefono());
            stmt.setString(15, cliente.getCodiceFiscale());

            stmt.executeUpdate();
            System.out.println("Cliente aggiornato con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per eliminare un cliente
    public void eliminaCliente(String codiceFiscale) {
        String sql = "DELETE FROM Cliente WHERE codiceFiscale = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            stmt.executeUpdate();
            System.out.println("Cliente eliminato con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

