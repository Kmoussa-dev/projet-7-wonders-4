package packageDTOs;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartieDTO implements Serializable {
    private String id;
    private LocalDate dateCreation;
    private String etatPartie;
    private List<String> lesJoueurs;

    public PartieDTO(){}

    public PartieDTO(String id, String etatPartie, LocalDate dateCreation) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.etatPartie = etatPartie;
        this.lesJoueurs = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(String etatPartie) {
        this.etatPartie = etatPartie;
    }

    public List<String> getLesJoueurs() {
        return this.lesJoueurs;
    }

    public void setLesJoueurs(List<String> lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    public void ajouterNomJoueur(String nom){
        this.lesJoueurs.add(nom);
    }

    public void supprimerNomJoueur(String nom){
        this.lesJoueurs.remove(nom);
    }

    @Override
    public String toString() {
        return "Partie n° " +  id + " - Joueurs " + lesJoueurs ;
    }
}
