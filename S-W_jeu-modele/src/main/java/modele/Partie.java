package modele;

import modele.interfaces.ICarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Partie {

    private String _id;
    private String etatPartie;
    private List <PartieJoueur> partieJoueurs ;

    public Partie(){
        this._id = "test";
        this.etatPartie = "debut";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(String etatPartie) {
        this.etatPartie = etatPartie;
    }

    public List<PartieJoueur> getPartieJoueurs() {
        return partieJoueurs;
    }

    public void setPartieJoueurs(List<PartieJoueur> partieJoueurs) {
        this.partieJoueurs = partieJoueurs;
    }

    public boolean authorisationCarteCirculant() {
        int cpt = 0;
        int nbJoueursPartie = this.partieJoueurs.size();

        for (PartieJoueur pj : this.partieJoueurs) {
            if (pj.getEtatChoisi() == EtatCarteChoisi.DEJA_CHOISIE) {
                cpt++;
            }
        }
        return cpt == nbJoueursPartie;
    }

    public void ajouterPartieJoueur(PartieJoueur partieJoueur){
        this.partieJoueurs.add(partieJoueur);
    }

    public void supprimerPartieJoueur(PartieJoueur partieJoueur){
        this.partieJoueurs.remove(partieJoueur);
    }

    public void notifierALaPartiJoueur(){
        if(this.authorisationCarteCirculant()){
            for (PartieJoueur partieJoueur : this.partieJoueurs){
                partieJoueur.updateCarteTemp(this);
            }
        }
    }

    @Override
    public String toString() {
        return "Partie{" +
                "_id='" + _id + '\'' +
                ", etatPartie='" + etatPartie + '\'' +
                ", partieJoueurs=" + partieJoueurs +
                '}';
    }
}
