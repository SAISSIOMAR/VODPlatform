package services;

import contrats.*;
import exceptions.SignUpException;
import exceptions.WrongCredentialsException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;


public class ClientRMI  {
    static Scanner sc = new Scanner(System.in); // for connexion
    static Scanner sc1 = new Scanner(System.in); // for movie

    private ClientRMI() {}

    public static void main(String[] args) {

        try {

            Registry registry = LocateRegistry.getRegistry(2012);
            IConnexion stubCNX = (IConnexion) registry.lookup("vod");
            IVOD vodService = connect(stubCNX);
            viewCatalogue(vodService); // display the movie catalog
            chooseMovieProcess(vodService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /***************************************************   Connexion   *******************************************************/



    public static IVOD signUp(IConnexion stub){
        try {
            System.out.println("\n------------------------------------------------------------------------- Sign Up Page -------------------------------------------------------------------------  \n");
            System.out.print("\tEnter your Email : ");
            String mail = sc.next();
            System.out.print("\tEnter your password : ");
            String password = sc.next();
            if (stub.signUp(mail, password)) {
                System.out.println("Account created successfully ! ");
                return login(stub);
            } else {
                throw new SignUpException("You already have an account with this email, try to login or use a new email ...");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return signUp(stub);
        }
    }

    public static IVOD login(IConnexion stub){
        try {
            System.out.println("\n-------------------------------------------------------------------------------- Login Page -------------------------------------------------------------------------------");
            System.out.print("\tEnter your Email : ");
            String mail = sc.next();
            System.out.print("\tEnter your password : ");
            String password = sc.next();
            IVOD vod = stub.login(mail, password);
            if (vod != null) {
                System.out.println("Login successfully!");
                return vod;
            } else {
                throw new WrongCredentialsException("Error while trying to login\n ");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return login(stub);
        }

    }

    public static IVOD connect(IConnexion stub){
        System.out.println("\n--------------------------------------------------------------------- Welcome to the VOD platform ------------------------------------------------------------------------- ");
        System.out.println("\n1: Type 1 to create an account");
        System.out.println("2: If you already have an account type 2");
        System.out.println("0: press 0 to exit");
        int param ;
        IVOD vodStub = null;
        System.out.print("\tType Here :  ");
        try {
            param = sc.nextInt();
            switch (param) {
                case 0 -> {
                    System.out.println("\n\tEXITING... ");
                    exit(0);
                }
                case 1 -> vodStub = signUp(stub);
                case 2 -> vodStub = login(stub);
                default -> {
                    System.out.println("Please type a valid number from above");
                    return connect(stub);
                }
            }
            return vodStub;
        }catch (Exception exception) {
            System.out.println("Please type a valid number from above");
            sc.next();
            return connect(stub);
        }
    }


    /***************************************************   Movie   *******************************************************/




    public static void chooseMovieProcess(IVOD stub){
        chooseMovie(stub);
        System.out.println("\nType Y to watch another movie, or type N to exit ");

        String rep = sc1.next();
        if(rep.equals("Y"))
            chooseMovieProcess(stub);
        if(rep.equals("N")){
            exit(0);
        }
    }

    public static void chooseMovie(IVOD stub) {
        try {
            System.out.println("\nPlease type the movie ISBN that you want to watch  : ");
            String movieChosen = sc1.next();
            Bill b = stub.playMovie(movieChosen, new ClientBox());
            System.out.println("\tBill :"+b);
        }catch (Exception e){
            System.out.println("ISBN not valid, try again please .");
            chooseMovie(stub);
        }
    }

    public static void viewCatalogue(IVOD stub) {
        System.out.println("\n--------------------------------------------------------------------- Welcome to the VOD platform --------------------------------------------------------------------------\n ");
        try {
            System.out.println("\nThe movies that currently available are :  \n");
            Thread.sleep(250);
            List<MovieDesc> movies = stub.viewCatalog();
            int i =0;
            for(MovieDesc movie : movies){
                Thread.sleep(250);
                System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>> Movie {"+(++i)+"} <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.print(movie+"\n\n");

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}