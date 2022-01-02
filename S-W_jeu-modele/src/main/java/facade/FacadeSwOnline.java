package facade;

import dao.Dao;
import interfaces.ICarte;
import modele.*;
import packageDTOs.CarteDTO;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FacadeSwOnline implements IFacadeSwOnline{


    //patron Singleton : la partie est commune pour les joueurs concernées.

   private static IFacadeSwOnline facadeSwOnline = new FacadeSwOnline();

    public static IFacadeSwOnline cree(){
        return facadeSwOnline;
    }

    List<ICarte> cartes;



    private Partie partie;

    public FacadeSwOnline(){
        this.partie = Partie.creer();
        this.cartes = LesJeuCartes.loadData();
    }



    @Override
    public void deplacementCarte(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement) {
      this.partie.deplacer(pseudo,carte,cartes,modeDeplacement);
    }

    @Override
    public List<ICarte> getLesCartesCirculants(String pseudo) {
        return this.partie.getPartieJoueurByPseudo(pseudo).getCartesCirculantes();
    }

    @Override
    public List<ICarte> getLesCartesConstructionCite(String pseudo) {
        return this.partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionCite();
    }

    @Override
    public List<ICarte> getLesCartesConstructionMerv(String pseudo){
        return this.partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionMerveille();
    }

    public boolean passerLesCarte(String pseudo){
        return this.partie.getPartieJoueurByPseudo(pseudo).passerLesCartes();
    }


    @Override
    public void getState() {
        this.partie.notifierALaPartiJoueur();
    }

    @Override
    public void distribution(String pseudo) {

        if(this.partie.getPartieJoueurs().size() == 4) {
            switch (pseudo){
                case "a":
                    this.partie.getPartieJoueurByPseudo(pseudo).setCartesCirculantes(this.cartes.subList(0,7));
                    break;
                case "b":
                    this.partie.getPartieJoueurByPseudo(pseudo).setCartesCirculantes(this.cartes.subList(7,14));
                    break;
                case "c":
                    this.partie.getPartieJoueurByPseudo(pseudo).setCartesCirculantes(this.cartes.subList(14,21));
                    break;
                case "d":
                    this.partie.getPartieJoueurByPseudo(pseudo).setCartesCirculantes(this.cartes.subList(21,28));
                    break;
            }




        }

    }

    @Override
    public Boolean partieCommence() {
        return this.partie.partieCommence();
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
    public void accederUnePartie(String pseudo, String plateau) {
        if (this.partie.getPartieJoueurs().size() < 5 ){
            this.partie.ajouterPartieJoueur(new PartieJoueur(pseudo,plateau));
        }
    }

    @Override
    public Boolean authorisationCirculer(){
        return this.partie.authorisationCarteCirculant();
    }

    @Override
    public void notification(){
        this.partie.notifierALaPartiJoueur();
    }

    @Override
    public PartieJoueur getPartieJoueurByPseudo(String pseudo) {
        return this.partie.getPartieJoueurByPseudo(pseudo);
    }

    public Partie getPartie() {
        return partie;
    }
}
