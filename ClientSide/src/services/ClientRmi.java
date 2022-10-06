package services;

import contracts.IConnexion;
import contracts.IVODService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRmi {

    private ClientRmi(){}

    public static void main(String[] args) {

        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(2001);
            IConnexion stubCNX = (IConnexion) registry.lookup("CNX");

            //make login / sig up
            ConnexionProcess connection = new ConnexionProcess(stubCNX);
            IVODService vodService = connection.connect();

            // view catalog and choose moovie
            MovieProcess movieProcess = new MovieProcess(vodService);
            movieProcess.viewCatalogue();
            movieProcess.wantChooseMovie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
