package org.example.client.modele;

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

    public FacadeProxy(){
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
            throw new RuntimeException("RMI Problem !!");
        }
    }
}
