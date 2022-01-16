package modele;

import effet.Effet;
import packageDTOs.TypeEffet;
import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private TypePlateau nomPlateau;
    private Effet effetParDefaut;
    private String imagePlateau;

    //ETAPES MERVEILLES
    private int etapesMerveilleConstruite; //TEMP
    private List<Effet> lesCoutsEtape1;
    private List<Effet> lesRessourcesEtape1;
    private List<Effet> lesCoutsEtape2;
    private List<Effet> lesRessourcesEtape2;
    private List<Effet> lesCoutsEtape3;
    private List<Effet> lesRessourcesEtape3;


    public Plateau() {
        this.lesCoutsEtape1 = new ArrayList<>();
        this.lesRessourcesEtape1 = new ArrayList<>();
        this.lesCoutsEtape2 = new ArrayList<>();
        this.lesRessourcesEtape2 = new ArrayList<>();
        this.lesCoutsEtape3 = new ArrayList<>();
        this.lesRessourcesEtape3 =new ArrayList<>();
    }

    public Plateau(TypePlateau nomPlateau) {
        this.nomPlateau = nomPlateau;
        this.etapesMerveilleConstruite = 0;
        this.lesCoutsEtape1 = new ArrayList<>();
        this.lesRessourcesEtape1 = new ArrayList<>();
        this.lesCoutsEtape2 = new ArrayList<>();
        this.lesRessourcesEtape2 = new ArrayList<>();
        this.lesCoutsEtape3 = new ArrayList<>();
        this.lesRessourcesEtape3 =new ArrayList<>();
    }

    public Plateau initPlateau() {

        switch (this.nomPlateau) {
            case BABYLON:
                this.effetParDefaut = new Effet(TypeEffet.ARGILE,1);
                this.lesCoutsEtape1.add(new Effet(TypeEffet.ARGILE,2));
                this.lesRessourcesEtape1.add(new Effet(TypeEffet.VICTOIRE,3));

                this.lesCoutsEtape2.add(new Effet(TypeEffet.BOIS,3));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.ROUE,1));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.COMPAS,1));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.TABLETTE,1));

                this.lesCoutsEtape3.add(new Effet(TypeEffet.ARGILE,4));
                this.lesRessourcesEtape3.add(new Effet(TypeEffet.VICTOIRE,7));
                break;

            case ALEXANDRIA:
                this.effetParDefaut = new Effet(TypeEffet.VERRE,1);
                this.lesCoutsEtape1.add(new Effet(TypeEffet.PIERRE,2));
                this.lesRessourcesEtape1.add(new Effet(TypeEffet.VICTOIRE,3));

                this.lesCoutsEtape2.add(new Effet(TypeEffet.MINERAI,2));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.ARGILE,1));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.MINERAI,1));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.BOIS,1));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.PIERRE,1));

                this.lesCoutsEtape3.add(new Effet(TypeEffet.VERRE,2));
                this.lesRessourcesEtape3.add(new Effet(TypeEffet.VICTOIRE,7));
                break;

            case RHODOS:
                this.effetParDefaut = new Effet(TypeEffet.MINERAI,1);
                this.lesCoutsEtape1.add(new Effet(TypeEffet.BOIS,2));
                this.lesRessourcesEtape1.add(new Effet(TypeEffet.VICTOIRE,3));

                this.lesCoutsEtape2.add(new Effet(TypeEffet.ARGILE,3));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.BOUCLIER,2));

                this.lesCoutsEtape3.add(new Effet(TypeEffet.MINERAI,4));
                this.lesRessourcesEtape3.add(new Effet(TypeEffet.VICTOIRE,7));
                break;

            case EPHOSOS:

                this.effetParDefaut = new Effet(TypeEffet.PAPIER,1);
                this.lesCoutsEtape1.add(new Effet(TypeEffet.PIERRE,2));
                this.lesRessourcesEtape1.add(new Effet(TypeEffet.VICTOIRE,3));

                this.lesCoutsEtape2.add(new Effet(TypeEffet.BOIS,2));
                this.lesRessourcesEtape2.add(new Effet(TypeEffet.PIECE,9));

                this.lesCoutsEtape3.add(new Effet(TypeEffet.PAPIER,2));
                this.lesRessourcesEtape3.add(new Effet(TypeEffet.VICTOIRE,7));
                break;
        }

        return this;
    }

    public TypePlateau getNomPlateau() {
        return nomPlateau;
    }

    public void setNomPlateau(TypePlateau nomPlateau) {
        this.nomPlateau = nomPlateau;
    }

    public Effet getEffetParDefaut() {
        return effetParDefaut;
    }

    public void setEffetParDefaut(Effet effetParDefaut) {
        this.effetParDefaut = effetParDefaut;
    }

    public String getImagePlateau() {
        return imagePlateau;
    }

    public void setImagePlateau(String imagePlateau) {
        this.imagePlateau = imagePlateau;
    }

    public int getEtapesMerveilleConstruite() {
        return etapesMerveilleConstruite;
    }

    public void setEtapesMerveilleConstruite(int etapesMerveilleConstruite) {
        this.etapesMerveilleConstruite = etapesMerveilleConstruite;
    }

    public List<Effet> getLesCoutsEtape1() {
        return lesCoutsEtape1;
    }

    public void setLesCoutsEtape1(List<Effet> lesCoutsEtape1) {
        this.lesCoutsEtape1 = lesCoutsEtape1;
    }

    public List<Effet> getLesRessourcesEtape1() {
        return lesRessourcesEtape1;
    }

    public void setLesRessourcesEtape1(List<Effet> lesRessourcesEtape1) {
        this.lesRessourcesEtape1 = lesRessourcesEtape1;
    }

    public List<Effet> getLesCoutsEtape2() {
        return lesCoutsEtape2;
    }

    public void setLesCoutsEtape2(List<Effet> lesCoutsEtape2) {
        this.lesCoutsEtape2 = lesCoutsEtape2;
    }

    public List<Effet> getLesRessourcesEtape2() {
        return lesRessourcesEtape2;
    }

    public void setLesRessourcesEtape2(List<Effet> lesRessourcesEtape2) {
        this.lesRessourcesEtape2 = lesRessourcesEtape2;
    }

    public List<Effet> getLesCoutsEtape3() {
        return lesCoutsEtape3;
    }

    public void setLesCoutsEtape3(List<Effet> lesCoutsEtape3) {
        this.lesCoutsEtape3 = lesCoutsEtape3;
    }

    public List<Effet> getLesRessourcesEtape3() {
        return lesRessourcesEtape3;
    }

    public void setLesRessourcesEtape3(List<Effet> lesRessourcesEtape3) {
        this.lesRessourcesEtape3 = lesRessourcesEtape3;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "nomPlateau='" + nomPlateau + '\'' +
                ", effetParDefaut=" + effetParDefaut +
                ", imagePlateau='" + imagePlateau + '\'' +
                ", etapesMerveilleConstruite=" + etapesMerveilleConstruite +
                ", lesCoutsEtape1=" + lesCoutsEtape1 +
                ", lesRessourcesEtape1=" + lesRessourcesEtape1 +
                ", lesCoutsEtape2=" + lesCoutsEtape2 +
                ", lesRessourcesEtape2=" + lesRessourcesEtape2 +
                ", lesCoutsEtape3=" + lesCoutsEtape3 +
                ", lesRessourcesEtape3=" + lesRessourcesEtape3 +
                '}';
    }

}
