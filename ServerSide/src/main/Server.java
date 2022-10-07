package main;

import services.IConnection;
import utils.Connection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public Server()  {
    }

    public static void main(String[] args)  {
        try {
            IConnection objetDistant = new Connection();
            Registry registry = LocateRegistry.createRegistry(2012);
            registry.rebind("CNX", objetDistant);
            System.out.println("Server is ready...");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

