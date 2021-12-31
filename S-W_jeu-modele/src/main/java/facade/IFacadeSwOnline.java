package facade;

import interfaces.ICarte;
import modele.Carte;
import modele.PartieJoueur;

import java.util.Collection;
import java.util.List;

public interface IFacadeSwOnline {

    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    void accederUnePartie(String pseudo, String plateau);

    void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes);

    List<ICarte> getLesCartesCirculants(String pseudo);

    List<ICarte> getLesCartesConstructionCite(String pseudo);

    void getState();

    void distribution(String pseudo);

    Boolean partieCommence();

    Boolean authorisationCirculer();

    void notification();

    PartieJoueur getPartieJoueurByPseudo(String pseudo);
    public boolean passerLesCarte(String pseudo);




}
