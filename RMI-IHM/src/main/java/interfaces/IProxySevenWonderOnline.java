package interfaces;

import packageDTOs.CarteDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IProxySevenWonderOnline extends Remote {
    Collection<CarteDTO> getCartes() throws RemoteException;
    CarteDTO getCarte(String nom) throws RemoteException;

    void accederUnePartie(String pseudo, String plateau) throws RemoteException;


    void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes)throws RemoteException;

    Collection<CarteDTO> getLesCartesCirculants(String pseudo)throws RemoteException;

    Collection<CarteDTO> getLesCartesConstructionCite(String pseudo)throws RemoteException;

    void getState()throws RemoteException;

    void distribution(String pseudo)throws RemoteException;

    Boolean partieCommence() throws RemoteException;

    Boolean authorisationCirculer()throws RemoteException;

    void notification()throws RemoteException;

    boolean passerCarte(String pseudo) throws RemoteException;



}
