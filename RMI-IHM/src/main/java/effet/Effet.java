package effet;

import interfaces.IEffet;
import packageDTOs.TypeEffet;

import java.io.Serializable;

public class Effet implements Serializable {
    private TypeEffet effect;

    private int valeur;

    public Effet(){}

    public Effet(TypeEffet effect, int valeur) {
        this.effect = effect;
        this.valeur = valeur;
    }

    public void setTypeEffet(TypeEffet effect){
        this.effect = effect;
    }

    public void setValeur(int valeur){
        this.valeur = valeur;
    }


    public TypeEffet getTypeEffet() {
        return this.effect;
    }


    public int getValeur() {
        return this.valeur;
    }

    @Override
    public String toString() {
        return "Effet{" +
                "effect=" + effect +
                ", valeur=" + valeur +
                '}';
    }
}
