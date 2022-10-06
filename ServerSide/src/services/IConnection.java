package services;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {

    boolean signUp(String mail, String pwd) throws SignUpFailed, RemoteException;



    IVODService SignIn(String mail, String pwd) throws InvalidCredentialsException, RemoteException;
}