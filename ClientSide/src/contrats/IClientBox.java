package contrats;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  an interface IClientBox
 */
public interface IClientBox extends Remote {

    /**
     * stream a chunk of movie byts
     * @param chunck a byte array
     * @throws RemoteException
     */
    void stream(byte[] chunck) throws RemoteException;
}
