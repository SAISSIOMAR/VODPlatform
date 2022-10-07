package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String[]> parseData(String fileName){
        List<String[]> data = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/data/"+fileName));
            String readLine = br.readLine();
            while ((readLine = br.readLine()) != null) {
                data.add(readLine.split(";"));
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return data;
    }

    public static void addToFile(String fileName, String... data){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/"+fileName,true));
            writer.write(String.join(";",data));
            writer.newLine();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
