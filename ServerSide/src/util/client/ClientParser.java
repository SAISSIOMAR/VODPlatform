package util.client;

import util.Parser;

import java.util.ArrayList;
import java.util.List;

public class ClientParser {

    public static List<Client> getClients(){
        List<Client> clients= new ArrayList<>();
        List<String[]> clientData = Parser.parseData("client.csv");
        int tmp = 1;
        for (String[] record : clientData) {
            if (record.length !=2){
                System.err.println("Error in Client parsing !!!!");
            }
            else{
                clients.add(new Client(record[0], record[1]));
            }
            tmp++;
        }
        return clients;
    }
    public static void writeDataClient(String mail, String mdp){
        Parser.addToFile("client.csv",mail,mdp);
    }
}
