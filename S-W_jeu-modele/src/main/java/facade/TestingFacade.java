package facade;

import interfaces.ICarte;
import modele.Carte;
import modele.Partie;
import modele.PartieJoueur;

import java.util.ArrayList;
import java.util.List;

public class TestingFacade {
    public static void main(String[] args) {
        FacadeSwOnline facadeSwOnline = new FacadeSwOnline();

        //facadeSwOnline.loadData();

        facadeSwOnline.accederUnePartie("a","Egypte");
        //facadeSwOnline.distribution("Alice");
        System.out.println("-------------------------------------------");
        System.out.println(facadeSwOnline.getLesCartesCirculants("a"));
        facadeSwOnline.accederUnePartie("b","Egypte");
        //facadeSwOnline.distribution("Bob");
        System.out.println("-------------------------------------------");
        System.out.println(facadeSwOnline.getLesCartesCirculants("a"));
        System.out.println(facadeSwOnline.getLesCartesCirculants("b"));
        facadeSwOnline.accederUnePartie("c","Egypte");
        //facadeSwOnline.distribution("Charlie");
        System.out.println("-------------------------------------------");
        System.out.println(facadeSwOnline.getLesCartesCirculants("a"));
        System.out.println(facadeSwOnline.getLesCartesCirculants("b"));
        System.out.println(facadeSwOnline.getLesCartesCirculants("c"));
        facadeSwOnline.accederUnePartie("d","Egypte");
        facadeSwOnline.distribution("a");
        facadeSwOnline.distribution("b");
        facadeSwOnline.distribution("c");
        facadeSwOnline.distribution("d");
        System.out.println("-------------------------------------------");

        for (PartieJoueur p : facadeSwOnline.getPartie().getPartieJoueurs())
        {
            System.out.println(p.getJoueur() +" " +  p.getCartesCirculantes());

        }

        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        /**
        facadeSwOnline.deplacementCarte("a",new Carte("carte2","rouge",5),null);
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("a").getJoueur() + " va faire une choix d'une carte " + facadeSwOnline.getPartie().getPartieJoueurByPseudo("a").getCartesConstructionCite());
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("a").getCartesCirculantes());
        System.out.println(facadeSwOnline.authorisationCirculer());
        System.out.println("-----------------------------------------------------------");
        facadeSwOnline.notification();
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        facadeSwOnline.deplacementCarte("b",new Carte("carte13","rouge",7),null);
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("b").getJoueur() + " va faire une choix d'une carte " + facadeSwOnline.getPartie().getPartieJoueurByPseudo("b").getCartesConstructionCite());
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("b").getCartesCirculantes());
        System.out.println(facadeSwOnline.authorisationCirculer());
        System.out.println("-----------------------------------------------------------");
        facadeSwOnline.notification();
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");

        facadeSwOnline.deplacementCarte("c",new Carte("carte18","rouge",5),null);
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("c").getJoueur() + " va faire une choix d'une carte " + facadeSwOnline.getPartie().getPartieJoueurByPseudo("c").getCartesConstructionCite());
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("c").getCartesCirculantes());
        System.out.println(facadeSwOnline.authorisationCirculer());
        System.out.println("-----------------------------------------------------------");
        facadeSwOnline.notification();
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");

        facadeSwOnline.deplacementCarte("d",new Carte("carte26","rouge",5),null);
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("d").getJoueur() + " va faire une choix d'une carte " + facadeSwOnline.getPartie().getPartieJoueurByPseudo("d").getCartesConstructionCite());
        System.out.println(facadeSwOnline.getPartie().getPartieJoueurByPseudo("d").getCartesCirculantes());
        System.out.println(facadeSwOnline.authorisationCirculer());
        facadeSwOnline.notification();
        System.out.println("-----------------------------------------------------------");
        System.out.println(facadeSwOnline.authorisationCirculer());
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");

        for (PartieJoueur p : facadeSwOnline.getPartie().getPartieJoueurs())
        {
            System.out.println(p.getJoueur() +" " +  p.getCartesCirculantes());

        }

        System.out.println(facadeSwOnline.authorisationCirculer());
**/



    }
}
