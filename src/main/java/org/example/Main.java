package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/fxml/iniziale.fxml");

        System.out.println("FXML trovato? " + (fxmlLocation != null));

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load(); // Cambiato da StackPane a Parent ✨

        Scene scene = new Scene(root);

        primaryStage.setTitle("Login Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
