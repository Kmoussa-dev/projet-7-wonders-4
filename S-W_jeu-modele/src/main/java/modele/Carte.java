package modele;

import interfaces.ICarte;
import interfaces.IEffet;
import packageDTOs.Effectif;

import java.util.List;

public class Carte implements ICarte {
    private String _id;
    private String nom;
    private Effectif effectif;
    private List<IEffet> lesCouts;
    private List<IEffet> lesRessources;


    public Carte(String _id, String nom,Effectif effectif, List<IEffet> lesRessources, List<IEffet> lesCouts) {
        this._id = _id;
        this.nom = nom;
        this.effectif = effectif;
        this.lesCouts = lesCouts;
        this.lesRessources = lesRessources;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<IEffet> getLesCouts() {
        return lesCouts;
    }

    public void setLesCouts(List<IEffet> lesCouts) {
        this.lesCouts = lesCouts;
    }

    public List<IEffet> getLesRessources() {
        return lesRessources;
    }

    public void setLesRessources(List<IEffet> lesRessources) {
        this.lesRessources = lesRessources;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ICarte){
            return  this._id.equals(((ICarte)obj).get_id());
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Carte{" +
                "_id='" + _id + '\'' +
                ", nom='" + nom + '\'' +
                ", effectif=" + effectif +
                ", lesCouts=" + lesCouts +
                ", lesRessources=" + lesRessources +
                '}';
    }
}
