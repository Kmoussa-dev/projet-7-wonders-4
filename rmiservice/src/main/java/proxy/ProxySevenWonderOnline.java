package proxy;

import facade.FacadeSwOnline;
import facade.IFacadeSwOnline;
import interfaces.IProxySevenWonderOnline;
import modele.Carte;
import packageDTOs.CarteDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

public class ProxySevenWonderOnline extends UnicastRemoteObject implements IProxySevenWonderOnline {

    private IFacadeSwOnline facade;

    public ProxySevenWonderOnline() throws RemoteException {
        super(0);
        this.facade = new FacadeSwOnline();
    }

    @Override
    public Collection<CarteDTO> getCartes() throws RemoteException {
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getCartes().forEach(c -> carteDTOCollection.add(new CarteDTO(c.getNom(), c.getCouleur(), c.getValeur())));
        return carteDTOCollection;
    }

    @Override
    public CarteDTO getCarte(String nom) throws RemoteException {
        Carte carte = this.facade.getCarte(nom);
        return new CarteDTO(carte.getNom(), carte.getCouleur(), carte.getValeur());
    }
}
