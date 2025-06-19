package Interfacce.controllers;

import Classi.Ordine;
import C.DAO.Ordine_DAO;
import C.DAO.Database_DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.util.List;

public class ordini {

    @FXML private TableView<Ordine> TabellaField;
    @FXML private TableColumn<Ordine, String> colcodice_ordine;
    @FXML private TableColumn<Ordine, String> coldata_ordine;
    @FXML private TableColumn<Ordine, String> colUtente;
    @FXML private TableColumn<Ordine, String> colcod_spedizione;
    @FXML private TableColumn<Ordine, Float> colCosto;
    @FXML private TextField utenteField;
    @FXML private DatePicker inizioField;
    @FXML private DatePicker FineField;

    private Ordine_DAO ordineDAO;

    public void initialize() {
        try {
            Connection conn = Database_DAO.getConnection();
            ordineDAO = new Ordine_DAO();
            initTableColumns();
            caricaTuttiGliOrdini();
        } catch (SQLException e) {
            showError("Errore durante la connessione al database: " + e.getMessage());
        }
    }

    private void caricaTuttiGliOrdini() {
        try {
            List<Ordine> ordini = ordineDAO.getAllOrdini();
            ObservableList<Ordine> ordiniList = FXCollections.observableArrayList(ordini);
            TabellaField.setItems(ordiniList);
        } catch (SQLException e) {
            showError("Errore nel recupero degli ordini: " + e.getMessage());
        }
    }

    @FXML
    public void ricercaOrdini(ActionEvent event) {
        String cod_fiscale_cliente = utenteField.getText().trim();
        java.time.LocalDate dataInizioLD = inizioField.getValue();
        java.time.LocalDate dataFineLD = FineField.getValue();

        java.sql.Date dataInizio = (dataInizioLD != null) ? java.sql.Date.valueOf(dataInizioLD) : null;
        java.sql.Date dataFine = (dataFineLD != null) ? java.sql.Date.valueOf(dataFineLD) : null;

        List<Ordine> ordini;

        try {
            if (cod_fiscale_cliente.isEmpty() && dataInizio == null && dataFine == null) {
                ordini = ordineDAO.getAllOrdini();
            } else if (!cod_fiscale_cliente.isEmpty() && dataInizio == null && dataFine == null) {
                ordini = ordineDAO.getOrdiniByCF(cod_fiscale_cliente);
            } else if (cod_fiscale_cliente.isEmpty() && dataInizio != null && dataFine != null) {
                ordini = ordineDAO.getOrdiniByData(dataInizio, dataFine);
            } else {
                showError("Seleziona solo uno tra i criteri di ricerca.");
                return;
            }

            ObservableList<Ordine> ordiniList = FXCollections.observableArrayList(ordini);
            TabellaField.setItems(ordiniList);
        } catch (SQLException e) {
            showError("Errore durante la ricerca: " + e.getMessage());
        }
    }

    private void initTableColumns() {
        colcodice_ordine.setCellValueFactory(new PropertyValueFactory<>("codice_ordine"));
        coldata_ordine.setCellValueFactory(cellData -> {
            if (cellData.getValue().getData_ordine() != null) {
                return new SimpleStringProperty(cellData.getValue().getData_ordine().toString());
            } else {
                return new SimpleStringProperty("");
            }
        });

        colUtente.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCod_fiscale_cliente() != null) {
                return new SimpleStringProperty(cellData.getValue().getCod_fiscale_cliente().getCod_fiscale());
            } else {
                return new SimpleStringProperty("");
            }
        });

        colcod_spedizione.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCodice_spedizione() != null) {
                return new SimpleStringProperty(cellData.getValue().getCodice_spedizione().getCodice_spedizione());
            } else {
                return new SimpleStringProperty("Non Assegnata");
            }
        });

        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
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