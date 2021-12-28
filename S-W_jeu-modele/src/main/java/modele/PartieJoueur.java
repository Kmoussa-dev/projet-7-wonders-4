package modele;

import modele.interfaces.ICarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartieJoueur {


    private String joueur;
    private String plateau;

    private List<ICarte> cartesCirculantes;
    private List<ICarte> cartesConstructionCite;
    private List<ICarte> cartesConstructionMerveille;
    private EtatCarteChoisi etatChoisi;

    /*-------------------------------- Constructeur - getters - setters ----------------------------- */

    public PartieJoueur(String joueur, String plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
        this.cartesCirculantes = new ArrayList<>();
        this.cartesConstructionCite = new ArrayList<>();
        this.cartesConstructionMerveille = new ArrayList<>();
    }



    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public String getPlateau() {
        return plateau;
    }

    public void setPlateau(String plateau) {
        this.plateau = plateau;
    }

    public List<ICarte> getCartesCirculantes() {
        return cartesCirculantes;
    }

    public void setCartesCirculantes(List<ICarte> cartesCirculantes) {
        this.cartesCirculantes = cartesCirculantes;
    }

    public List<ICarte> getCartesConstructionCite() {
        return cartesConstructionCite;
    }

    public void setCartesConstructionCite(List<ICarte> cartesConstructionCite) {
        this.cartesConstructionCite = cartesConstructionCite;
    }

    public List<ICarte> getCartesConstructionMerveille() {
        return cartesConstructionMerveille;
    }

    public void setCartesConstructionMerveille(List<ICarte> cartesConstructionMerveille) {
        this.cartesConstructionMerveille = cartesConstructionMerveille;
    }

    public EtatCarteChoisi getEtatChoisi() {
        return etatChoisi;
    }

    public void setEtatChoisi(EtatCarteChoisi etatChoisi) {
        this.etatChoisi = etatChoisi;
    }

    public void deplacementCarteChoisidDeTempVersConstructCite(String nomCarte){
        List<ICarte> carte = this.cartesCirculantes.stream().filter(c -> c.getNom().equals(nomCarte)).collect(Collectors.toList());

        this.cartesCirculantes.remove(carte.get(0));
        this.cartesConstructionCite.add(carte.get(0));
        this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
    }

    @Override
    public String toString() {
        return "PartieJoueur{" +
                "joueur='" + joueur + '\'' +
                ", plateau='" + plateau + '\'' +
                ", cartesCirculantes=" + cartesCirculantes +
                ", cartesConstructionCite=" + cartesConstructionCite +
                ", cartesConstructionMerveille=" + cartesConstructionMerveille +
                ", etatChoisi=" + etatChoisi +
                '}';
    }
}
