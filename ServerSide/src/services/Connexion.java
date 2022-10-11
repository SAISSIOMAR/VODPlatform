package services;

import contrats.IConnexion;
import contrats.IVOD;

import util.client.Client;

import exceptions.WrongCredentialsException;
import exceptions.SignUpException;
import util.client.ClientParser;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Connexion extends UnicastRemoteObject implements IConnexion, Serializable {

    private List<Client> clientList;

    public Connexion() throws RemoteException {
        clientList = ClientParser.getClients();
    }

    @Override
    public boolean signUp(String mail, String pwd) throws SignUpException {
        try {
            if (mail.isEmpty() || pwd.isEmpty())
                return false;
            else if (clientList.stream().anyMatch(c -> c.getMail().equals(mail))) {
                throw new SignUpException("a client with mail " + mail + " already exists");
            }
            ClientParser.writeDataClient(mail, pwd);
            clientList.add(new Client(mail, pwd));
            date("A new account is created ");
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public IVOD login(String mail, String pwd) throws WrongCredentialsException, RemoteException {
        try {
            if (clientList.stream().noneMatch(x -> x.getMail().equals(mail) && x.getPwd().equals(pwd))) {
                throw new WrongCredentialsException("account doesn't exist");
            }
            date("The client " + mail + " has logged in ");
            return VOD.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public  void date(String s) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(s + " at " + dtf.format(LocalDateTime.now()));
    }
}