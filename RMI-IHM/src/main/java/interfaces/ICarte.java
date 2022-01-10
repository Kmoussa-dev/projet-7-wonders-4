package interfaces;

import packageDTOs.Effectif;

import java.util.List;

public interface ICarte {

   public String get_id();

   public void set_id(String _id);

   String getNom();

   void setNom(String nom);

   Effectif getEffectif();

   void setEffectif(Effectif effectif);

   List<IEffet> getLesCouts();

   void setLesCouts(List<IEffet> lesCouts);

   List<IEffet> getLesRessources();

   void setLesRessources(List<IEffet> lesRessources);
}
