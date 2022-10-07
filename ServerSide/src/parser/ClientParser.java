package parser;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientParser {
    public static List<Client> getClients(){
        List<Client> clients= new ArrayList<>();
        List<String[]> clientData = Parser.parseData("client.csv");
        int index = 1;
        for (String[] record : clientData) {
            if (record.length !=2){
                System.out.println("Warning : La ligne "+index+" de la base Users est erronée !");
            }
            else{
                clients.add(new Client(record[0], record[1]));
            }
            index++;
        }
        return clients;
    }
    public static void writeDataClient(String mail, String mdp){
        Parser.addToFile("client.csv",mail,mdp);
    }
}
