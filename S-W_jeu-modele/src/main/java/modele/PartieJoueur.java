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
            throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException, RessourcesInsuffisantesException {
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
                    if(majCarteCirculant.size() == 1){

                        //deplacer la derniere carte dans la Defausse
                        partie.ajouterUneCarteALaDefausse(majCarteCirculant.get(0));
                        //nettoyer la liste des cartes circulantes
                        majCarteCirculant.clear();
                    }
                    if(this.cartesCirculantes.contains(choixCarte)){

                        if(modeDeplacement == ModeDeplacement.CONSTRUCTION_CITE){

                           this.gestionConstructionCite(choixCarte,majCarteCirculant);

                        }
                        else if(modeDeplacement == ModeDeplacement.CONSTRUCTION_MERVAILLE){

                           this.gestionConstructionMerveille(choixCarte,majCarteCirculant);
                        }
                        else {
                            //Si mode deplacement = CONSTRUCTION DEFAUSSE
                            partie.ajouterUneCarteALaDefausse(choixCarte);
                            this.recupererPiecesCarteDefausse();
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

    private void ajouterDesRessources(Carte carte) {

        for (Effet effet: carte.getLesRessources()) {
            TypeEffet typeEffet = effet.getTypeEffet();
            int valeur = effet.getValeur();

            int count = lesRessources.containsKey(typeEffet) ? lesRessources.get(typeEffet) : 0;
            lesRessources.put(String.valueOf(typeEffet), count + valeur);
        }
    }

    private void recupererPiecesCarteDefausse() {

        int count = lesRessources.containsKey("PIECE") ? lesRessources.get("PIECE") : 0;
        lesRessources.put("PIECE", count + 3);
    }


    /**
     *  Methode qui gère les conditions d'achat des ressources pour construire la cité
     * @param choixCarte
     * @param majCarteCirculant
     * @throws RessourcesInsuffisantesException
     */

    public void gestionConstructionCite(Carte choixCarte, List<Carte> majCarteCirculant) throws RessourcesInsuffisantesException {
        //les conditions
        boolean valide = true;
        for (Effet effet : choixCarte.getLesCouts()) {

            String nomEffetCout = String.valueOf(effet.getTypeEffet());

            if (this.lesRessources.containsKey(nomEffetCout)) {

                if (effet.getValeur() > this.lesRessources.get(nomEffetCout)) {
                    valide = false;
                }
                else if (effet.getTypeEffet() == TypeEffet.PIECE) {
                    // on retire la somme dépensée pour acheter la carte
                    int count = lesRessources.containsKey("PIECE") ? lesRessources.get("PIECE") : 0;
                    lesRessources.put("PIECE", count - effet.getValeur());
                }
            } else {
                valide = false;
            }
        }

        if (valide) {
            //contraintes conformes
            this.cartesConstructionCite.add(choixCarte);
            this.cartesCirculantes = majCarteCirculant;
            this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
            this.ajouterDesRessources(choixCarte);
        }
        else {
            throw new RessourcesInsuffisantesException();
        }

    }

    /**
     *  Methode gestion des conditions de construction des étapes de la merveille
     * @param choixCarte
     * @param majCarteCirculant
     */
    private void gestionConstructionMerveille(Carte choixCarte, List<Carte> majCarteCirculant) {

        if (cartesConstructionMerveille.size() < 3) {

            //les conditions

            //contraintes conformes
            this.cartesConstructionMerveille.add(choixCarte);
            this.plateau.setEtapesMerveilleConstruite((this.plateau.getEtapesMerveilleConstruite()+1));
            this.cartesCirculantes = majCarteCirculant;
            this.etatChoisi = EtatCarteChoisi.DEJA_CHOISIE;
        }
    }



    public void ajouterLaRessourceDuPlateau(Plateau plateau) {

        TypeEffet ressourcePlateau = plateau.getEffetParDefaut().getTypeEffet();

        int count = lesRessources.containsKey(ressourcePlateau) ? lesRessources.get(ressourcePlateau) : 0;
        lesRessources.put(String.valueOf(ressourcePlateau), count + 1);
    }



    @Override
    public String toString() {
        return  joueur + " - plateau : " + plateau +
//                "- cartesCirculantes=" + cartesCirculantes +
//                "- cartesConstructionCite=" + cartesConstructionCite +
//                ", cartesConstructionMerveille=" + cartesConstructionMerveille +
//                ", age=" + age +
                ", les Ressources : " + lesRessources //+
//                '}'
                ;
    }
}
