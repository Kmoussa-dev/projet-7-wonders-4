package facade;

import dao.Dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

public class TestingFacade {
    public static void main(String[] args) {
        //Dao.CreerUnePartie("mass","choier");
        //System.out.println(Dao.connexion("bonjour","54345345436"));

        System.out.println(Dao.getPartie("61e218c355d3af0d45c8407b"));
        //System.out.println(new ObjectId("61e1cffa22202127355d58b0"));
        //System.out.println(new ObjectId(new Date()).toHexString());
    }
}
