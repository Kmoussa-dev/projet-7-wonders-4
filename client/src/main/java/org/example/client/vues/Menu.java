package org.example.client.vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;

import java.io.IOException;

public class Menu {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    Button jouer;

    @FXML
    ImageView img;



    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


    public static Menu creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(Accueil.class.getResource("menu.fxml"));
        try {

            BorderPane borderPane = fxmlLoader.load();
            Menu vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
    }

    public void goToPagePrincipal(MouseEvent mouseEvent) {

    }
}
