package programme;

import interfaces.IProxySevenWonderOnline;
import proxy.ProxySevenWonderOnline;
import service.access.RMIServeurConnexion;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProgrammeRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(RMIServeurConnexion.PORT);
            IProxySevenWonderOnline serveurProxy = new ProxySevenWonderOnline();
            registry.rebind(RMIServeurConnexion.DOMAIN_NAME,serveurProxy);
            System.out.println("serveur lancé");
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
