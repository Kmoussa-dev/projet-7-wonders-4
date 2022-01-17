package org.example.client.vues;

import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.example.client.controleur.Controleur;
import org.example.client.modele.DonnesStatic;
import packageDTOs.PartieDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    TableView<PartieDTO> tableHistorique;
    @FXML
    TableColumn<PartieDTO, LocalDate> dateCreation;
    @FXML
    TableColumn<PartieDTO, String> etatPartie;
    @FXML
    TableColumn<PartieDTO, List<String>> lesJoueurs;


    public static Accueil creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(Plateforme.class.getResource("accueil.fxml"));
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
        DonnesStatic.codePartie = objectId.toHexString();
        try {
            this.controleur.setNouvellePartie(DonnesStatic.pseudo, DonnesStatic.codePartie);
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
        if(lesPartiesSuspendus.getSelectionModel().getSelectedIndex() != -1){
            DonnesStatic.codePartie = ((PartieDTO)lesPartiesSuspendus.getSelectionModel().getSelectedItem()).getId();
            this.controleur.reAccederAuJeu(DonnesStatic.codePartie, DonnesStatic.pseudo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez saisir sélectionner une partie", ButtonType.OK);
            alert.setTitle("La partie non concernée");
            alert.showAndWait();
        }

    }

    public void loadData(){
        this.dateCreation.setCellValueFactory(new PropertyValueFactory<PartieDTO, LocalDate>("dateCreation"));
        this.etatPartie.setCellValueFactory(new PropertyValueFactory<PartieDTO, String>("etatPartie"));
        this.lesJoueurs.setCellValueFactory(new PropertyValueFactory<PartieDTO, List<String>>("lesJoueurs"));
        ObservableList<PartieDTO> partieDTOS = FXCollections.observableArrayList(this.controleur.getLesParties());
        this.tableHistorique.setItems(partieDTOS);
    }

    public void showParties(ActionEvent actionEvent) {
        this.controleur.loadPartieSuspendus();
    }


}
