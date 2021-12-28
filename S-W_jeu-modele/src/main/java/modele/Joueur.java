package modele;

public class Joueur {

    private String _id;
    private String mdp;

    //constructeur
    public Joueur() {}

    public Joueur(String _id, String mdp){
        this._id = _id;
        this.mdp = mdp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
