package facade;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import dao.Dao;
import interfaces.ICarte;
import modele.Carte;
import modele.EtatPartie;
import modele.Partie;
import modele.PartieJoueur;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

public class TestingFacade {
    public static void main(String[] args) {
        System.out.println((EtatPartie.DEBUT.toString() == "DEBUT"));
    }
}
