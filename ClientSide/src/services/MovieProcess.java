package services;

import contracts.Bill;
import contracts.IVODService;
import contracts.MovieDesc;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class MovieProcess {
    IVODService stub;

    static Scanner sc = new Scanner(System.in);

    public MovieProcess(IVODService stub) {
        this.stub = stub;
    }

    public void wantChooseMovie(){
        chooseMovie();
        System.out.println("\nType Y if you want to see antother Mover or type N to exit ");
        String in = sc.next();
        if(in.equals("Y"))
            wantChooseMovie();
        if(in.equals("N")) {
            exit(0);
        }
    }

    /**
     * The client choses a movie
     */
    public void chooseMovie() {
        try {
            System.out.println("\nPlease choose the movie you want to see by its ISBN : ");
            String movieChosen = sc.next();
            Bill b = stub.playmovie(movieChosen, new ClientBox());
            System.out.println("\n The bill for the movie chosen  :"+b);
        }catch (Exception e){
            System.out.println("Error occured , please try again");
            chooseMovie();
        }
    }

    /**
     *  catag client side
     */
    public void viewCatalogue() {
        System.out.println("\n+++++++++THIS IS VOD-PLATFORM++++++++++\n ");
        try {
            System.out.println("\nThis is all the movies we have : ");
            Thread.sleep(250);


            List<MovieDesc> movies = stub.viewCatalog();
            int i =0;
            for(MovieDesc movie : movies){
                Thread.sleep(250);
                System.out.print("+++++++++++++++++++++++++++++++++++++ Movie ["+(++i)+"] +++++++++++++++++++++++++++++++++++++\n");
                System.out.print(movie);
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
