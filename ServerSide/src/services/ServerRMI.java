package services;

import contrats.IConnexion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI  {

    public ServerRMI()  {
    }

    public static void main(String[] args)  {
        try {
            IConnexion cnx = new Connexion();
            Registry registry = LocateRegistry.createRegistry(2012);
            registry.rebind("vod", cnx);
            System.out.println("Server is up");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
