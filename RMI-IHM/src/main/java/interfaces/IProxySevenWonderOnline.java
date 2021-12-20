package interfaces;

import packageDTOs.CarteDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IProxySevenWonderOnline extends Remote {
    Collection<CarteDTO> getCartes() throws RemoteException;
    CarteDTO getCarte(String nom) throws RemoteException;
}
