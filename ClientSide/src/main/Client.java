package main;

import services.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client(){}

    public static void main(String[] args) {

        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(2012);
            IConnection stubCNX = (IConnection) registry.lookup("CNX");

            //make login / sig up
            ConnexionProcess connection = new ConnexionProcess(stubCNX);
            IVOD vodService = (IVOD) connection.connect();

            // view catalog and choose moovie
            MovieProcess movieProcess = new MovieProcess(vodService);
            movieProcess.viewCatalogue();
            movieProcess.wantChooseMovie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
