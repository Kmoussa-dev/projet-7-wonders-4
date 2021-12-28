package facade;

import modele.Carte;
import modele.PartieJoueur;
import modele.interfaces.ICarte;

import java.util.ArrayList;
import java.util.List;

public class TestingFacade {
    public static void main(String[] args) {
        List<ICarte> cartes = new ArrayList<>();

        cartes.add(new Carte("carte1","rouge",5));
        cartes.add(new Carte("carte2","rouge",4));
        cartes.add(new Carte("carte3","rouge",3));
        cartes.add(new Carte("carte4","rouge",5));
        cartes.add(new Carte("carte5","rouge",5));
        cartes.add(new Carte("carte6","rouge",8));
        cartes.add(new Carte("carte7","rouge",5));


        cartes.add(new Carte("carte8","rouge",5));
        cartes.add(new Carte("carte9","rouge",5));
        cartes.add(new Carte("carte10","rouge",10));
        cartes.add(new Carte("carte11","rouge",8));
        cartes.add(new Carte("carte12","rouge",5));
        cartes.add(new Carte("carte13","rouge",7));
        cartes.add(new Carte("carte14","rouge",5));

        cartes.add(new Carte("carte15","rouge",5));
        cartes.add(new Carte("carte16","rouge",5));
        cartes.add(new Carte("carte17","rouge",5));
        cartes.add(new Carte("carte18","rouge",5));
        cartes.add(new Carte("carte19","rouge",5));
        cartes.add(new Carte("carte20","rouge",5));
        cartes.add(new Carte("carte21","rouge",5));

        cartes.add(new Carte("carte22","rouge",5));
        cartes.add(new Carte("carte23","rouge",5));
        cartes.add(new Carte("carte24","rouge",5));
        cartes.add(new Carte("carte25","rouge",5));
        cartes.add(new Carte("carte26","rouge",5));
        cartes.add(new Carte("carte27","rouge",5));
        cartes.add(new Carte("carte28","rouge",5));

        FacadeSwOnline facadeSwOnline = FacadeSwOnline.cree();

        facadeSwOnline.loadData();

        PartieJoueur partieJoueur1 = facadeSwOnline.lesPartieJoueurs.get(0);
        PartieJoueur partieJoueur2 = facadeSwOnline.lesPartieJoueurs.get(1);
        PartieJoueur partieJoueur3 = facadeSwOnline.lesPartieJoueurs.get(2);
        PartieJoueur partieJoueur4 = facadeSwOnline.lesPartieJoueurs.get(3);

        System.out.println(facadeSwOnline.partie.getPartieJoueurs());

        partieJoueur1.deplacementCarteChoisidDeTempVersConstructCite("carte2");
        System.out.println(partieJoueur1.getJoueur() + " va faire une choix d'une carte " + partieJoueur1.getCartesConstructionCite().get(0));
        System.out.println(facadeSwOnline.partie.authorisationCarteCirculant());
        facadeSwOnline.partie.rotationCarte();
        System.out.println("-----------------------------------------------------------");

        partieJoueur2.deplacementCarteChoisidDeTempVersConstructCite("carte12");
        System.out.println(partieJoueur2.getJoueur() + " va faire une choix d'une carte " + partieJoueur2.getCartesConstructionCite().get(0));
        System.out.println(facadeSwOnline.partie.authorisationCarteCirculant());
        facadeSwOnline.partie.rotationCarte();
        System.out.println("-----------------------------------------------------------");

        partieJoueur3.deplacementCarteChoisidDeTempVersConstructCite("carte18");
        System.out.println(partieJoueur3.getJoueur() + " va faire une choix d'une carte " + partieJoueur3.getCartesConstructionCite().get(0));
        System.out.println(facadeSwOnline.partie.authorisationCarteCirculant());
        facadeSwOnline.partie.rotationCarte();
        System.out.println("-----------------------------------------------------------");

        partieJoueur4.deplacementCarteChoisidDeTempVersConstructCite("carte27");
        System.out.println(partieJoueur4.getJoueur() + " va faire une choix d'une carte " + partieJoueur4.getCartesConstructionCite().get(0));
        System.out.println(facadeSwOnline.partie.authorisationCarteCirculant());
        facadeSwOnline.partie.rotationCarte();
        System.out.println("-----------------------------------------------------------");

        System.out.println(facadeSwOnline.partie.getPartieJoueurs());
    }
}
