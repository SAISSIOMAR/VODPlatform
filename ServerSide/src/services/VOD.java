package services;

import contrats.IClientBox;
import contrats.IVOD;
import contrats.Bill;
import contrats.MovieDesc;
import exceptions.IsbnNotFoundException;
import util.InfoDate;
import util.movie.MovieList;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;


public class VOD extends UnicastRemoteObject implements IVOD {

    MovieList movies = new MovieList();
    private static VOD vod_instance = null;

    protected VOD() throws RemoteException {}

    public static VOD getInstance() throws RemoteException {
        if (vod_instance == null)
            vod_instance = new VOD();
        return vod_instance;
    }

    @Override
    public List<MovieDesc> viewCatalog() {
        return movies.getMoviesDesc();
    }

    @Override
    public Bill playmovie(String isbn, IClientBox box) throws RemoteException, IsbnNotFoundException {
        MovieDesc movieToPlay = movies.findMovieByIsbn(isbn);
        byte[] movieBytes = movieToPlay.getFilmBytes();
        try {
            InfoDate.printInfo("Server received film : " + isbn + " to stram");
            if (movieToPlay == null) throw new IsbnNotFoundException("movie not found");
            int chunk = 4; //chunk size to divide
            box.stream(Arrays.copyOfRange(movieBytes, 0, Math.min(movieBytes.length, chunk)));
            Thread th = new Thread(() -> {
                System.out.println("launching thread...");
                for (int i = chunk; i < movieBytes.length; i += chunk) {
                    try {
                        Thread.sleep(200);
                        box.stream(Arrays.copyOfRange(movieBytes, i, Math.min(movieBytes.length, i + chunk)));
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
        return new Bill(movieToPlay.getMovieName(), new BigInteger("50"));
    }
}
