package Interfacce.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class iniziale {
    @FXML private ImageView logoImage;
    @FXML private Button accediButton;
    @FXML private Button registratiButton;

    @FXML
    private void handleAccedi() {
        System.out.println("Accesso in corso...");
        // Apri la dashboard
    }

    @FXML
    private void handleRegistrati() {
        System.out.println("Registrazione in corso...");
        // Apri la schermata di registrazione
    }
}