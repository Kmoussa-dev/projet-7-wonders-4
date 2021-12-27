package facade;

import dao.Dao;
import modele.Carte;
import modele.classes.ICarte;

import java.util.Collection;
import java.util.List;

public class FacadeSwOnline implements IFacadeSwOnline{


    @Override
    public Collection<Carte> getCartes() {
        return Dao.getCartes();
    }

    @Override
    public Carte getCarte(String nom) {
        return Dao.getCartesByName(nom);
    }

    @Override
    public void autoriserCartesACirculer() {



    }
}
