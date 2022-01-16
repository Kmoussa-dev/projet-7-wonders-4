package org.example.client.controleur;

import com.mongodb.DuplicateKeyException;
import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.client.modele.DonnesStatic;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;
import org.example.client.vues.Accueil;
import org.example.client.vues.Authentification;
import org.example.client.vues.Historisation;
import org.example.client.vues.TestPlatorm;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;

import java.util.List;

public class Controleur {
    private IFacadeProxy facade;
    private Accueil accueil;
    private TestPlatorm testPlatorm;
    private Authentification authentification;
    private Historisation historisation;

    public Controleur(Stage stage){
        this.facade = FacadeProxy.cree();
        this.accueil = Accueil.creer(stage);
        this.accueil.initialiserControleur(this);
        this.testPlatorm = TestPlatorm.creer(stage);
        this.testPlatorm.initialiserControleur(this);
        this.authentification = Authentification.creer(stage);
        this.authentification.initialiserControleur(this);
        this.historisation = Historisation.creer(stage);
        this.historisation.initialiserControleur(this);
    }



    public void loadCarteTemp(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesCirculants(idPartie,pseudo));
        this.testPlatorm.charger(cartes);

    }

    public void loadCarteConstruction(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesConstructionCite(idPartie,pseudo));
        this.testPlatorm.chargerContsructionCite(cartes);

    }

    public void loadCarteConstructionMerv(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesConstructionMerv(idPartie,pseudo));
        this.testPlatorm.chargerContsructionMerv(cartes);
    }

    public void loadCartesDefausses(String idPartie){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesDefausses(idPartie));
        this.testPlatorm.chargerCartesDefausses(cartes);

    }



    public void run(){
        this.authentification.show();
    }

    public void accederAuJeu(String idPartie, String pseudo) throws partieDejaTermineException, PartiePleinExecption, partieInexistantException {
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
        this.loadCartesDefausses(idPartie);
    }


    public void refrexh(String idPartie, String pseudo){
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
        this.loadCartesDefausses(idPartie);
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
        } catch (PartieTermineException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie est terminée.", ButtonType.OK);
            alert.setTitle("La partie est terminée");
            alert.showAndWait();
        }
        catch (PartieSuspenduException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie est suspendu.", ButtonType.OK);
            alert.setTitle("La partie est suspendu");
            alert.showAndWait();
        } catch (RessourcesInsuffisantesException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas assez de ressources \n pour vous procurer cette carte", ButtonType.OK);
            alert.setTitle("Ressources Insufffisantes");
            alert.showAndWait();
        }

        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
        this.loadCartesDefausses(idPartie);


    }

    public void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption {
        this.facade.setNouvellePartie(pseudo, ticket);

    }

    public void inscription(String pseudo, String mdp) throws DuplicateKeyException {
       this.facade.inscription(pseudo,mdp);
    }

    public void loadPartieSuspendus(){
        ObservableList<PartieDTO> partieDTOS = FXCollections.observableArrayList(this.facade.getLesPartiesSuspendu());
        this.accueil.chargerPartieSuspendu(partieDTOS);
    }

    public boolean connexion(String pseudo, String mdp){
        return this.facade.connexion(pseudo,mdp);
    }

    public void goToAcceuil(){
        this.accueil.show();
        this.loadPartieSuspendus();
    }

    public void goToPlateForm() {
        this.testPlatorm.show();
        this.testPlatorm.setToken(DonnesStatic.ticket,DonnesStatic.pseudo);
        this.testPlatorm.loadCardAge1();
    }

    public void reAccederAuJeu(String idPartie, String pseudo){
       try {
           if(this.facade.reAccederAuJeu(idPartie, pseudo)){
               this.testPlatorm.show();
               this.testPlatorm.setToken(idPartie,pseudo);
               this.loadCarteTemp(idPartie, pseudo);
               this.loadCarteConstruction(idPartie, pseudo);
               this.loadCarteConstructionMerv(idPartie, pseudo);
               this.loadCartesDefausses(idPartie);
           }
       }
       catch (Exception e){
           Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'êtes pas concerné pour cette partie", ButtonType.OK);
           alert.setTitle("La partie non concernée");
           alert.showAndWait();
       }
    }

    public int getEtapesMerveilleConstruite(String idPartie, String pseudo) {
        return this.facade.getEtapesMerveilleConstruite(idPartie,pseudo);
    }

    public boolean quitterPartie(String idPartie, String pseudo) {
        return this.facade.quitter(idPartie,pseudo);
    }

    public boolean suspendreJeu(String idPartie, String pseudo) throws PartieNonReprendreException {
        return this.facade.suspendreLaPartie(idPartie,pseudo);
    }

    public boolean reprendreJeu(String idPartie, String pseudo) throws PartieNonSuspenduException {
        return this.facade.reprendreUnePartie(idPartie,pseudo);
    }

    public void peutQuitter(String idPartie){
        if(this.facade.peutQuitter(idPartie)){
            this.goToAcceuil();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La partie n'est pas Termineé ou suspendu.", ButtonType.OK);
            alert.setTitle("La partie en plein action");
            alert.showAndWait();
        }
    }

    public void goToHistorique() {
        this.historisation.show();
    }
}
