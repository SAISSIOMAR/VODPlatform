package services;

import contrats.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * class main for client
 */
public class ClientRMI  {

    private ClientRMI() {}

    public static void main(String[] args) {

        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(2001);
            IConnextion stubCNX = (IConnextion) registry.lookup("CNX");

            //make login / sig up
            ConnextionProcess connection = new ConnextionProcess(stubCNX);
            IVOD vodService = connection.connect();

            // view catalog and choose moovie
            MovieProcess movieProcess = new MovieProcess(vodService);
            movieProcess.viewCatalogue();
            movieProcess.wantChooseMovie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}