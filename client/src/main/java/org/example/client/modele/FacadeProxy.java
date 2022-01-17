package org.example.client.modele;

import exceptions.*;
import interfaces.IProxySevenWonderOnline;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.PartieDTO;
import packageDTOs.RessourcesDTO;
import service.access.RMIServeurConnexion;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public Collection<Carte> getCartes() {
        try {
            return this.jeuFacade.getCartes();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public Carte getCarte(String nom) {
        try {
            return this.jeuFacade.getCarte(nom);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getEtapesMerveilleConstruite(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getEtapesMerveilleConstruite(idPartie,pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException("erreur rmi");
        }
    }

    @Override
    public void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, PartiePleinExecption {
        try {
            this.jeuFacade.accederUnePartie(idPartie,pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException, CarteDejaPossederException {
        try {
            this.jeuFacade.deplacementCarte(idPartie, pseudo,carte, cartes, modeDeplacement);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Carte> getLesCartesCirculants(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getLesCartesCirculants(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Carte> getLesCartesConstructionCite(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getLesCartesConstructionCite(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getLesCartesConstructionMerv(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Carte> getLesCartesDefausses(String idPartie) {
        try {
            return this.jeuFacade.getLesCartesDefausses(idPartie);
        } catch (RemoteException e) {
            throw new RuntimeException("erreur rmi");
        }
    }


    @Override
    public void distribution(String idPartie) {
        try {
            this.jeuFacade.distribution(idPartie);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean partieCommence(String idPartie) {
        try {
            return this.jeuFacade.partieCommence(idPartie);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean authorisationCirculer(String idPartie) {
        try {
            return this.jeuFacade.authorisationCirculer(idPartie);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void notification(String idPartie) {
        try {
            this.jeuFacade.notification(idPartie);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption {
        try {
            this.jeuFacade.setNouvellePartie(pseudo, ticket);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inscription(String pseudo, String mdp){
        try {
            this.jeuFacade.inscription(pseudo, mdp);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean connexion(String pseudo, String mdp)  {
        try {
            return this.jeuFacade.connexion(pseudo, mdp);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean reAccederAuJeu(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.reAccederAuJeu(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<PartieDTO> getLesPartiesSuspendu(String pseudo) {
        try {
            return this.jeuFacade.getLesPartiesSuspendu(pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean suspendreLaPartie(String idPartie, String pseudo) throws PartieNonReprendreException {
        try {
            return this.jeuFacade.suspendreLaPartie(idPartie,pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean quitter(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.quitter(idPartie,pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean reprendreUnePartie(String idPartie, String pseudo) throws PartieNonSuspenduException {
        try {
            return this.jeuFacade.reprendreUnePartie(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean peutQuitter(String idPartie) {
        try {
            return this.jeuFacade.peutQuitter(idPartie);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Collection<PartieDTO> getLesParties(){
        try {
            return this.jeuFacade.getLesParties();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean joueurCreateurDeLaPartie(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.joueurCreateurDeLaPartie(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getPlateauDuJoueur(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getPlateauDuJoueur(idPartie, pseudo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAgeCourantPartie(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getAgeCourantPartie(idPartie,pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException("erreur rmi!");
        }
    }

    @Override
    public String getEtatPartie(String idPartie) {
        try {
            return this.jeuFacade.getEtatPartie(idPartie);
        } catch (RemoteException e) {
            throw new RuntimeException("erreur rmi!");
        }
    }

    @Override
    public Collection<RessourcesDTO> getLesRessourcesDuJoueur(String idPartie, String pseudo) {
        try {
            return this.jeuFacade.getLesRessourcesDuJoueur(idPartie, pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException("erreur rmi!");
        }
    }
}
