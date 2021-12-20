package org.example.client.vues;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;
import packageDTOs.CarteDTO;

import java.io.IOException;

public class Accueil {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;
    private IFacadeProxy facade;

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setFacade(IFacadeProxy facade) {
        this.facade = facade;
    }

    public static Accueil creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(Accueil.class.getResource("accueil.fxml"));
        try {

            BorderPane borderPane = fxmlLoader.load();
            Accueil vue = fxmlLoader.getController();
            vue.setFacade(new FacadeProxy());
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
           throw new RuntimeException("error");
        }
    }

    public void show(){
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void initialiserControleur(Controleur controleur){
        this.controleur = controleur;
    }

    @FXML
    ListView carte;

    @FXML
    Label title;

    public void charger(ObservableList<CarteDTO> carteDTOS){
        carte.setItems(carteDTOS);
    }


    public void chargerdonnes(MouseEvent mouseEvent) {
        this.controleur.loadData();

    }
}
