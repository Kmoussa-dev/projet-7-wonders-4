package org.example.client.controleur;

import exceptions.CarteDejaException;
import exceptions.CarteInexistantException;
import interfaces.ICarte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;
import org.example.client.vues.Accueil;
import org.example.client.vues.TestPlatorm;
import packageDTOs.CarteDTO;
import packageDTOs.ModeDeplacement;

import java.util.List;

public class Controleur {
    private IFacadeProxy facade;
    private Accueil accueil;
    private TestPlatorm testPlatorm;

    public Controleur(Stage stage){
        this.facade = FacadeProxy.cree();
        this.accueil = Accueil.creer(stage);
        this.accueil.initialiserControleur(this);
        this.testPlatorm = TestPlatorm.creer(stage);
        this.testPlatorm.initialiserControleur(this);
    }

    public void loadData(){
        ObservableList<CarteDTO> carteDTOS = FXCollections.observableArrayList(this.facade.getCartes());
        //this.accueil.charger(carteDTOS);
    }

    public void loadCarteTemp(String pseudo){
        ObservableList<ICarte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesCirculants(pseudo));
        this.testPlatorm.charger(carteDTOS);

    }

    public void loadCarteConstruction(String pseudo){
        ObservableList<ICarte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesConstructionCite(pseudo));
        this.testPlatorm.chargerContsructionCite(carteDTOS);

    }

    public void loadCarteConstructionMerv(String pseudo){
        ObservableList<ICarte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesConstructionMerv(pseudo));
        this.testPlatorm.chargerContsructionMerv(carteDTOS);

    }

    public void run(){
        this.testPlatorm.show();
    }

    public void accederAuJeu(String text, String code) {
        this.facade.accederUnePartie(text,"blalbla");
        this.testPlatorm.loadCardAge1();
    }

    public Boolean partieCommence(){
        return this.facade.partieCommence();
    }

    public void distributionCarte(String pseudo){
        this.facade.distribution(pseudo);
        this.loadCarteTemp(pseudo);
    }

    public Boolean authorisationCirculer(){
        return  this.facade.authorisationCirculer();
    }

    public void notification(String pseudo){
        this.facade.notification();
        this.loadCarteTemp(pseudo);
        this.loadCarteConstruction(pseudo);
        this.loadCarteConstructionMerv(pseudo);
    }


    public void refrexh(String pseudo){
        this.loadCarteTemp(pseudo);
        this.loadCarteConstruction(pseudo);
        this.loadCarteConstructionMerv(pseudo);
    }

    public void deplacerCarte(String pseudo, ICarte nomCarte, List<ICarte> cartes, ModeDeplacement modeDeplacement){
        try {
            this.facade.deplacementCarte(pseudo,nomCarte, cartes, modeDeplacement);
        } catch (CarteInexistantException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cette carte n'existe pas dans le seuveur."+"\n"+"Veuillez cliquer le bouton actualiser", ButtonType.OK);
            alert.setTitle("Carte inexistant");
            alert.showAndWait();
        } catch (CarteDejaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous savez déjà choisi une carte,"+"\n"+"attendez pour le prochain tour.", ButtonType.OK);
            alert.setTitle("Carte déjà choisi");
            alert.showAndWait();
        }
        this.loadCarteConstruction(pseudo);
        this.loadCarteTemp(pseudo);
        this.loadCarteConstructionMerv(pseudo);


    }

    public void setNouvellePartie(String pseudo, String ticket, int effectif) {
        this.facade.setNouvellePartie(pseudo, ticket, effectif);
    }
}
