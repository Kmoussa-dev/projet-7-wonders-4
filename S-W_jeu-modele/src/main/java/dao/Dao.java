package dao;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import modele.Carte;
import org.bson.Document;
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

    public static Collection<Carte> getCartes(){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes",Carte.class);
        Collection<Carte> carteCollection = new ArrayList<>();
        carteMongoCollection.find().forEach(c->carteCollection.add(c));
        return carteCollection;
    }

    public static Carte getCartesByName(String nom){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes",Carte.class);
        return carteMongoCollection.find(Filters.eq("nom",nom)).first();
    }

    public static void updatePartie(String id){

    }


}
