package modele.classes;

import java.util.*;

/**
 * 
 */
public abstract class Carte implements ICarte {

    /**
     * Default constructor
     */
    public Carte() {
    }

    /**
     * 
     */
    public String nomCarte;

    /**
     * 
     */
    public FrequenceCarte frequence;

    /**
     * 
     */
    public List<IEffet> lesEffets;

    /**
     * 
     */
    public List<IEffet> lesCouts;

    /**
     * 
     */
    public Age ageCarte;




}