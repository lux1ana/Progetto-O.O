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
    private Button Spedizionifield;

    @FXML
    private Button Reportfield;

    @FXML
    private Button Prodottifield;


    @FXML
    private void initialize() {
        Ordinifield.setOnAction(this::apriOrdini);
        Spedizionifield.setOnAction(this::apriSpedizioni);
        Reportfield.setOnAction(this::apriReport);
        Prodottifield.setOnAction(this::apriProdotti);

    }

    private void apriOrdini(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ordini.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista Ordini");
            stage.setScene(new Scene(root));
            stage.show();


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

            Stage currentStage = (Stage) Spedizionifield.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void apriReport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/report.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Report Ordini");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) Reportfield.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apriProdotti(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listaprodotti.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista Prodotti");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) Prodottifield.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
