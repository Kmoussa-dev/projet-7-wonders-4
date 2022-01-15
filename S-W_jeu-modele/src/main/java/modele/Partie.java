package modele;

import exceptions.CarteDejaException;
import exceptions.CarteInexistantException;
import exceptions.PartieSuspenduOuTermine;
import exceptions.partiePleinExecption;
import interfaces.ICarte;
import org.bson.codecs.pojo.annotations.BsonProperty;

import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Partie {
    //private  static  Partie partie = new Partie();
    @BsonProperty("_id")
    private String id;
    private EtatPartie etatPartie;
    private List <PartieJoueur> partieJoueurs ;

    public Partie(){
        this.partieJoueurs = new ArrayList<>();
    }


    public Partie(String id){
        this.id = id;
        this.partieJoueurs = new ArrayList<>();
        this.etatPartie = EtatPartie.DEBUT;

    }

    //public static Partie creer(){
      //  return partie;
    //}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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




    public PartieJoueur getPartieJoueurByPseudo(String pseudo){
        return this.partieJoueurs.stream().filter(partieJoueur -> partieJoueur.getJoueur().equals(pseudo)).collect(Collectors.toList()).get(0);
    }

    public void deplacer(String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieSuspenduOuTermine {
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
        System.out.println(i == this.partieJoueurs.size());
        return i != this.partieJoueurs.size();

    }

    public void ajouterPartieJoueur(PartieJoueur partieJoueur) throws partiePleinExecption {
        if(this.partieJoueurs.size() < 4){
            this.partieJoueurs.add(partieJoueur);
        }


    }


    public void notifierALaPartiJoueur() {
        if(this.partieJoueurs.size()  == (this.partieJoueurs.stream().filter(pj-> pj.getCartesCirculantes().size() == 0 && pj.getAge() == 3).count())){
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
                "_id='" + id + '\'' +
                ", etatPartie='" + etatPartie + '\'' +
                ", partieJoueurs=" + partieJoueurs +
                '}';
    }
}