package packageDTOs;

import java.io.Serializable;

public class RessourcesDTO implements Serializable {

    private String ressource;
    private int quantite;

    public RessourcesDTO(String ressource, int quantite) {
        this.ressource = ressource;
        this.quantite = quantite;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


}
