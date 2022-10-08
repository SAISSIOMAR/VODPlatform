package contrats;

import exceptions.WrongCredentialsException;
import exceptions.SignUpException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * an interface for connection
 */
public interface IConnextion extends Remote {
    /**
     * Client signs up and added to the database in the server side
     * @param email the email of the client
     * @param password the password of the client
     * @return true if sign up is ok else false
     * @throws SignUpException
     * @throws RemoteException
     */
    boolean signUp(String email, String password) throws SignUpException, RemoteException;

    /**
     * The client logins
     * @param email the email of the client
     * @param password the password of the client
     * @return VODService if login succeed
     * @throws WrongCredentialsException
     * @throws RemoteException
     */
    IVOD login(String email, String password) throws WrongCredentialsException, RemoteException;
}
