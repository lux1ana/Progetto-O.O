package Interfacce.controllers;

import C.DAO.Persona_DAO;
import Classi.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import C.DAO.Database_DAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/*
 Questa è la schermata del log-in, in cui l'admin immette email e password per accedere. Si ha anche la possibilità
 di creare un nuovo utente tramite la registrazione
*/


public class iniziale {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink Registrationfield;
    @FXML private Label erroreLoginLabel;

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        System.out.println("Tentativo di login con:");
        System.out.println("Email: " + email);
        System.out.println("Password inserita: " + password);

        try (Connection conn = Database_DAO.getConnection()) {
            Persona_DAO personaDAO = new Persona_DAO(conn);
            Persona user = personaDAO.findByEmail(email);

            if (user == null) {
                System.out.println("DEBUG: User object è null");
                showError("Errore", "Email non trovata!");
                return;
            }

            System.out.println("Password DB: " + user.getPassword());
            System.out.println("Password inserita: " + password);
            System.out.println("Confronto password: DB='" + user.getPassword() + "' | Inserita='" + password + "'");

            if (!user.getPassword().trim().equals(password.trim())) {
                showError("Errore", "Password errata!");
                return;
            }

            // Login riuscito
            System.out.println("Login successo per: " + user.getEmail());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Controllo.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Eccezione durante il login:");
            e.printStackTrace();
            showError("Errore", "Si è verificato un errore durante il login");
        }
    }

    @FXML
    private void handleRegistration() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/registrazione.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Registrazione");
            stage.setScene(new Scene(root));

            ((Stage) Registrationfield.getScene().getWindow()).close();
            stage.show();
        } catch (IOException e) {
            showError("Errore", "Errore nel caricamento: " + e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}