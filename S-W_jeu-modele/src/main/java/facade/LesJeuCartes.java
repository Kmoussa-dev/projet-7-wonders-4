package facade;

import effet.Commerce;
import effet.Direction;
import effet.Effet;
import interfaces.ICarte;
import modele.Carte;
import packageDTOs.Effectif;
import packageDTOs.TypeEffet;

import java.util.ArrayList;
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
        //CARTES 3+
        cartes.add(new Carte("carte1","caserne",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,1)),List.of(new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carte2","presse",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PAPIER,1)),List.of()));
        cartes.add(new Carte("carte3","autel",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,2)),List.of()));
        cartes.add(new Carte("carte4","chantier",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOIS,1)),List.of()));
        cartes.add(new Carte("carte5","cavité",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIERRE,1)),List.of()));
        //COMMERCIALE
        cartes.add(new Carte("carte6", "marché", Effectif.TROIS_PLUS, List.of(new Commerce(TypeEffet.PIECE, 1, List.of(TypeEffet.VERRE, TypeEffet.TISSU, TypeEffet.PAPIER), List.of(Direction.GAUCHE, Direction.DROITE))), List.of()));
        cartes.add(new Carte("carte7", "verrerie", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.VERRE, 1)), List.of()));
        cartes.add(new Carte("carte8", "tour de garde", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.BOUCLIER, 1)), List.of(new Effet(TypeEffet.ARGILE, 1))));
        //COMMERCIALE
        cartes.add(new Carte("carte9", "comptoir ouest", Effectif.TROIS_PLUS, List.of(new Commerce(TypeEffet.PIECE, 1, List.of(TypeEffet.ARGILE, TypeEffet.PIERRE, TypeEffet.BOIS, TypeEffet.MINERAI), List.of(Direction.GAUCHE))), List.of()));
        cartes.add(new Carte("carte10", "atelier",  Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.ROUE, 1)), List.of(new Effet(TypeEffet.VERRE, 1))));
        cartes.add(new Carte("carte11", "fosse argileuse", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.ARGILE, 1), new Effet(TypeEffet.MINERAI, 1)), List.of(new Effet(TypeEffet.PIECE, 1))));
        cartes.add(new Carte("carte12", "scriptorium", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.TABLETTE, 1)), List.of(new Effet(TypeEffet.PAPIER, 1))));
        cartes.add(new Carte("carte13", "filon", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.MINERAI, 1)), List.of()));
        cartes.add(new Carte("carte14", "métier à tisser", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.TISSU, 1)), List.of()));
        cartes.add(new Carte("carte15", "théâtre", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.VICTOIRE, 2)), List.of()));
        //COMMERCIALE
        cartes.add(new Carte("carte16", "comptoir est", Effectif.TROIS_PLUS, List.of(new Commerce(TypeEffet.PIECE, 1, List.of(TypeEffet.ARGILE, TypeEffet.PIERRE, TypeEffet.BOIS, TypeEffet.MINERAI), List.of(Direction.DROITE))), List.of()));
        cartes.add(new Carte("carte17", "bassin argileux", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.ARGILE, 1)), List.of()));
        cartes.add(new Carte("carte18", "exploitation forestière", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.PIERRE, 1), new Effet(TypeEffet.BOIS, 1)), List.of(new Effet(TypeEffet.PIECE, 1))));
        cartes.add(new Carte("carte19", "palissade", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.BOUCLIER, 1)), List.of(new Effet(TypeEffet.BOIS, 1))));
        cartes.add(new Carte("carte20", "bains", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.VICTOIRE, 3)), List.of(new Effet(TypeEffet.PIERRE, 1))));
        cartes.add(new Carte("carte21", "officine", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.COMPAS, 1)), List.of(new Effet(TypeEffet.TISSU, 1))));
        cartes.add(new Carte("carte22", "tour de garde", Effectif.QUATRE_PLUS, List.of(new Effet(TypeEffet.BOUCLIER, 1)), List.of(new Effet(TypeEffet.ARGILE, 1))));
        //CARTES 4+
        cartes.add(new Carte("carte23", "excavation", Effectif.QUATRE_PLUS, List.of(new Effet(TypeEffet.PIERRE, 1), new Effet(TypeEffet.ARGILE, 1)), List.of(new Effet(TypeEffet.PIECE, 1))));
        cartes.add(new Carte("carte24", "scriptorium",  Effectif.QUATRE_PLUS, List.of(new Effet(TypeEffet.TABLETTE, 1)), List.of(new Effet(TypeEffet.PAPIER, 1))));
        cartes.add(new Carte("carte25", "taverne", Effectif.QUATRE_PLUS, List.of(new Effet(TypeEffet.PIECE, 5)), List.of()));
        cartes.add(new Carte("carte26", "chantier", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.BOIS, 1)), List.of()));
        cartes.add(new Carte("carte27", "prêteur sur gages", Effectif.QUATRE_PLUS, List.of(new Effet(TypeEffet.VICTOIRE, 3)), List.of()));
        cartes.add(new Carte("carte28", "filon", Effectif.TROIS_PLUS, List.of(new Effet(TypeEffet.MINERAI, 1)), List.of()));
        //Collections.shuffle(cartes)
        return cartes;
    }

    public static List<ICarte> loadDataII(){
        List<ICarte> cartes = new ArrayList<>();
        //CARTES 3+
        // MP
        cartes.add(new Carte("carteII_1","fonderie", Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.MINERAI,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_2","briqueterie",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.ARGILE,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_3","carrière",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIERRE,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_4","scierie",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOIS,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        //PM
        cartes.add(new Carte("carteII_5","presse",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PAPIER,1)),List.of()));
        cartes.add(new Carte("carteII_6","verrerie",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VERRE,1)),List.of()));
        cartes.add(new Carte("carteII_7","métier à tisser",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.TISSU,1)),List.of()));

        //BATIMENT CIVIL
        cartes.add(new Carte("carteII_8","temple",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,3)),
                List.of(new Effet(TypeEffet.BOIS,1),new Effet(TypeEffet.ARGILE,1),new Effet(TypeEffet.VERRE,1))));
        cartes.add(new Carte("carteII_9","statue",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,4)),
                List.of(new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.BOIS,1))));
        cartes.add(new Carte("carteII_10","tribunal",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,4)),
                List.of(new Effet(TypeEffet.ARGILE,2),new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteII_11","aqueduc.",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,5)),List.of(new Effet(TypeEffet.PIERRE,3))));

        //SCIENTIFIQUES
        cartes.add(new Carte("carteII_12","école",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.TABLETTE,1)),
                List.of(new Effet(TypeEffet.BOIS,1),new Effet(TypeEffet.PAPIER,1))));
        cartes.add(new Carte("carteII_13","bibliothèque",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.TABLETTE,1)),
                List.of(new Effet(TypeEffet.PIERRE,2),new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteII_14","dispensaire",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.COMPAS,1)),
                List.of(new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.VERRE,1))));
        cartes.add(new Carte("carteII_15","laboratoire", Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.ROUE,1)),
                List.of(new Effet(TypeEffet.ARGILE,2),new Effet(TypeEffet.PAPIER,1))));

        //MILITAIRES
        cartes.add(new Carte("carteII_16","muraille",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,2)),List.of(new Effet(TypeEffet.PIERRE,3))));
        cartes.add(new Carte("carteII_17","écuries",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,2)),
                List.of(new Effet(TypeEffet.BOIS,1),new Effet(TypeEffet.ARGILE,1),new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carteII_18","champs de tir",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,2)),
                List.of(new Effet(TypeEffet.MINERAI,1),new Effet(TypeEffet.BOIS,2))));

        //COMMERCIALES
        cartes.add(new Carte("carteII_19","forum",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.VERRE,1),new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.PAPIER,1)),
                List.of(new Effet(TypeEffet.ARGILE,2))));  //donne l'une des 3 ressources
        cartes.add(new Carte("carteII_20","caravansérail",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.BOIS,1),new Effet(TypeEffet.PIERRE,1),new Effet(TypeEffet.MINERAI,1),new Effet(TypeEffet.ARGILE,1)),
                List.of(new Effet(TypeEffet.BOIS,2)))); //donne l'une des 4 ressources
        cartes.add(new Carte("carteII_21","vignoble",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIECE,1)),List.of())); //pour chaque carte marron (MP) -> 1 piece

        // CARTES 4+
        //COMMERCIALE
        cartes.add(new Carte("carteII_22","bazar",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.PIECE,2)),List.of())); //donne 2 pieces pour chaque carte grises (PM)
        //MP
        cartes.add(new Carte("carteII_23","scierie",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.BOIS,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_24","fonderie",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.MINERAI,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_25","briqueterie",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.ARGILE,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        cartes.add(new Carte("carteII_26","carrière",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.PIERRE,2)),List.of(new Effet(TypeEffet.PIECE,1))));
        //MILITAIRE
        cartes.add(new Carte("carteII_27","place d'armes",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,2)),
                List.of(new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.BOIS,1))));
        //SCIENTIFIQUE
        cartes.add(new Carte("carteII_28","dispensaire",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.COMPAS,1)),
                List.of(new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.VERRE,1))));

        //Collections.shuffle(cartes);
        return cartes;
    }

    public static List<ICarte> loadDataIII(){
        List<ICarte> cartes = new ArrayList<>();

        //CARTES 3+
        //COMMERCIALES
        cartes.add(new Carte("carteIII_1","arène", Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIECE,3), new Effet(TypeEffet.VICTOIRE,1)),
                List.of(new Effet(TypeEffet.PIERRE,2), new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carteIII_2","port",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIECE,1), new Effet(TypeEffet.VICTOIRE,1)),
                List.of(new Effet(TypeEffet.BOIS,1), new Effet(TypeEffet.MINERAI,1), new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteIII_3","phare",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.PIECE,1), new Effet(TypeEffet.VICTOIRE,1)),
                List.of(new Effet(TypeEffet.PIERRE,1), new Effet(TypeEffet.VERRE,1))));

        //BATIMENTS CIVIL
        cartes.add(new Carte("carteIII_4","jardins",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,5)),
                List.of(new Effet(TypeEffet.ARGILE,2), new Effet(TypeEffet.BOIS,1))));
        cartes.add(new Carte("carteIII_5","bibliothèque",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,6)),
                List.of(new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.PIERRE,1), new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carteIII_6","hôtel de ville",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,6)),
                List.of(new Effet(TypeEffet.PIERRE,2), new Effet(TypeEffet.VERRE,1), new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carteIII_7","panthéon",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,7)),
                List.of(new Effet(TypeEffet.ARGILE,2), new Effet(TypeEffet.VERRE,1), new Effet(TypeEffet.MINERAI,1),
                        new Effet(TypeEffet.PAPIER,1), new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteIII_8","palace",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,8)),
                List.of(new Effet(TypeEffet.PIERRE,1), new Effet(TypeEffet.BOIS,1), new Effet(TypeEffet.MINERAI,1), new Effet(TypeEffet.ARGILE,1),
                        new Effet(TypeEffet.PAPIER,1), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.VERRE,1))));

        //MILITAIRES
        cartes.add(new Carte("carteIII_9","fortifications",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,3)),
                List.of(new Effet(TypeEffet.MINERAI,3), new Effet(TypeEffet.PIERRE,1))));
        cartes.add(new Carte("carteIII_10","atelier de siège",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,3)),
                List.of(new Effet(TypeEffet.ARGILE,3),new Effet(TypeEffet.BOIS,1))));
        cartes.add(new Carte("carteIII_11","arsenal",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,3)),
                List.of( new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.MINERAI,1))));

        //SCIENTIFIQUES
        cartes.add(new Carte("carteIII_12","étude",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.ROUE,1)),
                List.of( new Effet(TypeEffet.BOIS,1), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.PAPIER,1))));
        cartes.add(new Carte("carteIII_13","observatoire",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.ROUE,1)),
                List.of( new Effet(TypeEffet.MINERAI,2), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.VERRE,1))));
        cartes.add(new Carte("carteIII_14","académie",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.COMPAS,1)),
                List.of( new Effet(TypeEffet.PIERRE,2), new Effet(TypeEffet.VERRE,1))));
        cartes.add(new Carte("carteIII_15","loge",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.COMPAS,1)),
                List.of( new Effet(TypeEffet.ARGILE,2), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.PAPIER,1))));
        cartes.add(new Carte("carteIII_16","université",Effectif.TROIS_PLUS,List.of(new Effet(TypeEffet.TABLETTE,1)),
                List.of( new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.VERRE,1),new Effet(TypeEffet.PAPIER,1))));
        //GUILDES
        cartes.add(new Carte("carteIII_17","guilde des scientifiques",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.COMPAS,1),new Effet(TypeEffet.TABLETTE,1),new Effet(TypeEffet.ROUE,1)),
                List.of( new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.PAPIER,1))));
        cartes.add(new Carte("carteIII_18","guilde des espions",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.VICTOIRE,1)),
                List.of( new Effet(TypeEffet.ARGILE,3),new Effet(TypeEffet.VERRE,1))));
        cartes.add(new Carte("carteIII_19","guilde des artisans",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.VICTOIRE,2)),
                List.of( new Effet(TypeEffet.MINERAI,2),new Effet(TypeEffet.PIERRE,2))));
        cartes.add(new Carte("carteIII_20","guilde des magistrats",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.VICTOIRE,1)),
                List.of( new Effet(TypeEffet.BOIS,3),new Effet(TypeEffet.PIERRE,1),new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteIII_21","guilde des philosophes",Effectif.TROIS_PLUS,
                List.of(new Effet(TypeEffet.VICTOIRE,1)),
                List.of( new Effet(TypeEffet.ARGILE,3),new Effet(TypeEffet.PAPIER,1),new Effet(TypeEffet.TISSU,1))));

        //CARTES 4+
        //COMMERCIALES
        cartes.add(new Carte("carteIII_22","port",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.PIECE,1), new Effet(TypeEffet.VICTOIRE,1)),
                List.of(new Effet(TypeEffet.BOIS,1), new Effet(TypeEffet.MINERAI,1), new Effet(TypeEffet.TISSU,1))));
        cartes.add(new Carte("carteIII_23","chambre de commerce",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.PIECE,2), new Effet(TypeEffet.VICTOIRE,2)),
                List.of(new Effet(TypeEffet.ARGILE,2), new Effet(TypeEffet.PAPIER,1))));
        //MILITAIRES
        cartes.add(new Carte("carteIII_24","arsenal",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,3)),
                List.of( new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.TISSU,1),new Effet(TypeEffet.MINERAI,1))));
        cartes.add(new Carte("carteIII_25","cirque",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.BOUCLIER,3)),
                List.of( new Effet(TypeEffet.PIERRE,3), new Effet(TypeEffet.MINERAI,1))));
        //BAT CIVIL
        cartes.add(new Carte("carteIII_26","jardins",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.VICTOIRE,5)),
                List.of(new Effet(TypeEffet.ARGILE,2), new Effet(TypeEffet.BOIS,1))));
        //SCIENTIFIQUE
        cartes.add(new Carte("carteIII_27","université",Effectif.QUATRE_PLUS,List.of(new Effet(TypeEffet.TABLETTE,1)),
                List.of( new Effet(TypeEffet.BOIS,2), new Effet(TypeEffet.VERRE,1),new Effet(TypeEffet.PAPIER,1))));
        //GUILDE
        cartes.add(new Carte("carteIII_28","guilde des bâtisseurs",Effectif.QUATRE_PLUS,
                List.of(new Effet(TypeEffet.VICTOIRE,1)),
                List.of( new Effet(TypeEffet.PIERRE,2), new Effet(TypeEffet.ARGILE,2),new Effet(TypeEffet.VERRE,1))));

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
