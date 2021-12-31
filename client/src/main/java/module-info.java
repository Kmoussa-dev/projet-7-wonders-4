module org.example.client {
    requires java.rmi;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires RMI.IHM;
    requires S.W.jeu.modele;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens org.example.client.vues to javafx.fxml;
    exports org.example.client.executeclient;
}