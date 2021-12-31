package packageDTOs;

import interfaces.ICarte;

import java.util.List;

public class JoueurDTO {
    private String joueur;
    private String plateau;

    private List<ICarte> cartesCirculantes;
    private List<ICarte> cartesConstructionCite;
    private List<ICarte> cartesConstructionMerveille;
    private EtatCarteChoisi etatChoisi;


}
