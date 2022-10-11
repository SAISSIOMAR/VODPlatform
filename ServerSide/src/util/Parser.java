package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Parser {


    public static List<String[]> parseData(String fileName){
        List<String[]> data = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/"+fileName));
            String readLine = reader.readLine();
            while ((readLine = reader.readLine()) != null) {
                data.add(readLine.split(";"));
            }
            reader.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return data;
    }

    public static void addToFile(String fileName, String... data){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/"+fileName,true));
            writer.write(String.join(";",data));
            writer.newLine();
            writer.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

}
