package modele;

public class Joueur {

    private String pseudo;
    private String mdp;

    //constructeur
    public Joueur() {}

    //Getters et setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
