package contracts;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientBox extends Remote {

    /**
     *stream a chunk of bytes
     * @param chunck array of bytes
     * @throws RemoteException
     */

    void stream(byte[] chunck) throws RemoteException;
}
