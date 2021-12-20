package org.example.client.executeclient;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;

import java.io.IOException;

public class Programme extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controleur controleur = new Controleur(stage);
        controleur.run();
    }

    public static void main(String[] args) {
        launch();
    }
}
