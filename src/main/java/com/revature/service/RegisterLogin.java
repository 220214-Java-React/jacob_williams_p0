package com.revature.service;

import com.revature.model.User;

import java.util.Scanner;

public class RegisterLogin {
    //display menu options
    public void menuDisplay(Scanner scanner) {
        String choice  = "";
        User loggedIn;
        while (!choice.equals("3")) {
            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Register new user account");
            System.out.println("2 Log in using existing user account");
            System.out.println("3 Exit");
            choice= scanner.nextLine();

            switch (choice) {
                case "1": {
                }
                case "2": {

                }
                case "3": {
                    System.out.println("Closing program, have a nice day.");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }


    }

}
