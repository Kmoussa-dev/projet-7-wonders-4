package modele;

import modele.interfaces.IEtapeMerveille;
import modele.interfaces.IEffet;
import java.util.Collection;

public class Plateau {

    private String nomPlateau;
    private IEffet effetParDefaut;
    private String imagePlateau;
    private Collection<IEtapeMerveille> etapeMerveilleCollection;

}
