package proxy;

import facade.FacadeSwOnline;
import facade.IFacadeSwOnline;
import interfaces.ICarte;
import interfaces.IProxySevenWonderOnline;
import modele.Carte;
import packageDTOs.CarteDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProxySevenWonderOnline extends UnicastRemoteObject implements IProxySevenWonderOnline {

    private IFacadeSwOnline facade;

    public ProxySevenWonderOnline() throws RemoteException {
        super(0);
        this.facade = FacadeSwOnline.cree();
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

    @Override
    public void accederUnePartie(String pseudo, String plateau) throws RemoteException {
        this.facade.accederUnePartie(pseudo, plateau);
    }

    public boolean passerCarte(String pseudo) throws RemoteException{
        return  this.facade.passerLesCarte(pseudo);
    }



    @Override
    public void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes) throws RemoteException {
        this.facade.deplacementCarte(pseudo, carte, cartes);
    }

    @Override
    public Collection<CarteDTO> getLesCartesCirculants(String pseudo) throws RemoteException {
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getLesCartesCirculants(pseudo).forEach(c -> carteDTOCollection.add(new CarteDTO(c.getNom(), c.getCouleur(), c.getValeur())));
        return carteDTOCollection;

    }

    @Override
    public Collection<CarteDTO> getLesCartesConstructionCite(String pseudo) throws RemoteException {
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getLesCartesConstructionCite(pseudo).forEach(c -> carteDTOCollection.add(new CarteDTO(c.getNom(), c.getCouleur(), c.getValeur())));
        return carteDTOCollection;

    }

    @Override
    public void getState() throws RemoteException {
        this.facade.getState();
    }

    @Override
    public void distribution(String pseudo) throws RemoteException {
        this.facade.distribution(pseudo);
    }

    @Override
    public Boolean partieCommence() throws RemoteException {
        return this.facade.partieCommence();
    }

    @Override
    public Boolean authorisationCirculer() throws RemoteException {
        return this.facade.authorisationCirculer();
    }

    @Override
    public synchronized void notification() throws RemoteException {
        this.facade.notification();
    }




}
