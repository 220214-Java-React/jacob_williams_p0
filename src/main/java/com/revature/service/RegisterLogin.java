package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserRepo;

import java.util.Scanner;

public class RegisterLogin {
    //display menu options
    public static User menuDisplay(Scanner scanner) {
        String choice  = "";
        User newuser = null;
        User loggedIn =null;
        UserRepo uRepo = new UserRepo();
        while (!choice.equals("3")) {
            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Register new user account");
            System.out.println("2 Log in using existing user account");
            System.out.println("3 Exit");
            choice= scanner.nextLine();

            switch (choice) {
                case "1": {
                    newuser = gatherCreationInfo(scanner, uRepo);


                    uRepo.create(newuser);
                    break;
                }
                case "2": {
                    while (loggedIn == null){
                    loggedIn = gatherLoginInfo(scanner, uRepo);
                    if (loggedIn != null){

                    AccountManagement.accMenu(scanner,loggedIn);
                    loggedIn = null;

                    }
                        break;
                    }
                }
                case "3": {
                    System.out.println("Closing program, have a nice day.");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }

        return loggedIn;
    }
//gather new user info
    private static User gatherCreationInfo(Scanner scanner, UserRepo uRepo){
        User builtUser = null;
        boolean uniqueName = false;
        String username ="";
        while (!uniqueName) {
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            if (uRepo.getByUserName(username) == null){
                uniqueName = true;
            }
            else{
                System.out.println("Username already taken, please try again");

            }
        }
        String password = "";
        while(password.equals("")){
            System.out.println("Please enter password:");
            password = scanner.nextLine();
        }

        String fname = "";
        while(fname.equals("")){
            System.out.println("Enter first name:");
            fname= scanner.nextLine();
        }

        String lname = "";
        while(lname.equals("")){
            System.out.println("Enter last name:");
            lname= scanner.nextLine();
        }
        builtUser = new User(username, password, fname, lname);

        return builtUser;

    }

    private static User gatherLoginInfo(Scanner scanner, UserRepo uRepo){

        String username ="";
        String password = "";
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            if (uRepo.getByUserName(username)!= null && uRepo.getByUserName(username).getPassword().equals(password) ){
                return uRepo.getByUserName(username);
            }
            else{
                System.out.println("Incorrect login information, please register if new user. If registered user please try again.");
                return null;

            }

    }

}
