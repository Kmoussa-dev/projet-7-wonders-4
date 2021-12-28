package facade;

import dao.Dao;
import modele.*;
import modele.interfaces.ICarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FacadeSwOnline implements IFacadeSwOnline{


    //patron Singleton : la partie est commune pour les joueurs concernées.

    private static FacadeSwOnline facadeSwOnline = new FacadeSwOnline();

    public static FacadeSwOnline cree(){
        return facadeSwOnline;
    }

    List<ICarte> cartes;

    List<PartieJoueur> lesPartieJoueurs;

    public Partie partie;

    public FacadeSwOnline(){
        this.partie = new Partie();
        this.cartes = new ArrayList<>();
        this.lesPartieJoueurs = new ArrayList<>();
    }

    public void loadData(){
        this.lesPartieJoueurs.add(new PartieJoueur("Alice","Egypte"));
        this.lesPartieJoueurs.add(new PartieJoueur("Bob","Babylone"));
        this.lesPartieJoueurs.add(new PartieJoueur("Charlie","Londres"));
        this.lesPartieJoueurs.add(new PartieJoueur("Daniel","Alexandre"));


        this.cartes.add(new Carte("carte1","rouge",5));
        this.cartes.add(new Carte("carte2","rouge",4));
        this.cartes.add(new Carte("carte3","rouge",3));
        this.cartes.add(new Carte("carte4","rouge",5));
        this.cartes.add(new Carte("carte5","rouge",5));
        this.cartes.add(new Carte("carte6","rouge",8));
        this.cartes.add(new Carte("carte7","rouge",5));


        this.cartes.add(new Carte("carte8","rouge",5));
        this.cartes.add(new Carte("carte9","rouge",5));
        this.cartes.add(new Carte("carte10","rouge",10));
        this.cartes.add(new Carte("carte11","rouge",8));
        this.cartes.add(new Carte("carte12","rouge",5));
        this.cartes.add(new Carte("carte13","rouge",7));
        this.cartes.add(new Carte("carte14","rouge",5));

        this.cartes.add(new Carte("carte15","rouge",5));
        this.cartes.add(new Carte("carte16","rouge",5));
        this.cartes.add(new Carte("carte17","rouge",5));
        this.cartes.add(new Carte("carte18","rouge",5));
        this.cartes.add(new Carte("carte19","rouge",5));
        this.cartes.add(new Carte("carte20","rouge",5));
        this.cartes.add(new Carte("carte21","rouge",5));

        this.cartes.add(new Carte("carte22","rouge",5));
        this.cartes.add(new Carte("carte23","rouge",5));
        this.cartes.add(new Carte("carte24","rouge",5));
        this.cartes.add(new Carte("carte25","rouge",5));
        this.cartes.add(new Carte("carte26","rouge",5));
        this.cartes.add(new Carte("carte27","rouge",5));
        this.cartes.add(new Carte("carte28","rouge",5));

        for(ICarte carte : this.cartes){
            if(this.lesPartieJoueurs.get(0).getCartesCirculantes().size() < 7)
            {
                this.lesPartieJoueurs.get(0).getCartesCirculantes().add(carte);
            }
            else if (this.lesPartieJoueurs.get(1).getCartesCirculantes().size() < 7)
            {
                this.lesPartieJoueurs.get(1).getCartesCirculantes().add(carte);
            }
            else if (this.lesPartieJoueurs.get(2).getCartesCirculantes().size() < 7)
            {
                this.lesPartieJoueurs.get(2).getCartesCirculantes().add(carte);
            }
            else if (this.lesPartieJoueurs.get(3).getCartesCirculantes().size() < 7)
            {
                this.lesPartieJoueurs.get(3).getCartesCirculantes().add(carte);
            }
            else {}
        }

        partie.setPartieJoueurs(this.lesPartieJoueurs);

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
    public void autoriserCartesACirculer() {



    }
}
