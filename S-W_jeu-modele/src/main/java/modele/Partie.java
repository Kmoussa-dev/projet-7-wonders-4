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
    public void rotationCarte(){
        if(this.authorisationCarteCirculant()){
            List<ICarte> carteTemp = this.partieJoueurs.get(0).getCartesCirculantes();

            for (int i = 0; i < this.partieJoueurs.size() -1; i++){
                this.partieJoueurs.get(i).setCartesCirculantes(this.partieJoueurs.get(i+1).getCartesCirculantes());
            }
            this.partieJoueurs.get(this.partieJoueurs.size() -1).setCartesCirculantes(carteTemp);
            for(PartieJoueur pj  : this.partieJoueurs){
                pj.setEtatChoisi(EtatCarteChoisi.PAS_ENCORE_CHOISIE);
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
