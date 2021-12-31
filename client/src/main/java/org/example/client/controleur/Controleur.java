package org.example.client.controleur;

import interfaces.ICarte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.example.client.modele.FacadeProxy;
import org.example.client.modele.IFacadeProxy;
import org.example.client.vues.Accueil;
import org.example.client.vues.TestPlatorm;
import packageDTOs.CarteDTO;

import java.rmi.RemoteException;
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
        this.accueil.charger(carteDTOS);
    }

    public void loadCarteTemp(String pseudo){
        ObservableList<ICarte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesCirculants(pseudo));
        this.testPlatorm.charger(carteDTOS);

    }

    public void loadCarteConstruction(String pseudo){
        ObservableList<ICarte> carteDTOS = FXCollections.observableArrayList(this.facade.getLesCartesConstructionCite(pseudo));
        this.testPlatorm.chargerContsructionCite(carteDTOS);

    }

    public void run(){
        this.testPlatorm.show();
    }

    public void accederAuJeu(String text) {
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
        this.testPlatorm.activerBuutonDeplacer();
        this.loadCarteTemp(pseudo);
        this.loadCarteConstruction(pseudo);
    }


    public void refrexh(String pseudo){
        this.loadCarteTemp(pseudo);
        this.loadCarteConstruction(pseudo);
    }

    public void deplacerCarte(String pseudo, ICarte nomCarte, List<ICarte> cartes){
        this.facade.deplacementCarte(pseudo,nomCarte, cartes);
        this.loadCarteConstruction(pseudo);
        this.loadCarteTemp(pseudo);

    }

    public boolean passerCarte(String pseudo){
        return this.facade.passerCarte(pseudo);
    }
}
