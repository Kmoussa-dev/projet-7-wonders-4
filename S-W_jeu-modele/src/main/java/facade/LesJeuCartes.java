package facade;

import interfaces.ICarte;
import modele.Carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LesJeuCartes {
    public static List<ICarte> loadData(){
        List<ICarte> cartes = new ArrayList<>();
        cartes.add(new Carte("carte1","rouge",5));
        cartes.add(new Carte("carte2","rouge",4));
        cartes.add(new Carte("carte3","rouge",3));
        cartes.add(new Carte("carte4","rouge",5));
        cartes.add(new Carte("carte5","rouge",5));
        cartes.add(new Carte("carte6","rouge",8));
        cartes.add(new Carte("carte7","rouge",5));

        cartes.add(new Carte("carte8","rouge",5));
        cartes.add(new Carte("carte9","rouge",5));
        cartes.add(new Carte("carte10","rouge",10));
        cartes.add(new Carte("carte11","rouge",8));
        cartes.add(new Carte("carte12","rouge",5));
        cartes.add(new Carte("carte13","rouge",7));
        cartes.add(new Carte("carte14","rouge",5));

        cartes.add(new Carte("carte15","rouge",5));
        cartes.add(new Carte("carte16","rouge",5));
        cartes.add(new Carte("carte17","rouge",5));
        cartes.add(new Carte("carte18","rouge",5));
        cartes.add(new Carte("carte19","rouge",5));
        cartes.add(new Carte("carte20","rouge",5));
        cartes.add(new Carte("carte21","rouge",5));

        cartes.add(new Carte("carte22","rouge",5));
        cartes.add(new Carte("carte23","rouge",5));
        cartes.add(new Carte("carte24","rouge",5));
        cartes.add(new Carte("carte25","rouge",5));
        cartes.add(new Carte("carte26","rouge",5));
        cartes.add(new Carte("carte27","rouge",5));
        cartes.add(new Carte("carte28","rouge",5));
        //Collections.shuffle(cartes);
        return cartes;

    }


    public static List<ICarte> loadDataII(){
        List<ICarte> cartes = new ArrayList<>();
        cartes.add(new Carte("carteII_1","rouge",5));
        cartes.add(new Carte("carteII_2","rouge",4));
        cartes.add(new Carte("carteII_3","rouge",3));
        cartes.add(new Carte("carteII_4","rouge",5));
        cartes.add(new Carte("carteII_5","rouge",5));
        cartes.add(new Carte("carteII_6","rouge",8));
        cartes.add(new Carte("carteII_7","rouge",5));

        cartes.add(new Carte("carteII_8","rouge",5));
        cartes.add(new Carte("carteII_9","rouge",5));
        cartes.add(new Carte("carteII_10","rouge",10));
        cartes.add(new Carte("carteII_11","rouge",8));
        cartes.add(new Carte("carteII_12","rouge",5));
        cartes.add(new Carte("carteII_13","rouge",7));
        cartes.add(new Carte("carteII_14","rouge",5));

        cartes.add(new Carte("carteII_15","rouge",5));
        cartes.add(new Carte("carteII_16","rouge",5));
        cartes.add(new Carte("carteII_17","rouge",5));
        cartes.add(new Carte("carteII_18","rouge",5));
        cartes.add(new Carte("carteII_19","rouge",5));
        cartes.add(new Carte("carteII_20","rouge",5));
        cartes.add(new Carte("carteII_21","rouge",5));

        cartes.add(new Carte("carteII_22","rouge",5));
        cartes.add(new Carte("carteII_23","rouge",5));
        cartes.add(new Carte("carteII_24","rouge",5));
        cartes.add(new Carte("carteII_25","rouge",5));
        cartes.add(new Carte("carteII_26","rouge",5));
        cartes.add(new Carte("carteII_27","rouge",5));
        cartes.add(new Carte("carteII_28","rouge",5));
        //Collections.shuffle(cartes);
        return cartes;

    }
}
