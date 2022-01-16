package facade;

import dao.Dao;

import java.sql.SQLOutput;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

public class TestingFacade {
    public static void main(String[] args) {
        //Dao.CreerUnePartie("mass","choier");
        //System.out.println(Dao.connexion("bonjour","54345345436"));


        //System.out.println(Dao.reAccederAuJeu("61e2ee2fded142420fcc2b1f","alice"));
        //System.out.println(new ObjectId("61e1cffa22202127355d58b0"));
        //System.out.println(new ObjectId(new Date()).toHexString());

        System.out.println(Dao.getEtapesMerveilleConstruite("61e35d970a32442ae1b7c4ad","alice"));
        System.out.println(Dao.getEtapesMerveilleConstruite("61e35d970a32442ae1b7c4ad","bob"));
    }
}
