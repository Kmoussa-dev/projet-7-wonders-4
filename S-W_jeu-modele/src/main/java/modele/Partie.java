package modele;

import exceptions.*;
import org.bson.codecs.pojo.annotations.BsonProperty;

import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Partie {

    @BsonProperty("_id")
    private String id;
    private EtatPartie etatPartie;
    private List <PartieJoueur> partieJoueurs ;
    private List<Carte> cartesDefausse;

    public Partie(){}

    public Partie(String id){
        this.id = id;
        this.partieJoueurs = new ArrayList<>();
        this.cartesDefausse = new ArrayList<>();
        this.etatPartie = EtatPartie.DEBUT;
    }


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

    public void deplacer(String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieTermineException, PartieSuspenduException {
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
        return i != this.partieJoueurs.size();

    }

    public void ajouterPartieJoueur(PartieJoueur partieJoueur) throws PartiePleinExecption {
        if(this.partieJoueurs.size() < 4){
            this.partieJoueurs.add(partieJoueur);
        }
        else {
            throw new PartiePleinExecption();
        }


    }


    public void notifierALaPartiJoueur() {
        if(this.partieJoueurs.size()  == (this.partieJoueurs.stream().filter(pj-> pj.getCartesCirculantes().size() == 0 && pj.getAge() == 3).count())){
            this.etatPartie = EtatPartie.TERMINE;
        }
        for (PartieJoueur partieJoueur : this.partieJoueurs){
            partieJoueur.update(this);
        }


    }

    public boolean partieCommence(){
        return this.partieJoueurs.size() == 4;
    }

    public List<Carte> getCartesDefausse() {
        return cartesDefausse;
    }

    public void setCartesDefausse(List<Carte> cartesDefausse) {
        this.cartesDefausse = cartesDefausse;
    }


    @Override
    public String toString() {
        return "Partie{" +
                "id='" + id + '\'' +
                ", etatPartie=" + etatPartie +
                ", partieJoueurs=" + partieJoueurs +
                ", cartesDefausse=" + cartesDefausse +
                '}';
    }
}