module S.W.jeu.modele {
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    exports facade;
    exports modele;
    exports exceptions;
    exports modele.interfaces;
}