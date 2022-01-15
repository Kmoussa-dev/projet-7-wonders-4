package org.example.client.vues;

import facade.IFacadeSwOnline;
import interfaces.ICarte;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import org.example.client.modele.DonnesStatic;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPlatorm {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    TextField pseudo;

    @FXML
    Button accer;

    @FXML
    Button deplaceCarte;

    @FXML
    ListView carteTemp;

    @FXML
    ListView carteContructCite;

    @FXML
    ListView carteContructMerv;

    @FXML
    Label token;


    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setToken(String token){
        this.token.setText(token);
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



    public void accederJeu(ActionEvent actionEvent) {
        //this.controleur.accederAuJeu(pseudo.getText(),"");
        //accer.setDisable(true);
    }

    public void loadCardAge1() {

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

    public void charger(ObservableList<Carte> carteDTOS) {
        carteTemp.setItems(carteDTOS);
        carteTemp.setOrientation(Orientation.HORIZONTAL); //afficher horizontalement la liste des cartes circulantes
        visuelCartes(); //appel à la fonction qui affiche les images des cartes
    }

    public void choixUneCarte(ActionEvent actionEvent) {
        //if(controleur.getEtatParti() != "TERMINE" && controleur.getEtatParti() != "SUSPENDU"){}
        int index = carteTemp.getSelectionModel().getSelectedIndex();
        List<Carte> cartes = new ArrayList<>();
        Carte nomCarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
        carteTemp.getItems().remove(index);
        carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

        controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo, nomCarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_CITE);

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

    public void chargerContsructionCite(ObservableList<Carte> carteDTOS) {
        carteContructCite.setItems(carteDTOS);
    }

    public void chargerContsructionMerv(ObservableList<Carte> carteDTOS) {
        carteContructMerv.setItems(carteDTOS);
    }

    public void refresh(ActionEvent actionEvent) {
        this.controleur.refrexh(DonnesStatic.ticket,DonnesStatic.pseudo);
    }

    public void constructionMerveille(ActionEvent actionEvent) {
        int index = carteTemp.getSelectionModel().getSelectedIndex();
        List<Carte> cartes = new ArrayList<>();
        Carte CarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
        carteTemp.getItems().remove(index);
        carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

        controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo,CarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_MERVAILLE);

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
        List<Carte> cartes = new ArrayList<>();
        Carte CarteChoisi =  ((Carte)carteTemp.getSelectionModel().getSelectedItem());
        carteTemp.getItems().remove(index);
        carteTemp.getItems().forEach(o -> cartes.add(((Carte)o)));

        controleur.deplacerCarte(DonnesStatic.ticket, DonnesStatic.pseudo,CarteChoisi, cartes, ModeDeplacement.DEFAUSSE_CARTE);

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

    //DESIGN

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
        if(!this.controleur.suspendreJeu(DonnesStatic.ticket,DonnesStatic.pseudo)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas droit de faire quitter cette partie", ButtonType.OK);
            alert.setTitle("Accès refusée");
            alert.showAndWait();
        }
    }

    public void reprendreJeu(ActionEvent actionEvent) {
        if(!this.controleur.reprendreJeu(DonnesStatic.ticket,DonnesStatic.pseudo)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas droit de faire quitter cette partie", ButtonType.OK);
            alert.setTitle("Accès refusée");
            alert.showAndWait();
        }
    }
}
