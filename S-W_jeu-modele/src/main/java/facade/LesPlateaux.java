package facade;
import modele.Plateau;
import modele.TypePlateau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LesPlateaux {

        public static final List<Plateau> lesPlateaux = loadDataPlateaux();


        public static List<Plateau> loadDataPlateaux(){

            List<Plateau> plateaux = new ArrayList<>();

            plateaux.add(new Plateau(TypePlateau.ALEXANDRIA).initPlateau());
            plateaux.add(new Plateau(TypePlateau.BABYLON).initPlateau());
            plateaux.add(new Plateau(TypePlateau.EPHOSOS).initPlateau());
            plateaux.add(new Plateau(TypePlateau.RHODOS).initPlateau());

            Collections.shuffle(plateaux);

            return plateaux;
        }



        public static Plateau distribution(int index) {

            switch (index) {
                case 0:
                    return lesPlateaux.get(0);
                case 1:
                    return lesPlateaux.get(1);
                case 2:
                    return lesPlateaux.get(2);
                case 3:
                    return lesPlateaux.get(3);
                default:
                    return null;
            }

        }

    }
