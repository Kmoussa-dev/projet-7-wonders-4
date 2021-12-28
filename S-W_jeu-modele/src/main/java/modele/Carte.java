package modele;

import modele.interfaces.ICarte;

public class Carte implements ICarte {
    private String nom;
    private String couleur;
    private double valeur;

    public Carte() {}

    public Carte(String nom, String couleur, double valeur) {
        this.nom = nom;
        this.couleur = couleur;
        this.valeur = valeur;
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

    public String toString() {
        return "Carte{" +
                "nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
