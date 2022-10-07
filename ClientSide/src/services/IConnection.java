package services;


import exceptions.SignUpException;

import exceptions.WrongCredentialsException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {

    boolean signUp(String mail, String pwd) throws SignUpException, RemoteException;



    IVOD signIn(String mail, String pwd) throws WrongCredentialsException, RemoteException;
}