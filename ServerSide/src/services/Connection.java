package services;

import contrats.IConnection;
import contrats.IVOD;
import util.InfoDate;
import util.client.Client;
import util.client.ClientList;
import exceptions.WrongCredentialsException;
import exceptions.SignUpException;
import util.client.ClientParser;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Connection extends UnicastRemoteObject implements IConnection, Serializable {

    private ClientList clientList;

    public Connection() throws RemoteException {
        clientList = new ClientList();
    }

    @Override
    public boolean signUp(String mail, String pwd) throws SignUpException {
        try {
            if(mail.isEmpty() || pwd.isEmpty())
                return false;
            else if(clientList.findMail(mail)){
                throw new SignUpException("a client with mail "+ mail + " already exists");
            }
            ClientParser.writeDataClient(mail,pwd);
            clientList.getClients().add(new Client(mail,pwd));
            InfoDate.printInfo("A new account is created ");
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public IVOD login(String mail, String pwd) throws WrongCredentialsException, RemoteException {
        try {
            if (!clientList.findMailPwd(mail, pwd)) {
                throw new WrongCredentialsException("account doesn't exist");
            }
            InfoDate.printInfo("The client " +mail+ " log in ");
            return VOD.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
