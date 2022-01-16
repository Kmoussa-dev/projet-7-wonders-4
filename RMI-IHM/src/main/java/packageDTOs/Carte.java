package packageDTOs;

import effet.Effet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carte implements Serializable {
    private String id;
    private String nom;
    private Effectif effectif;
    private List<Effet> lesCouts;

    private List<Effet> lesRessources;

    public Carte(){
        this.lesCouts = new ArrayList<>();
        this.lesRessources = new ArrayList<>();
    }

    public Carte(String _id, String nom, Effectif effectif, List<Effet> lesRessources, List<Effet> lesCouts) {
        this.id = _id;
        this.nom = nom;
        this.effectif = effectif;
        this.lesCouts = lesCouts;
        this.lesRessources = lesRessources;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Effectif getEffectif() {
        return effectif;
    }

    public void setEffectif(Effectif effectif) {
        this.effectif = effectif;
    }

    public List<Effet> getLesCouts() {
        return lesCouts;
    }

    public void setLesCouts(List<Effet> lesCouts) {
        this.lesCouts = lesCouts;
    }

    public List<Effet> getLesRessources() {
        return lesRessources;
    }

    public void setLesRessources(List<Effet> lesRessources) {
        this.lesRessources = lesRessources;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Carte){
            return  this.id.equals(((Carte)obj).getId());
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Carte " + nom + " - Couts : " + lesCouts + " - Ressources : " + lesRessources ;
    }
}
