package contracts;

import exceptions.SignUpException;
import exceptions.WrongCredentialsException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnexion extends Remote {

    boolean signUp(String mail, String pwd) throws SignUpException, RemoteException;


    IVODService login(String mail, String pwd) throws WrongCredentialsException, RemoteException;


}
