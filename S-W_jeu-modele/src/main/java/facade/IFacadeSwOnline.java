package facade;

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

    void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement);

    List<ICarte> getLesCartesCirculants(String pseudo);

    List<ICarte> getLesCartesConstructionCite(String pseudo);

    List<ICarte> getLesCartesConstructionMerv(String pseudo);

    void getState() throws partiTermineException;

    void distribution(String pseudo);

    Boolean partieCommence();

    Boolean authorisationCirculer();

    void notification() throws partiTermineException;

    PartieJoueur getPartieJoueurByPseudo(String pseudo);
    public boolean passerLesCarte(String pseudo);


    void setNouvellePartie(String text, String ticket, int effectif);
}
