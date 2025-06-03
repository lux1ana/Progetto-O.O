package Interfacce.controllers;

import C.DAO.Database_DAO;
import C.DAO.Fattorino_DAO;
import C.DAO.Ordine_DAO;
import C.DAO.Trasporto_DAO;
import Classi.Fattorino;
import Classi.Ordine;
import Classi.Trasporto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class nuovespedizioni implements Initializable {

    @FXML
    private ListView<Ordine> ordinifield;

    @FXML
    private ComboBox<Trasporto> trasportofield;

    @FXML
    private ComboBox<Fattorino> fattorinoField;

    @FXML
    private Button invioButton;

    private Ordine_DAO ordineDAO;
    private Fattorino_DAO fattorinoDAO;
    private Trasporto_DAO trasportoDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inizializzazione controller iniziata"); // Debug

        try {
            System.out.println("Tentativo di connessione al DB..."); // Debug
            Connection conn = Database_DAO.getConnection();
            System.out.println("Connessione al DB riuscita!"); // Debug

            ordineDAO = new Ordine_DAO(conn);
            fattorinoDAO = new Fattorino_DAO(conn);
            trasportoDAO = new Trasporto_DAO(conn);

            System.out.println("DAO inizializzati, procedo a caricare i dati..."); // Debug
            caricaOrdini();
            caricaFattorini();
            caricaTrasporti();

            System.out.println("Dati caricati, setup UI..."); // Debug
            ordinifield.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            invioButton.setOnAction(e -> {
                try {
                    creaSpedizione();
                } catch (SQLException ex) {
                    showError("Errore durante la creazione della spedizione: " + ex.getMessage());
                }
            });

        } catch (SQLException e) {
            System.err.println("ERRORE CONNESSIONE DB: " + e.getMessage()); // Debug più dettagliato
            e.printStackTrace(); // Stampa lo stack trace completo
            showError("Errore durante la connessione al database: " + e.getMessage());
        }

        System.out.println("Inizializzazione controller completata"); // Debug
    }

    private void caricaOrdini() throws SQLException {
        List<Ordine> ordini = ordineDAO.getOrdiniNonEffettuati();
        ObservableList<Ordine> obsOrdini = FXCollections.observableArrayList(ordini);
        System.out.println("DEBUG - Ordini trovati: " + ordini.size());
        ordinifield.setItems(obsOrdini);
    }

    private void caricaFattorini() throws SQLException {
        List<Fattorino> fattorini = fattorinoDAO.getFattoriniDisponibili();
        ObservableList<Fattorino> obsFattorini = FXCollections.observableArrayList(fattorini);
        System.out.println("DEBUG - Fattorini trovati: " + fattorini.size());
        fattorinoField.setItems(obsFattorini);
    }

    private void caricaTrasporti() throws SQLException {
        List<Trasporto> trasporti = trasportoDAO.getTrasportiDisponibili();
        ObservableList<Trasporto> obsTrasporti = FXCollections.observableArrayList(trasporti);
        System.out.println("DEBUG - Trasporti trovati: " + trasporti.size());
        trasportofield.setItems(obsTrasporti);
    }

    private void creaSpedizione() throws SQLException {
        List<Ordine> ordiniSelezionati = ordinifield.getSelectionModel().getSelectedItems();
        Trasporto trasportoSelezionato = trasportofield.getSelectionModel().getSelectedItem();
        Fattorino fattorinoSelezionato = fattorinoField.getSelectionModel().getSelectedItem();

        if (ordiniSelezionati.isEmpty()) {
            showError("Seleziona almeno un ordine.");
            return;
        }
        if (trasportoSelezionato == null) {
            showError("Seleziona un mezzo di trasporto.");
            return;
        }
        if (fattorinoSelezionato == null) {
            showError("Seleziona un fattorino.");
            return;
        }

        System.out.println("Ordini selezionati:");
        ordiniSelezionati.forEach(o -> System.out.println(o));

        System.out.println("Trasporto selezionato:");
        System.out.println(trasportoSelezionato);

        System.out.println("Fattorino selezionato:");
        System.out.println(fattorinoSelezionato);

        // TODO: inserisci qui la chiamata alla DAO per creare la spedizione nel DB
    }

    private void showError(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void tornaIndietro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/controllo.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
