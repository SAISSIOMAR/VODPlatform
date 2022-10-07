package parser;

import services.MovieDesc;
import services.MovieDescExtended;

import java.util.ArrayList;
import java.util.List;

public class MovieParser {
    public static List<MovieDesc> readDataMovie(){
        List<MovieDesc> movieDescList= new ArrayList<>();
        try {
            List<String[]> dataMovie = Parser.parseData("movie.csv");
            dataMovie.forEach(data -> {
                if (data.length == 4) {
                    movieDescList.add(new MovieDesc(data[0], data[1], data[2], data[3].getBytes()));
                } else if (data.length == 5) {
                    movieDescList.add(new MovieDescExtended(data[0], data[1], data[2], data[3].getBytes(), data[4].getBytes()));
                } else {
                    System.out.println("Error while parsing movie data !");
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return movieDescList;
    }
}
