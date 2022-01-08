package modele;

import facade.LesJeuCartes;
import interfaces.ICarte;
import packageDTOs.ModeDeplacement;

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
    private int age;


    /*-------------------------------- Constructeur - getters - setters ----------------------------- */

    public PartieJoueur(String joueur, String plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
        this.age = 1;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        if(this.cartesCirculantes.stream().filter(c -> c.getNom().equals(carte.getNom())).count() == 0)
        {
            this.cartesCirculantes.add(carte);
        }

    }

    public void deplacerLaCarteChoisi(List<ICarte> majCarteCirculant, ICarte choixCarte, ModeDeplacement modeDeplacement, Partie partie){
        /*PartieJoueur partieJoueurGauche = null;
        PartieJoueur partieJoueurDroite = null;
        try {
            partieJoueurGauche = partie.getPartieJoueurs().get((partie.getPartieJoueurs().indexOf(this) - 1));
        }
        catch (Exception e){
            partieJoueurGauche = partie.getPartieJoueurs().get(0);
        }

        try {
            partieJoueurGauche = partie.getPartieJoueurs().get((partie.getPartieJoueurs().indexOf(this) + 1));
        }
        catch (Exception e){
            partieJoueurGauche = partie.getPartieJoueurs().get(partie.getPartieJoueurs().size() -1 );
        }*/

        if(this.etatChoisi == EtatCarteChoisi.PAS_ENCORE_CHOISIE){
            if(majCarteCirculant.size() == 1){majCarteCirculant.clear();}
            if(modeDeplacement == ModeDeplacement.CONSTRUCTION_CITE){
                    this.cartesConstructionCite.add(choixCarte);
                    this.cartesCirculantes = majCarteCirculant;
                    this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
            }
            else if(modeDeplacement == ModeDeplacement.CONSTRUCTION_MERVAILLE){
                this.cartesConstructionMerveille.add(choixCarte);
                this.cartesCirculantes = majCarteCirculant;
                this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
            }
            else {

                this.cartesCirculantes = majCarteCirculant;
                this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
            }

        }
        else {
            throw new RuntimeException();
        }

    }


    public void updateCarteTemp(Partie partie){

        if(this.etatChoisi == EtatCarteChoisi.DEJA_CHOISIE){
            if(this.cartesCirculantes.size() > 0){
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
            else {
                this.age ++;
                switch (this.age){
                    case 2:
                        this.cartesCirculantes = LesJeuCartes.distributionAGE_II(partie.getPartieJoueurs().indexOf(this),partie.getPartieJoueurs().size());
                        break;
                    case 3:
                        this.cartesCirculantes = LesJeuCartes.distributionAGE_III(partie.getPartieJoueurs().indexOf(this),partie.getPartieJoueurs().size());
                        break;
                }
                this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
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
