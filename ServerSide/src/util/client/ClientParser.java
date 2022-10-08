package util.client;

import util.Parser;

import java.util.ArrayList;
import java.util.List;

public class ClientParser {

    public static List<Client> readDataClient(){
        List<Client> clients= new ArrayList<>();
        List<String[]> clientData = Parser.readData("client.csv");
        clientData.forEach(data -> {
            if(data.length == 2) {
                clients.add(new Client(data[0], data[1]));
            }
            else {
                System.out.println("Error while parsing client data !");
            }
        });
        return clients;
    }


    public static void writeDataClient(String mail, String mdp){
        Parser.writeData("client.csv",mail,mdp);
    }
}
