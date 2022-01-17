package facade;

import exceptions.*;
import modele.Partie;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IFacadeSwOnline {



    void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException, CarteDejaPossederException;

    List<Carte> getLesCartesCirculants(String idPartie, String pseudo);

    List<Carte> getLesCartesConstructionCite(String idPartie, String pseudo);

    List<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo);

    List<Carte> getLesCartesDefausses(String idPartie);

    void distribution(String idPartie);

    boolean partieCommence(String idPartie);

    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    int getEtapesMerveilleConstruite(String idPartie, String pseudo);

    void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, PartiePleinExecption;

    boolean authorisationCirculer(String idPartie);

    void notification(String idPartie);

    void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption;

    void inscription(String pseudo, String mdp);

    boolean connexion(String pseudo, String mdp);

    boolean reAccederAuJeu(String idPartie, String pseudo);

    Collection<Partie> getLesPartiesSuspendu();

    boolean suspendreLaPartie(String idPartie,String pseudo) throws PartieNonReprendreException;

    boolean quitter(String idPartie, String pseudo);

    boolean reprendreUnePartie(String idPartie, String pseudo) throws PartieNonSuspenduException;

    boolean peutQuitter(String idPartie);

    Collection<Partie> getLesParties();

    boolean joueurCreateurDeLaPartie(String idPartie, String pseudo);

    String getPlateauDuJoueur(String idPartie, String pseudo);

    int getAgeCourantPartie(String idPartie, String pseudo);

    Map<String, Integer> getLesRessourcesDuJoueur(String idPartie, String pseudo);

    String getEtatPartie(String idPartie);
}
