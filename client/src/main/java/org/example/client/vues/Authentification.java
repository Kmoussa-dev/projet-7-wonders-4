package org.example.client.vues;

import com.mongodb.DuplicateKeyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import org.example.client.modele.DonnesStatic;

import java.io.IOException;

public class Authentification {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    TextField identifiant;

    @FXML
    PasswordField password;


    public static Authentification creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(TestPlatorm.class.getResource("authentification.fxml"));
        try {
            BorderPane borderPane = fxmlLoader.load();
            Authentification vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,682,417));
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


    public void connexion(ActionEvent actionEvent) {
        if(this.controleur.connexion(identifiant.getText(),password.getText())){
            DonnesStatic.pseudo = identifiant.getText();
            this.controleur.goToAcceuil();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Login incorrect", ButtonType.OK);
            alert.setTitle("Login incorrect");
            alert.showAndWait();
        }
    }

    public void inscription(ActionEvent actionEvent) {
        if(identifiant.getText().isEmpty() && password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez saisir tous les champs", ButtonType.OK);
            alert.setTitle("champs requis");
            alert.showAndWait();
        }
        else {
            try {
                this.controleur.inscription(identifiant.getText(),password.getText());
                DonnesStatic.pseudo = identifiant.getText();
                this.controleur.goToAcceuil();
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ce pseudo est déjà utilisé, vaeuillez choisir un autre", ButtonType.OK);
                alert.setTitle("pseudo déjà existant");
                alert.showAndWait();
            }
        }


    }
}
