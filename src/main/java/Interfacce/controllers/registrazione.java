package Interfacce.controllers;

import C.DAO.Persona_DAO;
import C.DAO.Database_DAO;
import Classi.Genere;
import Classi.Persona;
import Classi.tipo_persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class registrazione {
    @FXML
    private TextField NomField;
    @FXML
    private TextField CognomeField;
    @FXML
    private DatePicker NascitaField;
    @FXML
    private TextField CodFField;
    @FXML
    private TextField EmailRField;
    @FXML
    private PasswordField PasswordRField;
    @FXML
    private Button RegistrazioneButton;

    @FXML
    private void initialize() {
        RegistrazioneButton.setOnAction(this::handleRegistrazione);
    }
    private void handleRegistrazione(ActionEvent event) {
        String nome = NomField.getText();
        String cognome = CognomeField.getText();
        LocalDate dataNascita = NascitaField.getValue();
        String CodiceFiscale = CodFField.getText();
        String email = EmailRField.getText();
        String password = PasswordRField.getText();

        if (nome.isEmpty() || cognome.isEmpty() || dataNascita == null ||
                CodiceFiscale.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Compila tutti i campi.");
            return;
        }

        if (password.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Errore Password", "La password deve contenere almeno 8 caratteri.");
            return;
        }
        try (Connection conn = Database_DAO.getConnection()) {
            Persona_DAO personaDAO = new Persona_DAO(conn);

            if (personaDAO.findByEmail(email) != null) {
                showAlert(Alert.AlertType.ERROR, "Errore", "Email già registrata.");
                return;
            }
            Persona admin = new Persona(
                    nome,
                    cognome,
                    Date.valueOf(dataNascita),
                    email,
                    password,
                    CodiceFiscale,
                    tipo_persona.Admin,
                    Genere.Altro
            );

            personaDAO.aggiungiPersona(admin);

            showAlert(Alert.AlertType.INFORMATION, "Registrazione", "Registrazione avvenuta con successo!");

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/iniziale.fxml"));
            Stage stage = (Stage) RegistrazioneButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Errore", "Errore durante la registrazione.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}