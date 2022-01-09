package dao;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import exceptions.*;
import facade.LesJeuCartes;
import interfaces.ICarte;
import modele.Carte;
import modele.EtatPartie;
import modele.Partie;
import modele.PartieJoueur;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Dao {

    private static final MongoClient mongoClient = MongoClients.create("mongodb://172.17.0.2:27017");
    private static final CodecRegistry pojoCodeRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static final MongoDatabase db = mongoClient.getDatabase("sevenwonders").withCodecRegistry(pojoCodeRegistry);



    public static void deplacementCarte(String id, String pseudo, ICarte carte, List<ICarte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        partie.deplacer(pseudo,carte,cartes,modeDeplacement);
        partieMongoCollection.updateOne(Filters.eq("_id", id), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static List<ICarte> getLesCartesCirculants(String id, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesCirculantes();
    }

    public static List<ICarte> getLesCartesConstructionCite(String id, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionCite();
    }

    public static List<ICarte> getLesCartesConstructionMerv(String id, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionMerveille();
    }

    public static void distributionCarteDebut(String id){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        for(int i = 0; i < partie.getPartieJoueurs().size(); i++){
            partie.getPartieJoueurs().get(i).setCartesCirculantes(LesJeuCartes.distributionAGE_I(i,partie.getPartieJoueurs().size()));
        }
        partieMongoCollection.updateOne(Filters.eq("_id", id), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static boolean partieCommence(String id){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.partieCommence();
    }

    public static void accederUnePartie(String id, String pseudo) throws partieDejaTermineException, partieInexistantException {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        if(Objects.isNull(partie)){
            throw new partieInexistantException();
        }
        else if(partie.getEtatPartie().equals(EtatPartie.DEBUT)) {
            partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla"));
            partieMongoCollection.updateOne(Filters.eq("_id", id), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
        }
        else {
            throw new partieDejaTermineException();
        }

    }


    public static void accederUnePartieExistant(String id, String pseudo) throws partieDejaTermineException, partieInexistantException, PseudoInexistantExecption {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        if(Objects.isNull(partie)){
            throw new partieInexistantException();
        }
        else if(partie.getEtatPartie().equals(EtatPartie.TERMINE )) {
            throw new partieDejaTermineException();
        }
        else if(Objects.isNull(partie.getPartieJoueurByPseudo(pseudo))) {
            throw new PseudoInexistantExecption();
        }else {}

    }


    public static boolean authorisationCirculer(String id){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.authorisationCarteCirculant();
    }


    public static  void notification(String id) {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        partie.notifierALaPartiJoueur();
        partieMongoCollection.updateOne(Filters.eq("_id", id), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static void CreerUnePartie(String pseudo, String ticket, int effectif){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = new Partie(ticket,(effectif+1));
        partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla"));
        partieMongoCollection.insertOne(partie);
    }

    public static String getEtatPartie(String id){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",id)).first();
        return partie.getEtatPartie().toString();
    }

    public static Carte getCartesByName(String nom){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes",Carte.class);
        return carteMongoCollection.find(Filters.eq("nom",nom)).first();
    }

    public static Collection<Carte> getCartes(){
        MongoCollection<Carte> carteMongoCollection = db.getCollection("cartes", Carte.class);
        Collection<Carte> carteCollection = new ArrayList<>();
        carteMongoCollection.find().forEach(c->carteCollection.add(c));
        return carteCollection;

    }


}
