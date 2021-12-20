package org.example.client.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.example.client.modele.FacadeProxy;
import org.example.client.vues.Accueil;
import packageDTOs.CarteDTO;

public class Controleur {
    private FacadeProxy facade;
    private Accueil accueil;

    public Controleur(Stage stage){
        this.facade = new FacadeProxy();
        this.accueil = Accueil.creer(stage);
        this.accueil.initialiserControleur(this);
    }

    public void loadData(){
        ObservableList<CarteDTO> carteDTOS = FXCollections.observableArrayList(this.facade.getCartes());
        this.accueil.charger(carteDTOS);
    }

    public void run(){
        this.accueil.show();
    }
}
