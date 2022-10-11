package services;

import contrats.IClientBox;
import contrats.IVOD;
import contrats.Bill;
import contrats.MovieDesc;
import exceptions.IsbnNotFoundException;
import util.movie.MovieParser;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;


public class VOD extends UnicastRemoteObject implements IVOD {

    private List<MovieDesc> movies;
    private static VOD vod_instance = null;

    protected VOD() throws RemoteException {
        movies = MovieParser.getMovies();
    }

    public static VOD getInstance() throws RemoteException {
        if (vod_instance == null)
            vod_instance = new VOD();
        return vod_instance;
    }

    @Override
    public List<MovieDesc> viewCatalog() {
        return movies;
    }

    private MovieDesc findMovieByIsbn(String isbn){
        return movies.stream().filter(x->x.getIsbn().equals(isbn)).findFirst().get();
    }

    @Override
    public Bill playMovie(String isbn, IClientBox box) throws RemoteException, IsbnNotFoundException {
        MovieDesc movieToPlay = findMovieByIsbn(isbn);
        byte[] movieBytes = movieToPlay.getFilmBytes();
        try {
            if (movieToPlay == null) throw new IsbnNotFoundException("isbn not found");
            Thread thread = thread(movieBytes,box);
            thread.start();
            thread.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Bill(movieToPlay.getMovieName(), new BigInteger("15"));
    }


    /**
     * @param movieBytes
     * @param box
     * @return Creating Thread
     * @throws RemoteException
     */
    Thread thread(byte[] movieBytes,IClientBox box) throws RemoteException {
        int chunk = 5;
        box.stream(Arrays.copyOfRange(movieBytes, 0, Math.min(movieBytes.length, chunk)));
        Thread th = new Thread(() -> {
            for (int i = chunk; i < movieBytes.length; i += chunk) {
                try {
                    Thread.sleep(200);
                    box.stream(Arrays.copyOfRange(movieBytes, i, Math.min(movieBytes.length, i + chunk)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return th;
    }

}
