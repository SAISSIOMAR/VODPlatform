package contrats;

import exceptions.WrongCredentialsException;
import exceptions.SignUpException;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IConnexion extends Remote {

    boolean signUp(String mail, String pwd) throws SignUpException, RemoteException;


    IVOD login(String mail, String pwd) throws WrongCredentialsException, RemoteException;
}
