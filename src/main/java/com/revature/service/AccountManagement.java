package com.revature.service;

import com.revature.model.User;

import java.util.Scanner;

public class AccountManagement {
    public void accMenu(Scanner scanner, User user) {
        String choice = "0";

        while (!choice.equals("3")) {

            choice = scanner.nextLine();
            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Create new account");
            System.out.println("2 View existing account");
            System.out.println("3 Return to previous menu and log out");

            switch (choice) {
                case "1": {
                }
                case "2": {
                }
                case "3": {
                    System.out.println("Ending session and returning to previous menu. Have a nice day");
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }


    }
}
