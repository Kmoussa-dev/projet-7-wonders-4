package org.example.client.modele;

import facade.IFacadeSwOnline;
import interfaces.ICarte;
import interfaces.IProxySevenWonderOnline;
import packageDTOs.CarteDTO;
import service.access.RMIServeurConnexion;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.List;

public class FacadeProxy implements IFacadeProxy {

    private IProxySevenWonderOnline jeuFacade;

    private static IFacadeProxy facadeProxy = new FacadeProxy();

    public static IFacadeProxy cree(){
        return facadeProxy;
    }

    private FacadeProxy(){
        try {
            Registry registry = LocateRegistry.getRegistry(RMIServeurConnexion.PORT);
            this.jeuFacade = (IProxySevenWonderOnline) registry.lookup(RMIServeurConnexion.DOMAIN_NAME);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<CarteDTO> getCartes() {
        try {
            return this.jeuFacade.getCartes();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public CarteDTO getCarte(String nom) {
        try {
            return this.jeuFacade.getCarte(nom);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void accederUnePartie(String pseudo, String plateau) {
        try {
            this.jeuFacade.accederUnePartie(pseudo,plateau);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes){
        try {
            this.jeuFacade.deplacementCarte(pseudo,carte, cartes);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<CarteDTO> getLesCartesCirculants(String pseudo) {
        try {
            return this.jeuFacade.getLesCartesCirculants(pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<CarteDTO> getLesCartesConstructionCite(String pseudo) {
        try {
            return this.jeuFacade.getLesCartesConstructionCite(pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getState() {
        try {
            this.jeuFacade.getState();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void distribution(String pseudo) {
        try {
            this.jeuFacade.distribution(pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean partieCommence() {
        try {
            return this.jeuFacade.partieCommence();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean authorisationCirculer() {
        try {
            return this.jeuFacade.authorisationCirculer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void notification() {
        try {
            this.jeuFacade.notification();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public  boolean passerCarte(String pseudo){
        try {
            return this.jeuFacade.passerCarte(pseudo);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return false;
    }


}
