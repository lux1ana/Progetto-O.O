package Interfacce.controllers;

import C.DAO.Database_DAO;
import Classi.Merce;
import Classi.Immagazzinata;
import Classi.ProdottoTabella;
import C.DAO.Immagazzinata_DAO;
import C.DAO.Merce_DAO;
import C.DAO.Magazzino_DAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class listaprodotti {

    @FXML private TableView<ProdottoTabella> prodottiTable;
    @FXML private TableColumn<ProdottoTabella, String> colNome;
    @FXML private TableColumn<ProdottoTabella, Integer> colDisponibilita;
    @FXML private TableColumn<ProdottoTabella, Void> colAzioni;
    @FXML private TextField RicercaprodottoField;
    @FXML private Button ricercabutton;
    @FXML private Button indietroField;

    private final ObservableList<ProdottoTabella> prodotti = FXCollections.observableArrayList();
    private Connection conn;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome_prodotto"));
        colDisponibilita.setCellValueFactory(new PropertyValueFactory<>("quantita"));

        try {
            conn = Database_DAO.getConnection();
            caricaProdotti();
            aggiungiBottoniAzione();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Errore", "Connessione al database fallita: " + e.getMessage());
        }

        ricercabutton.setOnAction(e -> filtraProdotti());
        indietroField.setOnAction(e -> tornaIndietro(e));

    }

    private void caricaProdotti() throws SQLException {
        Merce_DAO merceDAO = new Merce_DAO();
        Immagazzinata_DAO immagazzinataDAO = new Immagazzinata_DAO(conn, new Merce_DAO(), new Magazzino_DAO(conn));


        List<Merce> tutte = merceDAO.getAllMerce();
        List<ProdottoTabella> tabella = new ArrayList<>();

        for (Merce m : tutte) {
            int quantita = immagazzinataDAO.getDisponibilita(m.getCodice_Prodotto());
            tabella.add(new ProdottoTabella(m.getNome_Prodotto(), quantita, m.getCodice_Prodotto()));
        }

        prodotti.setAll(tabella);
        prodottiTable.setItems(prodotti);
    }

    private void aggiungiBottoniAzione() {
        colAzioni.setCellFactory(col -> new TableCell<>() {
            private final Button btnPlus = new Button("+");
            private final Button btnMinus = new Button("-");

            {
                btnPlus.setOnAction(e -> {
                    ProdottoTabella prodotto = getTableView().getItems().get(getIndex());
                    modificaQuantita(prodotto, 1);
                });

                btnMinus.setOnAction(e -> {
                    ProdottoTabella prodotto = getTableView().getItems().get(getIndex());
                    modificaQuantita(prodotto, -1);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, btnMinus, btnPlus);
                    setGraphic(box);
                }
            }
        });
    }

    private void modificaQuantita(ProdottoTabella prodotto, int delta) {
        try {
            Immagazzinata_DAO iDAO = new Immagazzinata_DAO(conn, new Merce_DAO(), new Magazzino_DAO(conn));
            int nuova = prodotto.getQuantita() + delta;
            if (nuova >= 0) {
                iDAO.aggiornaDisponibilita(prodotto.getCodice_Prodotto(), nuova);
                prodotto.setQuantita(nuova);
                prodottiTable.refresh();
            }
        } catch (SQLException e) {
            showAlert("Errore", "Impossibile aggiornare la disponibilità.");
            e.printStackTrace();
        }
    }

    private void filtraProdotti() {
        String testo = RicercaprodottoField.getText().toLowerCase();
        if (testo.isBlank()) {
            prodottiTable.setItems(prodotti);
            return;
        }

        ObservableList<ProdottoTabella> filtrati = FXCollections.observableArrayList();
        for (ProdottoTabella p : prodotti) {
            if (p.getNome_prodotto().toLowerCase().contains(testo) ||
                    String.valueOf(p.getCodice_Prodotto()).contains(testo)) {
                filtrati.add(p);
            }
        }

        prodottiTable.setItems(filtrati);
    }

    private void showAlert(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
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