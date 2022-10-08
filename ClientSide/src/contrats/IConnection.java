package contrats;

import exceptions.WrongCredentialsException;
import exceptions.SignUpException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * an interface for connection
 */
public interface IConnection extends Remote {
    /**
     * sign up and add a new client to db server side
     * @param mail the email of the client
     * @param pwd the password of the client
     * @return true if sign up is ok else false
     * @throws SignUpException
     * @throws RemoteException
     */
    boolean signUp(String mail, String pwd) throws SignUpException, RemoteException;

    /**
     * verify the login of a client
     * @param mail the email of the client
     * @param pwd the password of the client
     * @return VODService if login succeed
     * @throws WrongCredentialsException
     * @throws RemoteException
     */
    IVOD login(String mail, String pwd) throws WrongCredentialsException, RemoteException;
}
