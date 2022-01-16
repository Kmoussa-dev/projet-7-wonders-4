package modele;

import effet.Effet;
import exceptions.*;
import facade.LesJeuCartes;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;
import packageDTOs.TypeEffet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartieJoueur {


    private String joueur;
    private Plateau plateau;

    private List<Carte> cartesCirculantes;
    private List<Carte> cartesConstructionCite;
    private List<Carte> cartesConstructionMerveille;
    private EtatCarteChoisi etatChoisi;
    private int age;
    private boolean createur;
    private Map<String, Integer> lesRessources;

    /*-------------------------------- Constructeur - getters - setters ----------------------------- */

    public PartieJoueur() {

    }

    public PartieJoueur(String joueur, boolean createur) {
        this.joueur = joueur;
        this.plateau = new Plateau();
        this.age = 1;
        this.createur = createur;
        this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
        this.cartesCirculantes = new ArrayList<>();
        this.cartesConstructionCite = new ArrayList<>();
        this.cartesConstructionMerveille = new ArrayList<>();
        this.lesRessources = new HashMap<String , Integer>();
        this.lesRessources.put(String.valueOf(TypeEffet.PIECE),3); // + à ajouter Ressources du plateau
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public boolean isCreateur() {
        return createur;
    }

    public void setCreateur(boolean createur) {
        this.createur = createur;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Carte> getCartesCirculantes() {
        return cartesCirculantes;
    }

    public void setCartesCirculantes(List<Carte> cartesCirculantes) {
        this.cartesCirculantes = cartesCirculantes;
    }


    public List<Carte> getCartesConstructionCite() {
        return this.cartesConstructionCite;
    }

    public void setCartesConstructionCite(List<Carte> cartesConstructionCite) {
        this.cartesConstructionCite = cartesConstructionCite;
    }


    public List<Carte> getCartesConstructionMerveille() {
        return cartesConstructionMerveille;
    }

    public void setCartesConstructionMerveille(List<Carte> cartesConstructionMerveille) {
        this.cartesConstructionMerveille = cartesConstructionMerveille;
    }


    public EtatCarteChoisi getEtatChoisi() {
        return etatChoisi;
    }

    public void setEtatChoisi(EtatCarteChoisi etatChoisi) {
        this.etatChoisi = etatChoisi;
    }


    public Map<String, Integer> getLesRessources() {
        return lesRessources;
    }

    public void setLesRessources(Map<String, Integer> lesRessources) {
        this.lesRessources = lesRessources;
    }

    public void deplacerLaCarteChoisi(List<Carte> majCarteCirculant, Carte choixCarte, ModeDeplacement modeDeplacement, Partie partie)
            throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException {
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
        if(!partie.getEtatPartie().equals(EtatPartie.TERMINE)){
            if (!partie.getEtatPartie().equals(EtatPartie.SUSPENDU)){
                if(this.etatChoisi == EtatCarteChoisi.PAS_ENCORE_CHOISIE){
                    if(majCarteCirculant.size() == 1){majCarteCirculant.clear();}
                    if(this.cartesCirculantes.contains(choixCarte)){

                        if(modeDeplacement == ModeDeplacement.CONSTRUCTION_CITE){
                            this.cartesConstructionCite.add(choixCarte);
                            this.cartesCirculantes = majCarteCirculant;
                            this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;

                            /********* LES RESSOURCES ********/
                            var ressourcesCartesChoisie = choixCarte.getLesRessources();

                            for (Effet ie : ressourcesCartesChoisie) {
                                var typeEffet = ie.getTypeEffet();
                                var valeur = ie.getValeur();

                                int count = lesRessources.containsKey(typeEffet) ? lesRessources.get(typeEffet) : 0;
                                lesRessources.put(String.valueOf(typeEffet), count + valeur);
                            }
                            /******************************/

                        }
                        else if(modeDeplacement == ModeDeplacement.CONSTRUCTION_MERVAILLE){

                            if (cartesConstructionMerveille.size() < 3) {
                                this.cartesConstructionMerveille.add(choixCarte);
                                this.plateau.setEtapesMerveilleConstruite((this.plateau.getEtapesMerveilleConstruite()+1));
                                this.cartesCirculantes = majCarteCirculant;
                                this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
                            }
                        }
                        else {
                            //Si mode deplacement = CONSTRUCTION DEFAUSSE
                            partie.getCartesDefausse().add(choixCarte);
                            this.cartesCirculantes = majCarteCirculant;
                            this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
                        }
                    }
                    else {
                        throw new CarteInexistantException();
                    }
                }
                else
                {
                    throw new CarteDejaException();
                }
            }
            else{
                throw new PartieSuspenduException();
            }
        }
        else {
            throw new PartieTermineException();
        }


    }


    public void update(Partie partie){

        if(this.etatChoisi == EtatCarteChoisi.DEJA_CHOISIE){
            if(this.cartesCirculantes.size() > 0){
                if(this.age == 2){
                    this.rotationInverse(partie);
                }
                else {
                    this.rotation(partie);
                }
            }
            else {
                if(this.age < 3){
                    this.age ++;
                    switch (this.age){
                        case 2:
                            this.cartesCirculantes = LesJeuCartes.distributionAGE_II(partie.getPartieJoueurs().indexOf(this));
                            break;
                        case 3:
                            this.cartesCirculantes = LesJeuCartes.distributionAGE_III(partie.getPartieJoueurs().indexOf(this));
                            break;
                    }
                    this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
                }
            }

        }

    }




    private void rotationInverse(Partie partie){
        try {
            PartieJoueur partieJoueurCote = partie.getPartieJoueurs().get((partie.getPartieJoueurs().indexOf(this) + 1));
            List<Carte> cartesTemp = this.cartesCirculantes;
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

    private void rotation(Partie partie){
        if((partie.getPartieJoueurs().size() - 1) == (partie.getPartieJoueurs().indexOf(this)))
        {
            this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;
        }
        else
        {
            PartieJoueur partieJoueurCote = partie.getPartieJoueurs().get((partie.getPartieJoueurs().size() - 1));
            List<Carte> cartesTemp = this.cartesCirculantes;
            this.cartesCirculantes = partieJoueurCote.getCartesCirculantes();
            partieJoueurCote.setCartesCirculantes(cartesTemp);
            this.etatChoisi = EtatCarteChoisi.PAS_ENCORE_CHOISIE;


        }
    }

    @Override
    public String toString() {
        return "PartieJoueur{" +
                "joueur='" + joueur + '\'' +
                ", plateau=" + plateau +
                ", cartesCirculantes=" + cartesCirculantes +
                ", cartesConstructionCite=" + cartesConstructionCite +
                ", cartesConstructionMerveille=" + cartesConstructionMerveille +
                ", etatChoisi=" + etatChoisi +
                ", age=" + age +
                ", createur=" + createur +
                ", lesRessources=" + lesRessources +
                '}';
    }
}
