package services;

import java.rmi.RemoteException;

public interface IClientBox {
    void stream(byte[] chunck) throws RemoteException;
}
