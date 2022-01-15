package facade;

import exceptions.*;
import interfaces.ICarte;
import modele.Partie;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.Collection;
import java.util.List;

public interface IFacadeSwOnline {



    void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieSuspenduOuTermine;

    List<Carte> getLesCartesCirculants(String idPartie, String pseudo);

    List<Carte> getLesCartesConstructionCite(String idPartie, String pseudo);

    List<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo);

    void distribution(String idPartie);

    boolean partieCommence(String idPartie);

    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, partiePleinExecption;

    boolean authorisationCirculer(String idPartie);

    void notification(String idPartie);

    void setNouvellePartie(String pseudo, String ticket)throws partiePleinExecption;

    void inscription(String pseudo, String mdp);

    boolean connexion(String pseudo, String mdp);

    boolean reAccederAuJeu(String idPartie, String pseudo);

    Collection<Partie> getLesPartiesSuspendu();

    boolean suspendreLaPartie(String idPartie,String pseudo);

    boolean quitter(String idPartie, String pseudo);

    boolean reprendreUnePartie(String idPartie, String pseudo);
}
