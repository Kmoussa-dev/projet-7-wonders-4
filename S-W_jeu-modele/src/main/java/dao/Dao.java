package dao;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import exceptions.*;
import facade.LesJeuCartes;
import interfaces.ICarte;
import modele.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import packageDTOs.Carte;
import packageDTOs.ModeDeplacement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Dao {

    private static final MongoClient mongoClient = MongoClients.create("mongodb://172.17.0.2:27017");
    private static final CodecRegistry pojoCodeRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static final MongoDatabase db = mongoClient.getDatabase("sevenwonders").withCodecRegistry(pojoCodeRegistry);



    public static void deplacementCarte(String idPartie, String pseudo, Carte carte, List<Carte> cartes, ModeDeplacement modeDeplacement) throws CarteInexistantException, CarteDejaException, PartieSuspenduOuTermine {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        partie.deplacer(pseudo,carte,cartes,modeDeplacement);
        partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static List<Carte> getLesCartesCirculants(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesCirculantes();
    }

    public static List<Carte> getLesCartesConstructionCite(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionCite();
    }

    public static List<Carte> getLesCartesConstructionMerv(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.getPartieJoueurByPseudo(pseudo).getCartesConstructionMerveille();
    }

    public static void distributionCarteDebut(String idPartie){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        for(int i = 0; i < partie.getPartieJoueurs().size(); i++){
            partie.getPartieJoueurs().get(i).setCartesCirculantes(LesJeuCartes.distributionAGE_I(i));
        }
        partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static boolean partieCommence(String idPartie){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.partieCommence();
    }

    public static void accederUnePartie(String idPartie, String pseudo) throws partieDejaTermineException, partieInexistantException, partiePleinExecption {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        if(Objects.isNull(partie)){
            throw new partieInexistantException();
        }
        else if(partie.getEtatPartie().equals(EtatPartie.DEBUT)) {
            partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla",false));
            partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
        }
        else {
            throw new partieDejaTermineException();
        }

    }

    public static Partie getPartie(String idPartie){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        return partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
    }

    public static boolean createurDuJeu(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.getPartieJoueurByPseudo(pseudo).isCreateur();
    }


    public static boolean authorisationCirculer(String idPartie){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        return partie.authorisationCarteCirculant();
    }


    public static  void notification(String idPartie) {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
        partie.notifierALaPartiJoueur();
        partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("partieJoueurs", partie.getPartieJoueurs())));
    }

    public static void creerUnePartie(String pseudo, String ticket) throws partiePleinExecption {
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = new Partie(ticket);
        partie.ajouterPartieJoueur(new PartieJoueur(pseudo,"blabla",true));
        partieMongoCollection.insertOne(partie);
    }


    public static String getEtatPartie(String idPartie){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id",idPartie)).first();
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

    public static void inscription(String pseudo, String mdp) {
        MongoCollection<Joueur> joueurMongoCollection = db.getCollection("joueurs",Joueur.class);
        Joueur joueur = new Joueur(pseudo,mdp);
        joueurMongoCollection.insertOne(joueur);
    }

    public static boolean connexion(String pseudo, String mdp) {
        MongoCollection<Joueur> joueurMongoCollection = db.getCollection("joueurs", Joueur.class);
        return Objects.nonNull(joueurMongoCollection.find(Filters.and(Filters.eq("_id",pseudo),Filters.eq("mdp", mdp))).first());
    }

    public static boolean reAccederAuJeu(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Collection<Partie> partieCollection = new ArrayList<>();
        Partie partie = partieMongoCollection.find(Filters.and(Filters.eq("_id",idPartie),Filters.eq("etatPartie","SUSPENDU"))).first();
        return  (!Objects.isNull(partie.getPartieJoueurByPseudo(pseudo)));
    }

    public static Collection<Partie> getLesPartiesSuspendu(){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Collection<Partie> partieCollection = new ArrayList<>();
        partieMongoCollection.find(Filters.eq("etatPartie","SUSPENDU")).forEach(p -> partieCollection.add(p));
        return partieCollection;
    }

    public static boolean suspendreLaPartie(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.and(Filters.eq("_id",idPartie),Filters.or(Filters.eq("etatPartie","EN_COURS"), Filters.eq("etatPartie","DEBUT")))).first();
        if(partie.getPartieJoueurByPseudo(pseudo).isCreateur()){
            partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("etatPartie", "SUSPENDU")));
            return true;
        }
        else {
            return false;
        }

    }

    public static boolean quitter(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Partie partie = partieMongoCollection.find(Filters.eq("_id", idPartie)).first();
        if(partie.getPartieJoueurByPseudo(pseudo).isCreateur()){
            partieMongoCollection.deleteOne(Filters.eq("_id", idPartie));
            return true;
        }
        else {
            return false;
        }
    }



    public static boolean reprendreUnePartie(String idPartie, String pseudo){
        MongoCollection<Partie> partieMongoCollection = db.getCollection("parties", Partie.class);
        Collection<Partie> partieCollection = new ArrayList<>();
        Partie partie = partieMongoCollection.find(Filters.and(Filters.eq("_id",idPartie),Filters.eq("etatPartie","SUSPENDU"))).first();
        if(partie.getPartieJoueurByPseudo(pseudo).isCreateur()){
            partieMongoCollection.updateOne(Filters.eq("_id", idPartie), Updates.combine(Updates.set("etatPartie", "DEBUT")));
            return true;
        }
        else {
            return false;
        }
    }





}
