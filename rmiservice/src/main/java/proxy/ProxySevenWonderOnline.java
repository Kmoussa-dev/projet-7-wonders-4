package proxy;

import exceptions.CarteDejaException;
import exceptions.CarteInexistantException;
import exceptions.partiTermineException;
import facade.FacadeSwOnline;
import facade.IFacadeSwOnline;
import interfaces.ICarte;
import interfaces.IEffet;
import interfaces.IProxySevenWonderOnline;
import modele.Carte;
import packageDTOs.CarteDTO;
import packageDTOs.Effectif;
import packageDTOs.ModeDeplacement;

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
        this.facade.getCartes().forEach(c -> carteDTOCollection.add(new CarteDTO(c.get_id(), c.getNom(), c.getEffectif(), c.getLesRessources(), c.getLesCouts())));
        return carteDTOCollection;
    }

    @Override
    public CarteDTO getCarte(String nom) throws RemoteException {
        Carte carte = this.facade.getCarte(nom);
        return new CarteDTO(carte.get_id(), carte.getNom(), carte.getEffectif(), carte.getLesRessources(), carte.getLesCouts());
    }

    @Override
    public void accederUnePartie(String pseudo, String plateau) throws RemoteException {
        this.facade.accederUnePartie(pseudo, plateau);
    }





    @Override
    public void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement) throws RemoteException, CarteInexistantException, CarteDejaException {
        this.facade.deplacementCarte(pseudo, carte, cartes, modeDeplacement);
    }

    @Override
    public Collection<CarteDTO> getLesCartesCirculants(String pseudo) throws RemoteException {
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getLesCartesCirculants(pseudo).forEach(c -> carteDTOCollection.add(new CarteDTO(c.get_id(), c.getNom(), c.getEffectif(), c.getLesRessources(), c.getLesCouts())));
        return carteDTOCollection;

    }

    @Override
    public Collection<CarteDTO> getLesCartesConstructionCite(String pseudo) throws RemoteException {
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getLesCartesConstructionCite(pseudo).forEach(c -> carteDTOCollection.add(new CarteDTO(c.get_id(), c.getNom(), c.getEffectif(), c.getLesRessources(), c.getLesCouts())));
        return carteDTOCollection;

    }

    public Collection<CarteDTO> getLesCartesConstructionMerv(String pseudo) throws RemoteException{
        Collection<CarteDTO> carteDTOCollection = new ArrayList<CarteDTO>();
        this.facade.getLesCartesConstructionMerv(pseudo).forEach(c -> carteDTOCollection.add(new CarteDTO(c.get_id(), c.getNom(), c.getEffectif(), c.getLesRessources(), c.getLesCouts())));
        return carteDTOCollection;
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
    public synchronized void notification() throws RemoteException, partiTermineException {
        this.facade.notification();
    }

    @Override
    public void setNouvellePartie(String text, String ticket, int effectif) throws RemoteException{
        this.facade.setNouvellePartie(text, ticket, effectif);
    }


}
