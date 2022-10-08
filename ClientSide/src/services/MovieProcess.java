package services;

import contrats.Bill;
import contrats.IVOD;
import contrats.MovieDesc;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * a class for the client to choose a movie
 */
public class MovieProcess {

    IVOD stub;

    static Scanner sc = new Scanner(System.in);

    public MovieProcess(IVOD stub) {
        this.stub = stub;
    }

    public void wantChooseMovie(){
        chooseMovie();
        System.out.println("\nType Y to watch another movie, or type N to exit ");

        String rep = sc.next();
        if(rep.equals("Y"))
            wantChooseMovie();
        if(rep.equals("N")){
            exit(0);
        }
    }

    /**
     * The client chooses a movie by its ISBN
     */
    public void chooseMovie() {
        try {
            System.out.println("\nPlease type the movie ISBN that you want to watch  : ");
            String movieChosen = sc.next();
            Bill b = stub.playmovie(movieChosen, new ClientBox());
            System.out.println("\tBill :"+b);
        }catch (Exception e){
            System.out.println("ISBN not valid, try again please .");
            chooseMovie();
        }
    }

    /**
     * view catag client side
     */
    public void viewCatalogue() {
        System.out.println("\n************************************ Welcome to the VOD platform ************************************\n ");
        try {
            System.out.println("\nThe movies that currently available are :  ");
            Thread.sleep(250);
            List<MovieDesc> movies = stub.viewCatalog();
            int i =0;
            for(MovieDesc movie : movies){
                Thread.sleep(250);
                System.out.print("************************************ Movie ["+(++i)+"] ************************************");
                System.out.print(movie);
                System.out.println("************************************************************************************************************\n");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
