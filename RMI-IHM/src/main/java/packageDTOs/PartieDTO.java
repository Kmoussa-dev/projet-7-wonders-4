package packageDTOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartieDTO implements Serializable {
    private String id;
    private List<String> lesJoueurs;

    public PartieDTO(){}

    public PartieDTO(String id) {
        this.id = id;
        this.lesJoueurs = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "PartieDTO{" +
                "id='" + id + '\'' +
                ", lesJoueurs=" + lesJoueurs +
                '}';
    }
}
