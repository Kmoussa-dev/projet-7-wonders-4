package org.example.client.vues;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;

import java.io.IOException;

public class FinPartie {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    Label vainqueurPartie;

    public static FinPartie creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(Plateforme.class.getResource("finPartie.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            FinPartie vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(anchorPane,682,417));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
    }

    private void setScene(Scene scene) {
        this.scene = scene;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialiserControleur(Controleur controleur){
        this.controleur = controleur;
    }

    public void show(){
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setVainqueurPartie(String pseudo){
        this.vainqueurPartie.setText(pseudo);
    }

    public void goToMenu(ActionEvent actionEvent) {
        this.controleur.goToAcceuil();
    }
}
