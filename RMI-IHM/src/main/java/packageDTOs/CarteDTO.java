package packageDTOs;

import interfaces.ICarte;

import java.io.Serializable;

public class CarteDTO implements ICarte,  Serializable{
    private String nom;
    private String couleur;
    private double valeur;

    public CarteDTO(String nom, String couleur, double valeur) {
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

    @Override
    public String toString() {
        return "CarteDTO{" +
                "nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
