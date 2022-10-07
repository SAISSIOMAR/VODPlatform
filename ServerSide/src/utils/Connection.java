package utils;

import client.Client;
import exceptions.SignUpException;

import exceptions.WrongCredentialsException;
import parser.ClientParser;
import services.IConnection;
import services.IVOD;
import services.VOD;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Connection extends UnicastRemoteObject implements IConnection {
    List<Client> clientList;
    public Connection() throws RemoteException {
        clientList = ClientParser.getClients();
    }
    @Override
    public boolean signUp(String mail, String pwd) throws SignUpException, RemoteException {
        try {

            if(mail.isEmpty() || pwd.isEmpty())
                return false;
            else if(clientList.stream().anyMatch(c -> c.getMail().equals(mail))){
                throw new SignUpException("a client with mail "+ mail + " already exists");
            }
            ClientParser.writeDataClient(mail,pwd);
            clientList.add(new Client(mail,pwd));
            Date.printInfo("A new account is created ");
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public IVOD signIn(String mail, String pwd) throws WrongCredentialsException, RemoteException {
        try {
            if (clientList.stream().noneMatch(x-> x.getMail().equals(mail) && x.getPwd().equals(pwd))) {
                throw new WrongCredentialsException("account doesn't exist");
            }
            Date.printInfo("The client " +mail+ " log in ");
            return VOD.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
