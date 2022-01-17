package org.example.client.modele;

import exceptions.*;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;
import packageDTOs.RessourcesDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IFacadeProxy {
    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    int getEtapesMerveilleConstruite(String idPartie, String pseudo);

    void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, PartiePleinExecption;

    void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException, CarteDejaPossederException;

    Collection<Carte> getLesCartesCirculants(String idPartie, String pseudo);

    Collection<Carte> getLesCartesConstructionCite(String idPartie, String pseudo);

    Collection<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo);

    Collection<Carte> getLesCartesDefausses(String idPartie);

    void distribution(String idPartie);

    Boolean partieCommence(String idPartie);

    Boolean authorisationCirculer(String idPartie);

    void notification(String idPartie);

    void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption;

    void inscription(String pseudo, String mdp);

    boolean connexion(String pseudo, String mdp);

    boolean reAccederAuJeu(String idPartie, String pseudo);

    Collection<PartieDTO> getLesPartiesSuspendu(String pseudo);

    boolean suspendreLaPartie(String idPartie, String pseudo) throws PartieNonReprendreException;

    boolean quitter(String idPartie, String pseudo);

    boolean reprendreUnePartie(String idPartie, String pseudo) throws PartieNonSuspenduException;

    boolean peutQuitter(String idPartie);

    Collection<PartieDTO> getLesParties();

    boolean joueurCreateurDeLaPartie(String idPartie, String pseudo);

    String getPlateauDuJoueur(String idPartie, String pseudo);

    int getAgeCourantPartie(String idPartie, String pseudo);

    String getEtatPartie(String idPartie);

    Collection<RessourcesDTO> getLesRessourcesDuJoueur(String idPartie, String pseudo);

    String getVainqueur(String idPartie);

}
