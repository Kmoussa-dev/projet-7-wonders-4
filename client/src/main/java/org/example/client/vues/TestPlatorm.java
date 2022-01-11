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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.client.controleur.Controleur;
import packageDTOs.CarteDTO;
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


    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
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
        this.controleur.accederAuJeu(pseudo.getText(),"");
        accer.setDisable(true);
    }

    public void loadCardAge1() {

        Task<Boolean> attenteDistributionCarte = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommence());
                return true;
            }
        };
        attenteDistributionCarte.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.distributionCarte(pseudo.getText()));
        Thread thread = new Thread(attenteDistributionCarte);
        thread.start();
        //TODO
    }

    public void charger(ObservableList<ICarte> carteDTOS) {
        carteTemp.setItems(carteDTOS);
        carteTemp.setOrientation(Orientation.HORIZONTAL); //afficher horizontalement la liste des cartes circulantes
        visuelCartes(); //appel à la fonction qui affiche les images des cartes
    }

    public void choixUneCarte(ActionEvent actionEvent) {
        //if(controleur.getEtatParti() != "TERMINE" && controleur.getEtatParti() != "SUSPENDU"){}
        int index = carteTemp.getSelectionModel().getSelectedIndex();
        List<ICarte> cartes = new ArrayList<>();
        CarteDTO nomCarteChoisi =  ((CarteDTO)carteTemp.getSelectionModel().getSelectedItem());
        carteTemp.getItems().remove(index);
        carteTemp.getItems().forEach(o -> cartes.add(((CarteDTO)o)));

        controleur.deplacerCarte(pseudo.getText(),nomCarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_CITE);

        Task<Boolean> deplacement = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (controleur.authorisationCirculer());
                return true;
            }
        };
        deplacement.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.notification(pseudo.getText()));
        Thread thread = new Thread(deplacement);
        thread.start();



    }

    public void chargerContsructionCite(ObservableList<ICarte> carteDTOS) {
        carteContructCite.setItems(carteDTOS);
    }

    public void chargerContsructionMerv(ObservableList<ICarte> carteDTOS) {
        carteContructMerv.setItems(carteDTOS);
    }

    public void refresh(ActionEvent actionEvent) {
        this.controleur.refrexh(pseudo.getText());
    }

    public void constructionMerveille(ActionEvent actionEvent) {
        int index = carteTemp.getSelectionModel().getSelectedIndex();
        List<ICarte> cartes = new ArrayList<>();
        CarteDTO CarteChoisi =  ((CarteDTO)carteTemp.getSelectionModel().getSelectedItem());
        carteTemp.getItems().remove(index);
        carteTemp.getItems().forEach(o -> cartes.add(((CarteDTO)o)));

        controleur.deplacerCarte(pseudo.getText(),CarteChoisi, cartes, ModeDeplacement.CONSTRUCTION_MERVAILLE);

        Task<Boolean> deplacement = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (controleur.authorisationCirculer());
                return true;
            }
        };
        deplacement.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> this.controleur.notification(pseudo.getText()));
        Thread thread = new Thread(deplacement);
        thread.start();
    }

    public void defausserCarte(ActionEvent actionEvent) {
    }

    //DESIGN

    /**
     * Méthode qui affiche l'image des cartes circulantes via imageView
     */
    public void visuelCartes() {

        carteTemp.setCellFactory(param -> {
            return new ListCell<CarteDTO>() {
                private ImageView imageView = new ImageView();

                @Override
                protected void updateItem(CarteDTO carte, boolean vide) {
                    super.updateItem(carte, vide);
                    if (vide) {
                        setText(null);
                        imageView.setImage(null);
                        setGraphic(null);
                    } else {
                        //String nomCarte =  ((ICarte)carteTemp.getSelectionModel().getSelectedItem()).getNom();
                        imageView.setFitHeight(130); //hauteur image
                        imageView.setFitWidth(90); //largeur image
                        imageView.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/cartesID/" + carte.get_id() + ".png")));
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
            return new ListCell<CarteDTO>() {
                private ImageView imageViewCite = new ImageView();

                @Override
                protected void updateItem(CarteDTO carte, boolean vide) {
                    super.updateItem(carte, vide);
                    if (vide) {
                        setText(null);
                        imageViewCite.setImage(null);
                        setGraphic(null);
                    } else {
                        //String nomCarte =  ((ICarte)carteTemp.getSelectionModel().getSelectedItem()).getNom();
                        imageViewCite.setFitHeight(100);
                        imageViewCite.setFitWidth(50);
                        imageViewCite.setImage(new Image(getClass().getResourceAsStream("/org/example/client/vues/image/cartesID/"+ carte.get_id() + ".png")));
                        setGraphic(imageViewCite);
                    }
                }
            };
        });
    }

}
