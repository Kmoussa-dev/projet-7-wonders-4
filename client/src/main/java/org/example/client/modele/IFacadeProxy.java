package org.example.client.modele;

import interfaces.ICarte;
import packageDTOs.CarteDTO;
import packageDTOs.ModeDeplacement;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IFacadeProxy {
    Collection<CarteDTO> getCartes();

    CarteDTO getCarte(String nom);

    void accederUnePartie(String pseudo, String plateau);


    void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement);

    Collection<CarteDTO> getLesCartesCirculants(String pseudo);

    Collection<CarteDTO> getLesCartesConstructionCite(String pseudo);

    Collection<CarteDTO> getLesCartesConstructionMerv(String pseudo);

    void getState();

    void distribution(String pseudo);
    public Boolean partieCommence();

    Boolean authorisationCirculer();

    void notification();

    boolean passerCarte(String pseudo);

    void setNouvellePartie(String text, String ticket, int effectif);


}
