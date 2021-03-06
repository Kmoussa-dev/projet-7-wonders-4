package proxy;

import exceptions.*;
import facade.FacadeSwOnline;
import facade.IFacadeSwOnline;
import interfaces.IProxySevenWonderOnline;
import modele.Partie;
import modele.PartieJoueur;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;
import packageDTOs.RessourcesDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ProxySevenWonderOnline extends UnicastRemoteObject implements IProxySevenWonderOnline {

    private IFacadeSwOnline facade;

    /**
     * constructeur
     * @throws RemoteException
     */
    public ProxySevenWonderOnline() throws RemoteException {
        super(0);
        this.facade = new  FacadeSwOnline();
    }
    @Override
    public void accederUnePartie(String idPartie, String pseudo) throws RemoteException, partieDejaTermineException, partieInexistantException, PartiePleinExecption {
        this.facade.accederUnePartie(idPartie,pseudo);
    }


    @Override
    public void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws RemoteException, CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException, CarteDejaPossederException {
        this.facade.deplacementCarte(idPartie, pseudo, carte, cartes, modeDeplacement);
    }

    @Override
    public Collection<Carte> getLesCartesCirculants(String idPartie, String pseudo) throws RemoteException {
        return this.facade.getLesCartesCirculants(idPartie, pseudo);
    }

    @Override
    public Collection<Carte> getLesCartesConstructionCite(String idPartie, String pseudo) throws RemoteException {
         return this.facade.getLesCartesConstructionCite(idPartie, pseudo);
    }

    @Override
    public Collection<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo) throws RemoteException{
        return this.facade.getLesCartesConstructionMerv(idPartie,pseudo);
    }

    @Override
    public Collection<Carte> getLesCartesDefausses(String idPartie) throws RemoteException {
        return this.facade.getLesCartesDefausses(idPartie);
    }

    @Override
    public int getEtapesMerveilleConstruite(String idPartie, String pseudo) throws RemoteException {
        return  this.facade.getEtapesMerveilleConstruite(idPartie, pseudo);
    }

    @Override
    public void distribution(String idPartie) throws RemoteException {
        this.facade.distribution(idPartie);
    }

    @Override
    public Boolean partieCommence(String idPartie) throws RemoteException {
        return this.facade.partieCommence(idPartie);
    }

    @Override
    public Boolean authorisationCirculer(String idPartie) throws RemoteException {
        return this.facade.authorisationCirculer(idPartie);
    }

    @Override
    public synchronized void notification(String idPartie) throws RemoteException {
        this.facade.notification(idPartie);
    }

    @Override
    public void inscription(String pseudo, String mdp) throws RemoteException{
        this.facade.inscription(pseudo,mdp);
    }

    @Override
    public boolean connexion(String pseudo, String mdp)throws RemoteException{
        return this.facade.connexion(pseudo,mdp);
    }

    @Override
    public void setNouvellePartie(String pseudo, String ticket) throws RemoteException, PartiePleinExecption {
        this.facade.setNouvellePartie(pseudo, ticket);
    }

    @Override
    public boolean reAccederAuJeu(String idPartie, String pseudo)throws RemoteException {
        return facade.reAccederAuJeu(idPartie,pseudo);
    }

    @Override
    public Collection<PartieDTO> getLesPartiesSuspendu(String pseudo) throws RemoteException{
        Collection<PartieDTO> partieDTOCollection = new ArrayList<PartieDTO>();
        for (Partie partie: this.facade.getLesPartiesSuspendu()){
            if (Objects.nonNull(partie.getPartieJoueurByPseudo(pseudo))){
                PartieDTO partieDTO = new PartieDTO(partie.getId(), partie.getEtatPartie().toString(), partie.getDateCreation());
                for (PartieJoueur partieJoueur : partie.getPartieJoueurs()){
                    partieDTO.ajouterNomJoueur(partieJoueur.getJoueur());
                }
                partieDTOCollection.add(partieDTO);
            }
        }
        return partieDTOCollection;
    }

    @Override
    public boolean suspendreLaPartie(String idPartie, String pseudo) throws RemoteException, PartieNonReprendreException {
        return this.facade.suspendreLaPartie(idPartie, pseudo);
    }

    @Override
    public boolean quitter(String idPartie, String pseudo)throws RemoteException {
        return this.facade.quitter(idPartie, pseudo);
    }

    @Override
    public boolean reprendreUnePartie(String idPartie, String pseudo) throws RemoteException, PartieNonSuspenduException {
        return  this.facade.reprendreUnePartie(idPartie,pseudo);
    }

    @Override
    public boolean peutQuitter(String idPartie) throws RemoteException{
        return this.facade.peutQuitter(idPartie);
    }

    @Override
    public Collection<PartieDTO> getLesParties() throws  RemoteException{
        Collection<PartieDTO> partieDTOCollection = new ArrayList<PartieDTO>();
        for (Partie partie: this.facade.getLesParties()){
            PartieDTO partieDTO = new PartieDTO(partie.getId(), partie.getEtatPartie().toString(), partie.getDateCreation());
            for (PartieJoueur partieJoueur : partie.getPartieJoueurs()){
                partieDTO.ajouterNomJoueur(partieJoueur.getJoueur());
            }
            partieDTOCollection.add(partieDTO);
        }
        return partieDTOCollection;
    }

    @Override
    public boolean joueurCreateurDeLaPartie(String idPartie, String pseudo) throws RemoteException {
        return this.facade.joueurCreateurDeLaPartie(idPartie,pseudo);
    }

    @Override
    public String getPlateauDuJoueur(String idPartie, String pseudo) throws RemoteException {
        return this.facade.getPlateauDuJoueur(idPartie,pseudo);
    }

    @Override
    public int getAgeCourantPartie(String idPartie, String pseudo) throws RemoteException {
        return this.facade.getAgeCourantPartie(idPartie,pseudo);
    }

    @Override
    public Collection<RessourcesDTO> getLesRessourcesDuJoueur(String idPartie, String pseudo) throws RemoteException {

        Collection<RessourcesDTO> lesRessourcesDTO = new ArrayList<>();
        this.facade.getLesRessourcesDuJoueur(idPartie,pseudo).forEach((k,v)->lesRessourcesDTO.add(new RessourcesDTO(k,v)));
        return lesRessourcesDTO;

    }

    @Override
    public String getEtatPartie(String idPartie) throws RemoteException {
        return this.facade.getEtatPartie(idPartie);
    }

    @Override
    public String getVainqueur(String idPartie) throws RemoteException {
        return this.facade.getVainqueur(idPartie);
    }
}
