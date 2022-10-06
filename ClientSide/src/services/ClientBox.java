package services;

import contracts.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * class represents the Client Box
 */

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    protected ClientBox() throws RemoteException {
        super();
    }
    @Override
    public void stream(byte[] chunck) throws RemoteException {

        System.out.println("["+new String(chunck)+"]");


    }
}
