package Interfacce.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class controllo {

    @FXML
    private Button Ordinifield;

    @FXML
    private Button Spedizionifield; // Aggiunto nuovo bottone

    @FXML
    private void initialize() {
        Ordinifield.setOnAction(this::apriOrdini);
        Spedizionifield.setOnAction(this::apriSpedizioni); // Aggiunto evento
    }

    private void apriOrdini(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ordini.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista Ordini");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi finestra attuale
            Stage currentStage = (Stage) Ordinifield.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apriSpedizioni(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuovespedizioni.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuova Spedizione");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi finestra attuale
            Stage currentStage = (Stage) Spedizionifield.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
