package modele;

import interfaces.ICarte;
import packageDTOs.Effectif;

public class Carte implements ICarte {

    private String nom;
    private String couleur;
    private double valeur;
    private Effectif effectif;


    public Carte() {}

    public Carte(String nom, String couleur, double valeur, Effectif effectif) {
        this.nom = nom;
        this.couleur = couleur;
        this.valeur = valeur;
        this.effectif = effectif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    @Override
    public Effectif getEffectif() {
        return effectif;
    }

    @Override
    public void setEffectif(Effectif effectif) {
        this.effectif = effectif;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ICarte)
        {
            return this.nom.equals(((ICarte) obj).getNom());
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Carte{" +
                "nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", valeur=" + valeur +
                ", effectif=" + effectif +
                '}';
    }
}
