package interfaces;

import packageDTOs.Effectif;

public interface ICarte {

   String getNom();

   void setNom(String nom);

   String getCouleur();

   void setCouleur(String couleur) ;

   double getValeur();

   void setValeur(double valeur);

   Effectif getEffectif();

   void setEffectif(Effectif effectif);
}
