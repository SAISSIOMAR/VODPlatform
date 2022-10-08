package util.movie;

import contrats.MovieDesc;
import contrats.MovieDescExtended;
import util.Parser;

import java.util.ArrayList;
import java.util.List;

public class MovieParser {

    public static List<MovieDesc> getMovies(){
        List<MovieDesc> movies= new ArrayList<>();
        try {
            List<String[]> movieData = Parser.parseData("movie.csv");
            movieData.forEach(data -> {
                if (data.length == 4) {
                    movies.add(new MovieDesc(data[0], data[1], data[2], data[3].getBytes()));
                } else if (data.length == 5) {
                    movies.add(new MovieDescExtended(data[0], data[1], data[2], data[3].getBytes(), data[4].getBytes()));
                } else {

                    System.out.println("Movie Parser Error !!!!!");
                }
            });
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
     return movies;
    }

}
