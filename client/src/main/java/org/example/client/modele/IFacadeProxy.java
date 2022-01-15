package org.example.client.modele;

import com.mongodb.DuplicateKeyException;
import exceptions.*;
import interfaces.ICarte;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IFacadeProxy {
    Collection<Carte> getCartes();

    Carte getCarte(String nom);

    void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, partiePleinExecption;

    void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieSuspenduOuTermine;

    Collection<Carte> getLesCartesCirculants(String idPartie, String pseudo);

    Collection<Carte> getLesCartesConstructionCite(String idPartie, String pseudo);

    Collection<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo);

    void distribution(String idPartie);

    Boolean partieCommence(String idPartie);

    Boolean authorisationCirculer(String idPartie);

    void notification(String idPartie);

    void setNouvellePartie(String pseudo, String ticket) throws partiePleinExecption;

    void inscription(String pseudo, String mdp);

    boolean connexion(String pseudo, String mdp);

    boolean reAccederAuJeu(String idPartie, String pseudo);

    Collection<PartieDTO> getLesPartiesSuspendu();

    boolean suspendreLaPartie(String idPartie, String pseudo);

    boolean quitter(String idPartie, String pseudo);

    boolean reprendreUnePartie(String idPartie, String pseudo);


}
