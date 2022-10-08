package services;

import contrats.IConnextion;
import contrats.IVOD;
import exceptions.WrongCredentialsException;
import exceptions.SignUpException;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * a class for connection client side
 */
public class ConnextionProcess {

    IConnextion stub;

    static Scanner sc = new Scanner(System.in);

    public ConnextionProcess(IConnextion stub) {
        this.stub = stub;
    }

    /**
     * Client creates an account
     * @return vodService
     */
    public IVOD signUp(){
        try {
            System.out.println("\n************************************Create your account here************************************  \n");
            System.out.println("Type s to go back to the home page \n");

            System.out.print("\tEnter your Email : ");
            String email = sc.next();

            if(email.equals(":s"))
                return connect();

            System.out.print("\tEnter your password : ");
            String password = sc.next();

            if (stub.signUp(email, password)) {
                System.out.println("Account created successfully ! ");
                return login();
            } else {
                throw new SignUpException("You already have an account with this email, try to login or use a new email ...");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return signUp();
        }
    }

    /**
     * login client side
     * @return vodService
     */
    public IVOD login(){
        try {
            System.out.println("\n************************************ Login Page ************************************");
            System.out.println("Type s to go back to the home page \n");

            System.out.print("\tEnter your Email : ");
            String mail = sc.next();

            if(mail.equals("s"))
                return connect();

            System.out.print("\tEnter your password : ");
            String pwdLogin = sc.next();

            IVOD vodLoginStub = stub.login(mail, pwdLogin);

            if (vodLoginStub != null) {
                System.out.println("Login successfully!");
                return vodLoginStub;
            } else {
                throw new WrongCredentialsException("Error while trying to login\n ");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return login();
        }

    }

    /**
     * connect to vod platform (login/signup)
     * @return vodService
     */
    public IVOD connect(){
        System.out.println("\n************************************ Welcome to the VOD platform ************************************ ");
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
                case 1 -> vodStub = signUp();
                case 2 -> vodStub = login();
                default -> {
                    System.out.println("Please type a valid number from above");
                    return connect();
                }
            }
            return vodStub;
        }catch (Exception exception) {
            System.out.println("Please type a valid number from above");
            sc.next();
            return connect();
        }
    }
}
