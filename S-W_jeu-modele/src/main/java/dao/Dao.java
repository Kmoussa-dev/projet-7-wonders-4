package dao;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import facade.LesJeuCartes;
import interfaces.ICarte;
import modele.Carte;
import modele.Partie;
import modele.PartieJoueur;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dao {

    private static final MongoClient mongoClient = MongoClients.create("mongodb://172.17.0.2:27017");
    private static CodecRegistry pojoCodeRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static final MongoDatabase db = mongoClient.getDatabase("sevenwonders").withCodecRegistry(pojoCodeRegistry);

    private List<ICarte> lesCarteAgeI = LesJeuCartes.loadData();

    public static Collection<Carte> getCartes(){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes", Carte.class);
        Collection<Carte> carteCollection = new ArrayList<>();
        carteMongoCollection.find().forEach(c->carteCollection.add(c));
        return carteCollection;

    }

    public static void ajouterJeueurALaPartie(String id, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla"));
        partieMongoCollection.updateOne(Filters.eq("_id", id), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static void CreerUnePartie(String pseudo, String ticket, int effectif){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = new Partie(ticket,(effectif+1));
        partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla"));
        partieMongoCollection.insertOne(partie);
    }


    public static void distributionCarteDebut(String id){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        int i = 0;
        for (PartieJoueur partieJoueur : partie.getPartieJoueurs()){
            partieJoueur.setCartesCirculantes(LesJeuCartes.distributionAGE_I(i,partie.getPartieJoueurs().size()));
            i++;
        }
    }


    public static Carte getCartesByName(String nom){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes",Carte.class);
        return carteMongoCollection.find(Filters.eq("nom",nom)).first();
    }




}
