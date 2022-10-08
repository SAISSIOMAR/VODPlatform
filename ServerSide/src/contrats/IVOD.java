package contrats;

import exceptions.IsbnNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface IVOD extends Remote {

    List<MovieDesc> viewCatalog() throws RemoteException;

    Bill playmovie(String isbn, IClientBox box) throws RemoteException, IsbnNotFoundException;
}
