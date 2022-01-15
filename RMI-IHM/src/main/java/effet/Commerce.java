package effet;

import packageDTOs.TypeEffet;

import java.util.ArrayList;
import java.util.List;

public class Commerce extends  Effet {

    private List<TypeEffet> lesEffetJoueursCote;

    private List<Direction> lesDirections;

    public Commerce(){
        super();
        this.lesEffetJoueursCote = new ArrayList<>();
        this.lesDirections = new ArrayList<>();
    }

    public Commerce(TypeEffet effect, int valeur, List<TypeEffet> lesEffetJoueursCote, List<Direction> lesDirections) {
        super(effect, valeur);
        this.lesEffetJoueursCote = lesEffetJoueursCote;
        this.lesDirections = lesDirections;
    }

    public List<TypeEffet> getLesEffetJoueursCote() {
        return lesEffetJoueursCote;
    }

    public void setLesEffetJoueursCote(List<TypeEffet> lesEffetJoueursCote) {
        this.lesEffetJoueursCote = lesEffetJoueursCote;
    }

    public List<Direction> getLesDirections() {
        return lesDirections;
    }

    public void setLesDirections(List<Direction> lesDirections) {
        this.lesDirections = lesDirections;
    }

    @Override
    public String toString() {
        return "Commerce{" +
                "lesEffetJoueursCote=" + lesEffetJoueursCote +
                ", lesDirections=" + lesDirections +
                '}';
    }
}
