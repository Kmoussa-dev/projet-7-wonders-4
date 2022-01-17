package facade;

import dao.Dao;
import exceptions.*;
import modele.*;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;


import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FacadeSwOnline implements IFacadeSwOnline{


    @Override
    public void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException, CarteDejaPossederException {
        Dao.deplacementCarte(idPartie, pseudo, carte, cartes, modeDeplacement);
    }

    @Override
    public List<Carte> getLesCartesCirculants(String idPartie, String pseudo) {
        return Dao.getLesCartesCirculants(idPartie, pseudo);
    }

    @Override
    public List<Carte> getLesCartesConstructionCite(String idPartie, String pseudo) {
        return Dao.getLesCartesConstructionCite(idPartie,pseudo);
    }

    @Override
    public List<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo){
        return Dao.getLesCartesConstructionMerv(idPartie, pseudo);
    }

    @Override
    public List<Carte> getLesCartesDefausses(String idPartie) {
        return Dao.getLesCartesDefausses(idPartie);
    }

    @Override
    public void distribution(String idPartie) {Dao.distributionCarteDebut(idPartie);}

    @Override
    public boolean partieCommence(String idPartie) {
        return Dao.partieCommence(idPartie);
    }

    @Override
    public int getEtapesMerveilleConstruite(String idPartie, String pseudo) {
        return Dao.getEtapesMerveilleConstruite(idPartie,pseudo);
    }

    @Override
    public void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, PartiePleinExecption, partieInexistantException {
        Dao.accederUnePartie(idPartie,pseudo);
    }

    @Override
    public boolean authorisationCirculer(String idPartie){
        return  Dao.authorisationCirculer(idPartie);
    }

    @Override
    public void notification(String idPartie) {
        Dao.notification(idPartie);
    }

    @Override
    public void setNouvellePartie(String pseudo, String ticket) throws PartiePleinExecption {
        Dao.creerUnePartie(pseudo,ticket);
    }

    @Override
    public void inscription(String pseudo, String mdp) {
        Dao.inscription(pseudo,mdp);
    }

    @Override
    public boolean connexion(String pseudo, String mdp) {
       return Dao.connexion(pseudo,mdp);
    }

    @Override
    public boolean reAccederAuJeu(String idPartie, String pseudo){
       return Dao.reAccederAuJeu(idPartie, pseudo);
    }

    @Override
    public Collection<Partie> getLesPartiesSuspendu(){
       return Dao.getLesPartiesSuspendu();
    }

    @Override
    public boolean suspendreLaPartie(String idPartie, String pseudo) throws PartieNonReprendreException {
        return Dao.suspendreLaPartie(idPartie, pseudo);
    }

    @Override
    public boolean quitter(String idPartie, String pseudo){
       return Dao.quitter(idPartie,pseudo);
    }


    @Override
    public boolean reprendreUnePartie(String idPartie, String pseudo) throws PartieNonSuspenduException {
        return Dao.reprendreUnePartie(idPartie,pseudo);
    }

    @Override
    public boolean peutQuitter(String idPartie) {
        return Dao.peutQuitter(idPartie);
    }

    @Override
    public Collection<Partie> getLesParties(){
        return Dao.getLesParties();
    }

    @Override
    public boolean joueurCreateurDeLaPartie(String idPartie, String pseudo) {
        return Dao.joueurCreateurDeLaPartie(idPartie,pseudo);
    }

    @Override
    public String getPlateauDuJoueur(String idPartie, String pseudo) {
        return Dao.getPlateauDuJoueur(idPartie,pseudo);
    }

    @Override
    public int getAgeCourantPartie(String idPartie, String pseudo) {
        return Dao.getAgeCourantPartie(idPartie,pseudo);
    }

    @Override
    public Map<String, Integer> getLesRessourcesDuJoueur(String idPartie, String pseudo) {
        return Dao.getLesRessourcesDuJoueur(idPartie,pseudo);
    }

    @Override
    public String getEtatPartie(String idPartie) {
        return Dao.getEtatPartie(idPartie);
    }

    @Override
    public String getVainqueur(String idPartie) {
        return Dao.getVainqueur(idPartie);
    }


}
