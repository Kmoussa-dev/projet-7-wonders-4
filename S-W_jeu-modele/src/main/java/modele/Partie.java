package modele;

import modele.interfaces.ICarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Partie {

    private String idPartie;
    private String etatPartie;
    private List <PartieJoueur> partieJoueurs ;


    public Partie() {
        this.partieJoueurs = new ArrayList<>();
    }

    public String getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(String idPartie) {
        this.idPartie = idPartie;
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

    public void setPartieJoueurs(List<PartieJoueur> joueurs) {
        this.partieJoueurs = joueurs;
    }


    public void autoriserCartesACirculer() {

        //compteur des joueurs
        int cpt = 0;
        int nbJoueurPartie = partieJoueurs.size();

        for(PartieJoueur pj : partieJoueurs)
        {
            if(pj.getEtatChoisi() == EtatCarteChoisi.DEJA_CHOISIE)
            {
                cpt++;
            }
        }

        if(cpt == nbJoueurPartie)
        {
            Collection<ICarte> cartesTemp = getPartieJoueurs().get(0).getCartesCirculantes();

            for(int i = 0; i < partieJoueurs.size() - 1; i++)
            {
                partieJoueurs.get(i).getCartesCirculantes() = partieJoueurs.get(i+1).getCartesCirculantes();
            }

            partieJoueurs.get(partieJoueurs.size() - 1).getCartesCirculantes() = cartesTemp;

            for(PartieJoueur pj : partieJoueurs)
            {
                pj.setEtatChoisi(EtatCarteChoisi.PAS_ENCORE_CHOISIE);
            }
        }
    }
}
