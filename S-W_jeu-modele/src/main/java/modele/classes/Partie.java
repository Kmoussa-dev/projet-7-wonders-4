package modele.classes;

import java.util.*;

/**
 * 
 */
public class Partie {

    /**
     * Default constructor
     */
    public Partie() {
    }

    /**
     * 
     */
    public int idPartie;

    /**
     * 
     */
    public List<Joueur> joueurs;

    /**
     * 
     */
    public Map<Age,List<Carte>> ages;

    /**
     * 
     */
    public Joueur vainqueur;

    /**
     * 
     */
    public boolean etat;


}