package facade;

import interfaces.ICarte;
import modele.Carte;
import packageDTOs.Effectif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LesJeuCartes {
    public static final List<ICarte> lesCarteAgeI4 = loadData();
    public static final List<ICarte> lesCarteAgeI3 = loadData().stream().filter(carte -> carte.getEffectif().equals(Effectif.TROIS_PLUS)).collect(Collectors.toList());

    public static final List<ICarte> lesCarteAgeII4 = loadDataII();
    public static final List<ICarte> lesCarteAgeII3 = loadDataII().stream().filter(carte -> carte.getEffectif().equals(Effectif.TROIS_PLUS)).collect(Collectors.toList());

    public static final List<ICarte> lesCarteAgeIII4 = loadDataIII();

    public static final List<ICarte> lesCarteAgeIII3 = loadDataIII().stream().filter(carte -> carte.getEffectif().equals(Effectif.TROIS_PLUS)).collect(Collectors.toList());
    public static List<ICarte> loadData(){
        List<ICarte> cartes = new ArrayList<>();
        cartes.add(new Carte("carte1","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte2","rouge",4,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte3","rouge",3,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte4","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte5","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte6","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte7","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carte8","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte9","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte10","rouge",10,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte11","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte12","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte13","rouge",7,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte14","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carte15","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte16","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte17","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte18","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte19","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte20","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carte21","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carte22","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte23","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte24","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte25","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte26","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte27","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carte28","rouge",5,Effectif.QUATRE_PLUS));
        //Collections.shuffle(cartes);
        return cartes;

    }

    public static List<ICarte> loadDataII(){
        List<ICarte> cartes = new ArrayList<>();
        cartes.add(new Carte("carteII_1","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_2","rouge",4,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_3","rouge",3,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_4","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_5","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_6","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_7","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteII_8","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_9","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_10","rouge",10,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_11","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_12","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_13","rouge",7,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_14","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteII_15","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_16","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_17","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_18","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_19","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_20","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteII_21","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteII_22","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_23","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_24","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_25","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_26","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_27","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteII_28","rouge",5,Effectif.QUATRE_PLUS));
        //Collections.shuffle(cartes);
        return cartes;
    }

    public static List<ICarte> loadDataIII(){
        List<ICarte> cartes = new ArrayList<>();
        cartes.add(new Carte("carteIII_1","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_2","rouge",4,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_3","rouge",3,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_4","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_5","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_6","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_7","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteIII_8","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_9","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_10","rouge",10,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_11","rouge",8,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_12","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_13","rouge",7,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_14","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteIII_15","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_16","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_17","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_18","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_19","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_20","rouge",5,Effectif.TROIS_PLUS));
        cartes.add(new Carte("carteIII_21","rouge",5,Effectif.TROIS_PLUS));

        cartes.add(new Carte("carteIII_22","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_23","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_24","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_25","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_26","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_27","rouge",5,Effectif.QUATRE_PLUS));
        cartes.add(new Carte("carteIII_28","rouge",5,Effectif.QUATRE_PLUS));
        //Collections.shuffle(cartes);
        return cartes;
    }

    public static List<ICarte> distributionAGE_I(int index, int nbJoueur) {
        if (nbJoueur == 4) {
            switch (index) {
                case 0:
                    return lesCarteAgeI4.subList(0, 7);
                case 1:
                    return lesCarteAgeI4.subList(7, 14);
                case 2:
                    return lesCarteAgeI4.subList(14, 21);
                case 3:
                    return lesCarteAgeI4.subList(21, 28);
                default:
                    return null;
            }
        } else {
            switch (index) {
                case 0:
                    return lesCarteAgeI3.subList(0, 7);
                case 1:
                    return lesCarteAgeI3.subList(7, 14);
                case 2:
                    return lesCarteAgeI3.subList(14, 21);
                default:
                    return null;
            }

        }

    }

    public static List<ICarte> distributionAGE_II(int index, int nbJoueur) {
        if (nbJoueur == 4) {
            switch (index) {
                case 0:
                    return lesCarteAgeII4.subList(0, 7);
                case 1:
                    return lesCarteAgeII4.subList(7, 14);
                case 2:
                    return lesCarteAgeII4.subList(14, 21);
                case 3:
                    return lesCarteAgeII4.subList(21, 28);
                default:
                    return null;
            }
        } else {
            switch (index) {
                case 0:
                    return lesCarteAgeII3.subList(0, 7);
                case 1:
                    return lesCarteAgeII3.subList(7, 14);
                case 2:
                    return lesCarteAgeII3.subList(14, 21);
                default:
                    return null;
            }

        }

    }

    public static List<ICarte> distributionAGE_III(int index, int nbJoueur) {
        if (nbJoueur == 4) {
            switch (index) {
                case 0:
                    return lesCarteAgeIII4.subList(0, 7);
                case 1:
                    return lesCarteAgeIII4.subList(7, 14);
                case 2:
                    return lesCarteAgeIII4.subList(14, 21);
                case 3:
                    return lesCarteAgeIII4.subList(21, 28);
                default:
                    return null;
            }
        }
        else {

            switch (index) {
                case 0:
                    return lesCarteAgeIII3.subList(0, 7);
                case 1:
                    return lesCarteAgeIII3.subList(7, 14);
                case 2:
                    return lesCarteAgeIII3.subList(14, 21);
                default:
                    return null;
            }

        }

    }
}
