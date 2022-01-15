package facade;

import com.mongodb.DuplicateKeyException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dao.Dao;
import exceptions.*;
import interfaces.ICarte;
import modele.*;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FacadeSwOnline implements IFacadeSwOnline{


    //patron Singleton : la partie est commune pour les joueurs concernées.

   private static IFacadeSwOnline facadeSwOnline = new FacadeSwOnline();

    public static IFacadeSwOnline cree(){
        return facadeSwOnline;
    }





    private Partie partie;

    public FacadeSwOnline(){
        this.partie = new Partie();
        //this.cartes = LesJeuCartes.loadData();
    }



    @Override
    public void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieSuspenduOuTermine {
        Dao.deplacementCarte(idPartie, pseudo, carte, cartes, modeDeplacement);
        //this.partie.deplacer(pseudo,carte,cartes,modeDeplacement);
    }

    @Override
    public List<Carte> getLesCartesCirculants(String idPartie, String pseudo) {
        return Dao.getLesCartesCirculants(idPartie, pseudo);
        //return this.partie.getPartieJoueurByPseudo(pseudo).getCartesCirculantes();
    }

    @Override
    public List<Carte> getLesCartesConstructionCite(String idPartie, String pseudo) {
        return Dao.getLesCartesConstructionCite(idPartie,pseudo);
        //return this.partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionCite();
    }

    @Override
    public List<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo){
        return Dao.getLesCartesConstructionMerv(idPartie, pseudo);
        //return this.partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionMerveille();
    }

    @Override
    public void distribution(String idPartie) {
        Dao.distributionCarteDebut(idPartie);
    }

    @Override
    public boolean partieCommence(String idPartie) {
        return Dao.partieCommence(idPartie);
        //return this.partie.partieCommence();
    }

    @Override
    public Collection<Carte> getCartes() {
        return Dao.getCartes();
    }

    @Override
    public Carte getCarte(String nom) {
        return Dao.getCartesByName(nom);
    }

    @Override
    public void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, partiePleinExecption {
        Dao.accederUnePartie(idPartie,pseudo);
    }

    @Override
    public boolean authorisationCirculer(String idPartie){
        return  Dao.authorisationCirculer(idPartie);
        //return this.partie.authorisationCarteCirculant();
    }

    @Override
    public void notification(String idPartie) {
        Dao.notification(idPartie);
        //this.partie.notifierALaPartiJoueur();
    }

    @Override
    public void setNouvellePartie(String pseudo, String ticket)throws partiePleinExecption {
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
    public boolean suspendreLaPartie(String idPartie, String pseudo){
        return Dao.suspendreLaPartie(idPartie, pseudo);

    }

    @Override
    public boolean quitter(String idPartie, String pseudo){
       return Dao.quitter(idPartie,pseudo);
    }


    @Override
    public boolean reprendreUnePartie(String idPartie, String pseudo){
        return Dao.reprendreUnePartie(idPartie,pseudo);
    }



}
