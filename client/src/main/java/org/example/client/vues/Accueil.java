package org.example.client.vues;

import exceptions.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.example.client.controleur.Controleur;
import org.example.client.modele.DonnesStatic;
import packageDTOs.PartieDTO;

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
    Label labelPseudo;

    @FXML
    TextField ticket;

    @FXML
    ListView lesPartiesSuspendus;

    @FXML
    TableView tableHistorique;


    public static Accueil creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(TestPlatorm.class.getResource("accueil.fxml"));
        try {

            BorderPane borderPane = fxmlLoader.load();
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

    public void setLabelPseudo(String pseudo){
        this.labelPseudo.setText(pseudo);
    }

    public void chargerPartieSuspendu(ObservableList<PartieDTO> partieDTOS){
        this.lesPartiesSuspendus.setItems(partieDTOS);
    }

    public void rejoindrePartie(ActionEvent actionEvent) {
        try {

            this.controleur.accederAuJeu(ticket.getText(),DonnesStatic.pseudo);
            this.controleur.goToPlateForm();
        }  catch (partieDejaTermineException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie est déjà terminé", ButtonType.OK);
            alert.setTitle("Remplie");
            alert.showAndWait();
        } catch (PartiePleinExecption e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie est pleine", ButtonType.OK);
            alert.setTitle("Remplie");
            alert.showAndWait();
        } catch (partieInexistantException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le code de la partie est incorrect", ButtonType.OK);
            alert.setTitle("Remplie");
            alert.showAndWait();
        }
    }

    public void creerPartie(ActionEvent actionEvent) {
        ObjectId objectId = new ObjectId(new Date());
        DonnesStatic.ticket = objectId.toHexString();
        try {
            this.controleur.setNouvellePartie(DonnesStatic.pseudo, DonnesStatic.ticket);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Envoyer ce code d'invitation: "+"\n" + objectId.toHexString(), ButtonType.OK);
            alert.setTitle("Invitation");
            alert.showAndWait();
            this.controleur.goToPlateForm();
        } catch (PartiePleinExecption e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie est plein", ButtonType.OK);
            alert.setTitle("Remplie");
            alert.showAndWait();
        }


    }

    public void reprendreUnePartie(ActionEvent actionEvent) {
        DonnesStatic.ticket = ((PartieDTO)lesPartiesSuspendus.getSelectionModel().getSelectedItem()).getId();
        this.controleur.reAccederAuJeu(DonnesStatic.ticket, DonnesStatic.pseudo);
    }

    public void showParties(ActionEvent actionEvent) {
        this.controleur.loadPartieSuspendus();
    }

    public void goToHistorique(ActionEvent actionEvent) {
        this.controleur.goToHistorique();
    }
}
