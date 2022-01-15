package org.example.client.controleur;

import com.mongodb.DuplicateKeyException;
import exceptions.*;
import interfaces.ICarte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.client.modele.DonnesStatic;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;
import org.example.client.vues.Accueil;
import org.example.client.vues.Authentification;
import org.example.client.vues.TestPlatorm;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.List;

public class Controleur {
    private IFacadeProxy facade;
    private Accueil accueil;
    private TestPlatorm testPlatorm;
    public Authentification authentification;

    public Controleur(Stage stage){
        this.facade = FacadeProxy.cree();
        this.accueil = Accueil.creer(stage);
        this.accueil.initialiserControleur(this);
        this.testPlatorm = TestPlatorm.creer(stage);
        this.testPlatorm.initialiserControleur(this);
        this.authentification = Authentification.creer(stage);
        this.authentification.initialiserControleur(this);
    }

    public void loadData(){
        //ObservableList<Carte> carteDTOS = FXCollections.observableArrayList(this.facade.getCartes());
        //this.accueil.charger(carteDTOS);
    }

    public void loadCarteTemp(String idPartie, String pseudo){
        System.out.println(this.facade.getLesCartesCirculants(idPartie,pseudo));
        ObservableList<Carte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesCirculants(idPartie,pseudo));
        this.testPlatorm.charger(carteDTOS);

    }

    public void loadCarteConstruction(String idPartie, String pseudo){
        ObservableList<Carte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesConstructionCite(idPartie,pseudo));
        this.testPlatorm.chargerContsructionCite(carteDTOS);

    }

    public void loadCarteConstructionMerv(String idPartie, String pseudo){
        ObservableList<Carte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesConstructionMerv(idPartie,pseudo));
        this.testPlatorm.chargerContsructionMerv(carteDTOS);

    }

    public void run(){
        this.authentification.show();
    }

    public void accederAuJeu(String idPartie, String pseudo) throws partieDejaTermineException, partiePleinExecption, partieInexistantException {
        this.facade.accederUnePartie(idPartie,pseudo);
        DonnesStatic.ticket = idPartie;
    }

    public Boolean partieCommence(String idPartie){
        return this.facade.partieCommence(idPartie);
    }

    public void distributionCarte(String idPartie, String pseudo){
        this.facade.distribution(idPartie);
        this.loadCarteTemp(idPartie,pseudo);
    }

    public Boolean authorisationCirculer(String idPartie){
        return this.facade.authorisationCirculer(idPartie);
    }

    public void notification(String idPartie, String pseudo){
        this.facade.notification(idPartie);
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
    }


    public void refrexh(String idPartie, String pseudo){
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
    }

    public void deplacerCarte(String idPartie, String pseudo, Carte nomCarte, List<Carte> cartes, ModeDeplacement modeDeplacement){
        try {
            this.facade.deplacementCarte(idPartie, pseudo,nomCarte, cartes, modeDeplacement);
        } catch (CarteInexistantException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cette carte n'existe pas dans le seuveur."+"\n"+"Veuillez cliquer le bouton actualiser", ButtonType.OK);
            alert.setTitle("Carte inexistant");
            alert.showAndWait();
        } catch (CarteDejaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous savez déjà choisi une carte,"+"\n"+"attendez pour le prochain tour.", ButtonType.OK);
            alert.setTitle("Carte déjà choisi");
            alert.showAndWait();
        } catch (PartieSuspenduOuTermine e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous savez déjà choisi une carte,"+"\n"+"attendez pour le prochain tour.", ButtonType.OK);
            alert.setTitle("Carte déjà choisi");
            alert.showAndWait();
        }

        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);


    }

    public void setNouvellePartie(String pseudo, String ticket) throws partiePleinExecption {
        this.facade.setNouvellePartie(pseudo, ticket);

    }

    public void inscription(String pseudo, String mdp) throws DuplicateKeyException {
       this.facade.inscription(pseudo,mdp);
    }

    public boolean connexion(String pseudo, String mdp){
        return this.facade.connexion(pseudo,mdp);
    }

    public void goToAcceuil(){
        this.accueil.show();
    }

    public void goToPlateForm() {
        this.testPlatorm.show();
        this.testPlatorm.setToken(DonnesStatic.ticket);
        this.testPlatorm.loadCardAge1();
    }

    public void reAccederAuJeu(String idPartie, String pseudo){
       if(this.facade.reAccederAuJeu(idPartie, pseudo)){
           this.goToPlateForm();
           this.loadCarteTemp(idPartie, pseudo);
           this.loadCarteConstruction(idPartie, pseudo);
           this.loadCarteConstructionMerv(idPartie, pseudo);

       }

    }

    public boolean quitterPartie(String idPartie, String pseudo) {
        return this.facade.quitter(idPartie,pseudo);
    }

    public boolean suspendreJeu(String idPartie, String pseudo) {
        return this.facade.suspendreLaPartie(idPartie,pseudo);
    }

    public boolean reprendreJeu(String idPartie, String pseudo) {
        return this.facade.reprendreUnePartie(idPartie,pseudo);
    }



}
