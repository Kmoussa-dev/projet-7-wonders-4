package facade;

import exceptions.CarteDejaException;
import exceptions.CarteInexistantException;
import exceptions.partiTermineException;
import interfaces.ICarte;
import modele.Carte;
import modele.PartieJoueur;
import packageDTOs.ModeDeplacement;

import java.util.Collection;
import java.util.List;

public interface IFacadeSwOnline {

    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    void accederUnePartie(String pseudo, String plateau);

    void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException;

    List<ICarte> getLesCartesCirculants(String pseudo);

    List<ICarte> getLesCartesConstructionCite(String pseudo);

    List<ICarte> getLesCartesConstructionMerv(String pseudo);


    void distribution(String pseudo);

    boolean partieCommence();

    boolean authorisationCirculer();

    void notification() throws partiTermineException;

    void setNouvellePartie(String text, String ticket, int effectif);
}
