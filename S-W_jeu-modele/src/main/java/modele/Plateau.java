package modele;

import modele.interfaces.IEtapeMerveille;
import modele.interfaces.IEffet;
import java.util.Collection;

public class Plateau {

    private String nomPlateau;
    private IEffet effetParDefaut;
    private String imagePlateau;

    public Plateau(String nomPlateau, IEffet effetParDefaut, String imagePlateau){
        this.nomPlateau = nomPlateau;
        this.effetParDefaut = effetParDefaut;
        this.imagePlateau = imagePlateau;
    }

    private Collection<IEtapeMerveille> etapeMerveilleCollection;

}
