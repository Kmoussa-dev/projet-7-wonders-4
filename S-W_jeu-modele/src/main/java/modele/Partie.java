package modele;

import exceptions.partiTermineException;
import interfaces.ICarte;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Partie {
    private  static  Partie partie = new Partie();
    private String _id;
    private EtatPartie etatPartie;
    private int nbJoueur;
    private List <PartieJoueur> partieJoueurs ;

    public Partie(){
        this.partieJoueurs = new ArrayList<>();
    }


    public Partie(String _id, int nbJoueur){
        super();
        this.etatPartie = EtatPartie.DEBUT;
    }

    public static Partie creer(){
        return partie;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public EtatPartie getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(EtatPartie etatPartie) {
        this.etatPartie = etatPartie;
    }

    public List<PartieJoueur> getPartieJoueurs() {
        return partieJoueurs;
    }

    public void setPartieJoueurs(List<PartieJoueur> partieJoueurs) {
        this.partieJoueurs = partieJoueurs;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public PartieJoueur getPartieJoueurByPseudo(String pseudo){
        return this.partieJoueurs.stream().filter(partieJoueur -> partieJoueur.getJoueur().equals(pseudo)).collect(Collectors.toList()).get(0);
    }

    public void deplacer(String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement){
        for (PartieJoueur partieJoueur : this.partieJoueurs){
            if(partieJoueur.getJoueur().equals(pseudo)){
                partieJoueur.deplacerLaCarteChoisi(cartes,carte,modeDeplacement, this);
            }
        }
    }

    public boolean authorisationCarteCirculant() {
        int i = 0;
        for (PartieJoueur pj  : this.partieJoueurs){
            if(pj.getEtatChoisi().equals(EtatCarteChoisi.DEJA_CHOISIE)){
                i ++;
            }
        }
        return i != this.nbJoueur;

    }

    public void ajouterPartieJoueur(PartieJoueur partieJoueur){
        this.partieJoueurs.add(partieJoueur);
        this.nbJoueur ++;

    }



    public void supprimerPartieJoueur(PartieJoueur partieJoueur){
        this.partieJoueurs.remove(partieJoueur);
    }

    public void notifierALaPartiJoueur() throws partiTermineException {
        if(this.nbJoueur  == (this.partieJoueurs.stream().filter(pj-> pj.getCartesCirculantes().size() == 0 && pj.getAge() == 3).count())){
            this.etatPartie = EtatPartie.TERMINE;
        }
    for (PartieJoueur partieJoueur : this.partieJoueurs){
        partieJoueur.updateCarteTemp(this);
}


    }

    public boolean partieCommence(){
        return this.partieJoueurs.size() == 4;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "_id='" + _id + '\'' +
                ", etatPartie='" + etatPartie + '\'' +
                ", partieJoueurs=" + partieJoueurs +
                '}';
    }
} //this.cartesCirculantes.remove(choixCarte);
 //this.cartesCirculantes.remove(choixCarte);