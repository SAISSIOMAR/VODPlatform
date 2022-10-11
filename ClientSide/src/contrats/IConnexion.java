package contrats;

import exceptions.WrongCredentialsException;
import exceptions.SignUpException;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IConnexion extends Remote {

    boolean signUp(String email, String password) throws SignUpException, RemoteException;

    IVOD login(String email, String password) throws WrongCredentialsException, RemoteException;
}
