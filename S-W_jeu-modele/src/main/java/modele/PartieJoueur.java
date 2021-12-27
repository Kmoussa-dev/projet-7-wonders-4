package modele;

import modele.interfaces.ICarte;
import java.util.Collection;

public class PartieJoueur {

    private Joueur joueur;
    private Plateau plateau;

    private Collection<ICarte> cartesCirculantes;
    private Collection<ICarte> cartesConstructionCite;
    private Collection<ICarte> cartesConstructionMerveille;
    private EtatCarteChoisi etatChoisi;

    /*-------------------------------- Constructeur - getters - setters ----------------------------- */

    public PartieJoueur() {}

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Collection<ICarte> getCartesCirculantes() {
        return cartesCirculantes;
    }

    public void setCartesCirculantes(Collection<ICarte> cartesCirculantes) {
        this.cartesCirculantes = cartesCirculantes;
    }

    public Collection<ICarte> getCartesConstructionCite() {
        return cartesConstructionCite;
    }

    public void setCartesConstructionCite(Collection<ICarte> cartesConstructionCite) {
        this.cartesConstructionCite = cartesConstructionCite;
    }

    public Collection<ICarte> getCartesConstructionMerveille() {
        return cartesConstructionMerveille;
    }

    public void setCartesConstructionMerveille(Collection<ICarte> cartesConstructionMerveille) {
        this.cartesConstructionMerveille = cartesConstructionMerveille;
    }

    public EtatCarteChoisi getEtatChoisi() {
        return etatChoisi;
    }

    public void setEtatChoisi(EtatCarteChoisi etatChoisi) {
        this.etatChoisi = etatChoisi;
    }
}
