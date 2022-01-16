package org.example.client.vues;

import exceptions.PartieNonReprendreException;
import exceptions.PartieNonSuspenduException;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import org.example.client.modele.DonnesStatic;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestPlatorm {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    TextField codePartie;

    @FXML
    Button deplaceCarte;

    @FXML
    ListView carteTemp;

    @FXML
    ListView carteContructCite;

    @FXML
    ListView carteContructMerv;

    @FXML
    ListView cartesDefausse;

    @FXML
    Label pseudo;

    @FXML
    ImageView merveille;

    @FXML
    Button btnConstrucMerv;

    @FXML
    Label labelPlateau;
    @FXML
    Label labelAge;
    @FXML
    Label labelEtape;



    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setToken(String token, String pseudo){
        this.pseudo.setText(pseudo);
        this.codePartie.setText(token);
        this.codePartie.setEditable(false);
    }


    public static TestPlatorm creer(Stage stage){

        FXMLLoader fxmlLoader = new FXMLLoader(TestPlatorm.class.getResource("testPlatorm.fxml"));
        try {

            AnchorPane borderPane = fxmlLoader.load();
            TestPlatorm vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,750,530));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
    }

    public void activerBuutonDeplacer(){
        deplaceCarte.setDisable(false);
    }

    public void show(){
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void initialiserControleur(Controleur controleur){
        this.controleur = controleur;
    }


    public void loadCardAge1() {
        this.carteTemp.getItems().clear();
        this.carteContructCite.getItems().clear();
        this.carteContructMerv.getItems().clear();
        Task<Boolean> attenteDistributionCarte = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommence(DonnesStatic.ticket));
                return true;
            }
        };
        attenteDistributionCarte.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.distributionCarte(DonnesStatic.ticket, DonnesStatic.pseudo));
        Thread thread = new Thread(attenteDistributionCarte);
        thread.start();

    }

    public void charger(ObservableList<Carte> cartes) {
        carteTemp.setItems(cartes);
        carteTemp.setOrientation(Orientation.HORIZONTAL); //afficher horizontalement la liste des cartes circulantes
        visuelCartes(); //appel à la fonction qui affiche les images des cartes
        //afficher les étapes de la merveille
        merveille.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/etapes/etapeMerveille0.png")));
    }

    public void choixUneCarte(ActionEvent actionEvent) {

        int index = carteTemp.getSelectionModel().getSelectedIndex();
        if (index != - 1){
            List<Carte> cartes = new ArrayList<>();
            Carte nomCarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
            carteTemp.getItems().remove(index);
            carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

            controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo, nomCarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_CITE);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucune carte n'est choisi", ButtonType.OK);
            alert.setTitle("Aucune carte choisi");
            alert.showAndWait();
        }


        Task<Boolean> deplacement = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (controleur.authorisationCirculer(DonnesStatic.ticket));
                return true;
            }
        };
        deplacement.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.notification(DonnesStatic.ticket, DonnesStatic.pseudo));
        Thread thread = new Thread(deplacement);
        thread.start();



    }

    public void chargerContsructionCite(ObservableList<Carte> cartes) {
        carteContructCite.setItems(cartes);
    }

    public void chargerContsructionMerv(ObservableList<Carte> cartes) {
        carteContructMerv.setItems(cartes);

        var etapesMerveille = controleur.getEtapesMerveilleConstruite(DonnesStatic.ticket,DonnesStatic.pseudo);
        labelEtape.setText(String.valueOf(etapesMerveille));

        if (etapesMerveille == 3) {btnConstrucMerv.setDisable(true);}

        merveille.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/etapes/etapeMerveille"+ etapesMerveille +".png")));
    }

    public void chargerCartesDefausses(ObservableList<Carte> cartes) {
        cartesDefausse.setItems(cartes);
    }

    public void refresh(ActionEvent actionEvent) {
        this.controleur.refrexh(DonnesStatic.ticket,DonnesStatic.pseudo);
    }

    public void constructionMerveille(ActionEvent actionEvent) {

        int index = carteTemp.getSelectionModel().getSelectedIndex();
        if (index != - 1){
            List<Carte> cartes = new ArrayList<>();
            Carte nomCarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
            carteTemp.getItems().remove(index);
            carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

            controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo, nomCarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_MERVAILLE);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucune carte n'est choisi", ButtonType.OK);
            alert.setTitle("Aucune carte choisi");
            alert.showAndWait();
        }

        Task<Boolean> deplacement = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (controleur.authorisationCirculer(DonnesStatic.ticket));
                return true;
            }
        };
        deplacement.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.notification(DonnesStatic.ticket, DonnesStatic.pseudo));
        Thread thread = new Thread(deplacement);
        thread.start();
    }

    public void defausserCarte(ActionEvent actionEvent) {
        int index = carteTemp.getSelectionModel().getSelectedIndex();
        if (index != - 1){
            List<Carte> cartes = new ArrayList<>();
            Carte nomCarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
            carteTemp.getItems().remove(index);
            carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

            controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo, nomCarteChoisi, cartes, ModeDeplacement.DEFAUSSE_CARTE);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucune carte n'est choisi", ButtonType.OK);
            alert.setTitle("Aucune carte choisi");
            alert.showAndWait();
        }
        Task<Boolean> deplacement = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (controleur.authorisationCirculer(DonnesStatic.ticket));
                return true;
            }
        };
        deplacement.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.notification(DonnesStatic.ticket, DonnesStatic.pseudo));
        Thread thread = new Thread(deplacement);
        thread.start();
    }


    /**
     * Méthode qui affiche l'image des cartes circulantes via imageView
     */
    public void visuelCartes() {

        carteTemp.setCellFactory(param -> {
            return new ListCell<Carte>() {
                private ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Carte carte, boolean vide) {
                    super.updateItem(carte, vide);
                    if (vide) {
                        setText(null);
                        imageView.setImage(null);
                        setGraphic(null);
                    } else {
                        //String nomCarte =  ((ICarte)carteTemp.getSelectionModel().getSelectedItem()).getNom();
                        imageView.setFitHeight(130); //hauteur image
                        imageView.setFitWidth(90);
                        imageView.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/cartesID/" + carte.getId() + ".png")));
                        setGraphic(imageView);
                    }
                }
            };
        });
    }

    /**
     * Méthode qui affiche l'image des cartes de construction de la Cité
     */
    public void visuelCarteConstructionCiteV1() {

        carteContructCite.setCellFactory(param -> {
            return new ListCell<Carte>() {
                private ImageView imageViewCite = new ImageView();

                @Override
                protected void updateItem(Carte carte, boolean vide) {
                    super.updateItem(carte, vide);
                    if (vide) {
                        setText(null);
                        imageViewCite.setImage(null);
                        setGraphic(null);
                    } else {
                        //String nomCarte =  ((ICarte)carteTemp.getSelectionModel().getSelectedItem()).getNom();
                        imageViewCite.setFitHeight(100);
                        imageViewCite.setFitWidth(50);
                        imageViewCite.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/cartesID/"+ carte.getId() + ".png")));
                        setGraphic(imageViewCite);
                    }
                }
            };
        });
    }

    public void quitterPartie(ActionEvent actionEvent) {
        if(!this.controleur.quitterPartie(DonnesStatic.ticket,DonnesStatic.pseudo)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas droit de faire quitter cette partie", ButtonType.OK);
            alert.setTitle("Accès refusée");
            alert.showAndWait();
        }
    }

    public void suspendreJeu(ActionEvent actionEvent) {
        try {
            if(this.controleur.suspendreJeu(DonnesStatic.ticket,DonnesStatic.pseudo)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous avez suspendu la partie", ButtonType.OK);
                alert.setTitle("Suspendre");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas droit de suspendre cette partie", ButtonType.OK);
                alert.setTitle("Accès refusée");
                alert.showAndWait();
            }
        } catch (PartieNonReprendreException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "La partie est déjà suspendu", ButtonType.OK);
            alert.setTitle("La partie déjà suspendu");
            alert.showAndWait();
        }
    }

    public void reprendreJeu(ActionEvent actionEvent) {
        try {
            if(this.controleur.reprendreJeu(DonnesStatic.ticket,DonnesStatic.pseudo)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous avez repris la partie", ButtonType.OK);
                alert.setTitle("Reprendre");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas droit de reprendre cette partie", ButtonType.OK);
                alert.setTitle("Accès refusée");
                alert.showAndWait();
            }
        } catch (PartieNonSuspenduException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "La partie n'est pas suspendu", ButtonType.OK);
            alert.setTitle("La partie non suspendu");
            alert.showAndWait();
        }

    }

    public void retourMenu(ActionEvent actionEvent) {
        this.controleur.peutQuitter(DonnesStatic.ticket);

    }
}
