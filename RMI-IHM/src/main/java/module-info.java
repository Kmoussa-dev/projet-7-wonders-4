module RMI.IHM {
    requires java.rmi;
    requires org.mongodb.driver.core;
    exports packageDTOs;
    exports interfaces;
    exports service.access;
    exports exceptions;
    exports effet;
}