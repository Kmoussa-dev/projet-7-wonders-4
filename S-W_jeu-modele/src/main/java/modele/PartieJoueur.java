package modele;

import interfaces.ICarte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PartieJoueur {


    private String joueur;
    private String plateau;

    private List<ICarte> cartesCirculantes;
    private List<ICarte> cartesConstructionCite;
    private List<ICarte> cartesConstructionMerveille;
    private EtatCarteChoisi etatChoisi;
    private boolean consulterDernierJoueur;

    /*-------------------------------- Constructeur - getters - setters ----------------------------- */

    public PartieJoueur(String joueur, String plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.consulterDernierJoueur = false;
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

    public boolean isConsulterDernierJoueur() {
        return this.consulterDernierJoueur;
    }

    public void setConsulterDernierJoueur(boolean consulterDernierJoueur) {
        this.consulterDernierJoueur = consulterDernierJoueur;
    }

    public List<ICarte> getCartesCirculantes() {
        return cartesCirculantes;
    }

    public void setCartesCirculantes(List<ICarte> cartesCirculantes) {
        this.cartesCirculantes = cartesCirculantes;
    }


    public List<ICarte> getCartesConstructionCite() {
        return this.cartesConstructionCite;
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



    public void ajouterCarteCirculant(ICarte carte){
        if(this.cartesCirculantes.stream().filter(c -> c.getNom().equals(carte.getNom())).collect(Collectors.toList()).size() == 0)
        {
            this.cartesCirculantes.add(carte);
        }

    }

    public void deplacementCarteChoisidDeTempVersConstructCite(List<ICarte> majCarteCirculant, ICarte choixCarte){
        if(this.etatChoisi == EtatCarteChoisi.PAS_ENCORE_CHOISIE){
            this.cartesConstructionCite.add(choixCarte);
            this.cartesCirculantes = majCarteCirculant;
            this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
        }
        else {
            throw new RuntimeException();
        }

    }


    public void updateCarteTemp(Partie partie){
        if(this.etatChoisi == EtatCarteChoisi.DEJA_CHOISIE){
            try {
                PartieJoueur partieJoueurCote = partie.getPartieJoueurs().get((partie.getPartieJoueurs().indexOf(this) + 1));
                List<ICarte> cartesTemp = this.cartesCirculantes;
                this.cartesCirculantes = partieJoueurCote.getCartesCirculantes();
                partieJoueurCote.setCartesCirculantes(cartesTemp);
                cartesTemp = null;
                this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
            }
            catch (Exception e)
            {
                this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
                e.printStackTrace();
            }
        }

    }


    public boolean passerLesCartes(){
        return this.etatChoisi == EtatCarteChoisi.DEJA_CHOISIE;
    }

    private void rotationInverse(Partie partie){

    }

    private void rotation(Partie partie){
        try {
            PartieJoueur partieJoueurCote = partie.getPartieJoueurs().get((partie.getPartieJoueurs().indexOf(this) - 1));
            List<ICarte> cartesTemp = this.cartesCirculantes;
            this.cartesCirculantes = partieJoueurCote.getCartesCirculantes();
            partieJoueurCote.setCartesCirculantes(cartesTemp);
            cartesTemp = null;
            this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
        }
        catch (Exception e)
        {
            PartieJoueur partieJoueurCote = partie.getPartieJoueurs().get(partie.getPartieJoueurs().size() -1);
            List<ICarte> cartesTemp = this.cartesCirculantes;
            this.cartesCirculantes = partieJoueurCote.getCartesCirculantes();
            partieJoueurCote.setCartesCirculantes(cartesTemp);
            cartesTemp = null;
            this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
        }
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
