package org.example.client.vues;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.example.client.controleur.Controleur;

import java.io.IOException;
import java.util.Date;

public class Accueil {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    Button btnRejoindre;

    @FXML
    Button btnCreer;

    @FXML
    TextField pseudo;

    @FXML
    TextField ticket;

    @FXML
    RadioButton deuxJoueurs;

    @FXML
    RadioButton troisJoueurs;





    public static Accueil creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(TestPlatorm.class.getResource("accueil.fxml"));
        try {

            AnchorPane borderPane = fxmlLoader.load();
            Accueil vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,682,417));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
    }


    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialiserControleur(Controleur controleur){
        this.controleur = controleur;
    }

    public void show(){
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void rejoindrePartie(ActionEvent actionEvent) {
        this.controleur.accederAuJeu(pseudo.getText(), ticket.getText());
    }

    public void creerPartie(ActionEvent actionEvent) {
       if(deuxJoueurs.isSelected()){
           ObjectId objectId = new ObjectId(new Date());
           this.controleur.setNouvellePartie(pseudo.getText(), objectId.toString(), 2);
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Envoyer ce ticket d'invitation: " + objectId, ButtonType.OK);
           alert.setTitle("Invitation");
           alert.showAndWait();
       }
       else if(troisJoueurs.isSelected()){
           ObjectId objectId = new ObjectId(new Date());
           this.controleur.setNouvellePartie(pseudo.getText(), objectId.toString(), 3);
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Envoyer ce ticket d'invitation: " + objectId, ButtonType.OK);
           alert.setTitle("Invitation");
           alert.showAndWait();
       }
       else{

       }

    }

    public void getNbJoueursInvitation(ActionEvent actionEvent) {

    }
}
