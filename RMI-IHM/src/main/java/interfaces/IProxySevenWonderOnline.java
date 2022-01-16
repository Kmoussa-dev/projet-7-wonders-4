package interfaces;

import com.mongodb.DuplicateKeyException;
import exceptions.*;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IProxySevenWonderOnline extends Remote {
    Collection<Carte> getCartes() throws RemoteException;
    Carte getCarte(String nom) throws RemoteException;


    void accederUnePartie(String idPartie, String pseudo) throws RemoteException, partieDejaTermineException, partieInexistantException, PartiePleinExecption;

    void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws RemoteException, CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException;

    Collection<Carte> getLesCartesCirculants(String idPartie, String pseudo)throws RemoteException;

    Collection<Carte> getLesCartesConstructionCite(String idPartie, String pseudo)throws RemoteException;

    Collection<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo) throws RemoteException;

    Collection<Carte> getLesCartesDefausses(String idPartie) throws RemoteException;

    int getEtapesMerveilleConstruite(String idPartie, String pseudo) throws RemoteException;

    void distribution(String idPartie)throws RemoteException;

    Boolean partieCommence(String idPartie) throws RemoteException;

    Boolean authorisationCirculer(String idPartie)throws RemoteException;

    void notification(String idPartie) throws RemoteException;

    void setNouvellePartie(String pseudo, String ticket) throws RemoteException, PartiePleinExecption;

    void inscription(String pseudo, String mdp) throws RemoteException;

    boolean connexion(String pseudo, String mdp)throws RemoteException;

    boolean reAccederAuJeu(String idPartie, String pseudo)throws RemoteException;

    Collection<PartieDTO> getLesPartiesSuspendu()throws RemoteException;

    boolean suspendreLaPartie(String idPartie, String pseudo) throws RemoteException, PartieNonReprendreException;

    boolean quitter(String idPartie, String pseudo)throws RemoteException;

    boolean reprendreUnePartie(String idPartie, String pseudo) throws RemoteException, PartieNonSuspenduException;

    boolean peutQuitter(String idPartie) throws RemoteException;
}
