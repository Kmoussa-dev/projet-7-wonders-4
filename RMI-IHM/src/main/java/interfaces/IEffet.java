package interfaces;

import packageDTOs.TypeEffet;

import java.io.Serializable;

public class IEffet implements Serializable {
        private TypeEffet effect;

        private int valeur;

        public IEffet(){}

        public IEffet(TypeEffet effect, int valeur) {
                this.effect = effect;
                this.valeur = valeur;
        }

        public void setTypeEffet(TypeEffet effect){
                this.effect = effect;
        }

        public void setValeur(int valeur){
                this.valeur = valeur;
        }


        public TypeEffet getTypeEffet() {
                return this.effect;
        }


        public int getValeur() {
                return this.valeur;
        }

        @Override
        public String toString() {
                return "Effet{" +
                        "effect=" + effect +
                        ", valeur=" + valeur +
                        '}';
        }
}
