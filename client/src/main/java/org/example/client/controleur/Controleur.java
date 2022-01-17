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
import org.example.client.vues.FinPartie;
import org.example.client.vues.Plateforme;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;
import packageDTOs.RessourcesDTO;

import java.util.Collection;
import java.util.List;

/**
 * controleur
 */
public class Controleur {
    private IFacadeProxy facade;
    private Accueil accueil;
    private Plateforme plateforme;
    private Authentification authentification;
    private FinPartie finPartie;

    /**
     * Constructeur
     * @param stage
     */
    public Controleur(Stage stage){
        this.facade = FacadeProxy.cree();
        this.accueil = Accueil.creer(stage);
        this.accueil.initialiserControleur(this);
        this.plateforme = Plateforme.creer(stage);
        this.plateforme.initialiserControleur(this);
        this.authentification = Authentification.creer(stage);
        this.authentification.initialiserControleur(this);
        this.finPartie = FinPartie.creer(stage);
        this.finPartie.initialiserControleur(this);
    }

    /**
     * chrger la liste des cartes circulantes
     * @param idPartie
     * @param pseudo
     */
    public void loadCarteTemp(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesCirculants(idPartie,pseudo));
        this.plateforme.charger(cartes);

    }

    /**
     * charger les cartes construction
     * @param idPartie
     * @param pseudo
     */
    public void loadCarteConstruction(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesConstructionCite(idPartie,pseudo));
        this.plateforme.chargerContsructionCite(cartes);

    }

    /**
     * charger les cartes pour la construction du merveille
     * @param idPartie
     * @param pseudo
     */
    public void loadCarteConstructionMerv(String idPartie, String pseudo){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesConstructionMerv(idPartie,pseudo));
        this.plateforme.chargerContsructionMerv(cartes);
    }

    /**
     * charger les cartes déffaussées
     * @param idPartie
     */
    public void loadCartesDefausses(String idPartie){
        ObservableList<Carte> cartes = FXCollections.observableArrayList(this.facade.getLesCartesDefausses(idPartie));
        this.plateforme.chargerCartesDefausses(cartes);

    }

    /**
     * lancer la page d'authentification
     */
    public void run(){
        this.authentification.show();
    }

    /**
     * joindre une partie
     * @param idPartie
     * @param pseudo
     * @throws partieDejaTermineException
     * @throws PartiePleinExecption
     * @throws partieInexistantException
     */
    public void accederAuJeu(String idPartie, String pseudo) throws partieDejaTermineException, PartiePleinExecption, partieInexistantException {
        this.facade.accederUnePartie(idPartie,pseudo);
        DonnesStatic.codePartie = idPartie;
    }

    /**
     * commencer la partie quand tout le monde est dedans
     * @param idPartie
     * @return true si le nombre joueur  = 4 et false sinon
     */
    public Boolean partieCommence(String idPartie){
        return this.facade.partieCommence(idPartie);
    }

    /**
     * la distribution initial des carte et des plateaux (age1) et
     * @param idPartie
     * @param pseudo
     */
    public void distributionCarte(String idPartie, String pseudo){
        this.facade.distribution(idPartie);
        this.loadCarteTemp(idPartie,pseudo);
        this.plateforme.chargerLesRessources();
        this.plateforme.setLabelPlateau(this.facade.getPlateauDuJoueur(idPartie,pseudo));
    }

    /**
     * autoriser la circulation des cartes lorsque tout les joueurs ont choisis une carte
     * @param idPartie
     * @return true si oui false sinon
     */
    public Boolean authorisationCirculer(String idPartie){
        return this.facade.authorisationCirculer(idPartie);
    }

    /**
     * lorsqu'on termine un tour on notifit les joueur et on mis à jour les ressource et échangé les cartes
     * @param idPartie
     * @param pseudo
     */
    public void notification(String idPartie, String pseudo){
        this.facade.notification(idPartie);
        if(this.facade.getEtatPartie(idPartie).equals("TERMINE")){
            this.goToFinPartie();
        }
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
        this.loadCartesDefausses(idPartie);
        this.plateforme.setAgeCurant(this.facade.getAgeCourantPartie(idPartie,pseudo));
    }


    /**
     * actualiser la page lorsque les cartes ne s'affichent pas ou la carte n'est pas bien circulé
     * @param idPartie
     * @param pseudo
     */
    public void refrexh(String idPartie, String pseudo){
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
        this.loadCartesDefausses(idPartie);
        this.plateforme.setAgeCurant(this.facade.getAgeCourantPartie(DonnesStatic.codePartie,DonnesStatic.pseudo));
    }

    /**
     * déplacer les cartes selon les choix de joueurs
     * @param idPartie
     * @param pseudo
     * @param nomCarte
     * @param cartes
     * @param modeDeplacement
     */
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
        } catch (CarteDejaPossederException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous avez déjà en votre \n possesion cette carte", ButtonType.OK);
            alert.setTitle("Ressources déjà acquise");
            alert.showAndWait();
        }

        this.loadCarteConstruction(idPartie, pseudo);
        this.loadCarteTemp(idPartie, pseudo);
        this.loadCarteConstructionMerv(idPartie, pseudo);
        this.loadCartesDefausses(idPartie);


    }

    /**
     * créer une nouvelle partie
     * @param pseudo
     * @param ticket
     * @throws PartiePleinExecption
     */
    public void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption {
        this.facade.setNouvellePartie(pseudo, ticket);

    }

    /**
     * inscription de nouveau joueur
     * @param pseudo
     * @param mdp
     * @throws DuplicateKeyException
     */
    public void inscription(String pseudo, String mdp) throws DuplicateKeyException {
       this.facade.inscription(pseudo,mdp);
    }

    /**
     * afficher les partie qui sont en pause
     */
    public void loadPartieSuspendus(){
        ObservableList<PartieDTO> partieDTOS = FXCollections.observableArrayList(this.facade.getLesPartiesSuspendu(DonnesStatic.pseudo));
        this.accueil.chargerPartieSuspendu(partieDTOS);
    }

    /**
     * authentification des joueurs
     * @param pseudo
     * @param mdp
     * @return true si le joueur est dans la bdd et false sinon
     */
    public boolean connexion(String pseudo, String mdp){
        return this.facade.connexion(pseudo,mdp);
    }

    /**
     * revient à l'acceil
     */
    public void goToAcceuil(){
        this.accueil.show();
        this.accueil.setLabelPseudo(DonnesStatic.pseudo);
        this.loadPartieSuspendus();
        this.accueil.loadData();
    }


    /**
     * revient au jeux
     */
    public void goToPlateForm() {
        this.plateforme.show();
        this.plateforme.setPseudoCodePartie(DonnesStatic.codePartie,DonnesStatic.pseudo);
        this.plateforme.loadCardAge1();
        this.plateforme.setAgeCurant(this.facade.getAgeCourantPartie(DonnesStatic.codePartie,DonnesStatic.pseudo));
        if(!this.facade.joueurCreateurDeLaPartie(DonnesStatic.codePartie,DonnesStatic.pseudo)){
            this.plateforme.desactivationButton();
        }

    }


    /**
     * reprendre une partie mis en pause (suspendu)
     * @param idPartie
     * @param pseudo
     */
    public void reAccederAuJeu(String idPartie, String pseudo){
       try {
           if(this.facade.reAccederAuJeu(idPartie, pseudo)){
               this.plateforme.show();
               this.loadCarteTemp(idPartie, pseudo);
               this.loadCarteConstruction(idPartie, pseudo);
               this.loadCarteConstructionMerv(idPartie, pseudo);
               this.loadCartesDefausses(idPartie);
           }
       }
       catch (Exception e){
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le Créateur a repris cette partie.\nDemander de mettre en pause pour accéder.", ButtonType.OK);
           alert.setTitle("La partie non concernée");
           alert.showAndWait();
       }
    }

    /**
     * récupére les états du merveille
     * @param idPartie
     * @param pseudo
     * @return numéro du piramide construit
     */
    public int getEtapesMerveilleConstruite(String idPartie, String pseudo) {
        return this.facade.getEtapesMerveilleConstruite(idPartie,pseudo);
    }

    /**
     * quitté la partie si le créateur a quitté la partie et changé l'état de patie en "terminé"
     * @param idPartie
     * @param pseudo
     * @return true si le créateur à quitté la partie
     */
    public boolean quitterPartie(String idPartie, String pseudo) {
        return this.facade.quitter(idPartie,pseudo);
    }

    /**
     *
     * @param idPartie
     * @param pseudo
     * @return
     * @throws PartieNonReprendreException
     */
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

    public void goToFinPartie() {
        this.finPartie.show();
        this.finPartie.setVainqueurPartie(this.facade.getVainqueur(DonnesStatic.codePartie));
    }

    public Collection<PartieDTO> getLesParties(){
        return this.facade.getLesParties();
    }

    public Collection<RessourcesDTO> getLesRessourcesDuJoueur(String idPartie, String pseudo) {
        return this.facade.getLesRessourcesDuJoueur(idPartie,pseudo);
    }

}
