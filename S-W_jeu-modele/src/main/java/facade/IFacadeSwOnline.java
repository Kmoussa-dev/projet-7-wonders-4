package facade;

import modele.Carte;

import java.util.Collection;
import java.util.List;

public interface IFacadeSwOnline {
    Collection<Carte> getCartes();

    Carte getCarte(String nom);
}
