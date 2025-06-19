package Interfacce.controllers;

import C.DAO.Ordine_DAO;
import Classi.Ordine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class report {

    @FXML
    private Button Reportfield;

    @FXML
    private DatePicker ricercaMeseButton;

    @FXML
    private BarChart<String, Number> ordiniChart;

    @FXML
    private Label menoOrdiniText;

    @FXML
    private Label piuOrdiniText;

    private Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @FXML
    public void initialize() {
        Reportfield.setOnAction(event -> caricaReport());
    }
    @FXML
    private void caricaReport() {
        try {
            LocalDate dataScelta = ricercaMeseButton.getValue();

            if (dataScelta == null) {
                piuOrdiniText.setText("Seleziona un mese per visualizzare il report.");
                menoOrdiniText.setText("");
                ordiniChart.getData().clear();
                return;
            }

            Ordine_DAO ordineDao = new Ordine_DAO();
            List<Ordine> ordini = ordineDao.getOrdiniByNumeroProdotti(Date.valueOf(dataScelta));

            ordiniChart.getData().clear();
            XYChart.Series<String, Number> serie = new XYChart.Series<>();

            for (Ordine ordine : ordini) {
                serie.getData().add(new XYChart.Data<>(ordine.getCodice_ordine(), ordine.getNum_prodotti()));
            }

            ordiniChart.getData().add(serie);

            if (!ordini.isEmpty()) {
                Ordine piuProdotti = ordini.get(0);
                Ordine menoProdotti = ordini.get(ordini.size() - 1);

                piuOrdiniText.setText("Ordine con più prodotti: " + piuProdotti.getCodice_ordine() +
                        " (" + piuProdotti.getNum_prodotti() + " prodotti)");

                menoOrdiniText.setText("Ordine con meno prodotti: " + menoProdotti.getCodice_ordine() +
                        " (" + menoProdotti.getNum_prodotti() + " prodotti)");
            } else {
                piuOrdiniText.setText("Nessun ordine per il mese selezionato.");
                menoOrdiniText.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            piuOrdiniText.setText("Errore durante il caricamento del report.");
            menoOrdiniText.setText("");
        }
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