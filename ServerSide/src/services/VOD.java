package services;

import exceptions.MovieNotFoundException;
import parser.MovieParser;
import utils.Date;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class VOD extends UnicastRemoteObject implements IVOD {
    private List<MovieDesc> movies = MovieParser.readDataMovie();

    private static VOD vod= null;
    /** Constructeur privé */
    private VOD() throws RemoteException
    {
        movies= MovieParser.readDataMovie();
    }
    private MovieDesc findMovieByIsbn(String isbn){
        return movies.stream().filter(x->x.isbn.equals(isbn)).findFirst().get();
    }

    @Override
    public List<MovieDesc> viewCatalog() throws RemoteException {
        return movies;
    }
    public static VOD getInstance() throws RemoteException {
        if (vod == null)
            vod = new VOD();
        return vod;
    }
    @Override
    public Bill playmovie(String isbn, IClientBox box) throws RemoteException {
        MovieDesc movie = findMovieByIsbn(isbn);
        byte[] movieData = movie.getFilmBytes();
        try {
            Date.printInfo("Server received film : " + isbn + " to stram");
            if (movie == null) throw new MovieNotFoundException("movie not found");
            int chunk = 4; //chunk size to divide
            box.stream(Arrays.copyOfRange(movieData, 0, Math.min(movieData.length, chunk)));
            Thread th = new Thread(() -> {
                System.out.println("launching thread...");
                for (int i = chunk; i < movieData.length; i += chunk) {
                    try {
                        Thread.sleep(200);
                        box.stream(Arrays.copyOfRange(movieData, i, Math.min(movieData.length, i + chunk)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            th.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Bill(movie.getMovieName(), new BigInteger("50"));

    }
}
