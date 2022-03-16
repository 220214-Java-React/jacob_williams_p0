package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;

import java.util.Scanner;

public class Transactions {
    public void transMenu(Scanner scanner, Account account) {
        String choice = "";
        User loggedIn;
        while (!choice.equals("3")) {
            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Deposit");
            System.out.println("2 Withdrawal");
            System.out.println("3 Return to previous menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                }
                case "2": {
                }
                case "3": {
                    System.out.println("Returning to previous menu");
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }


    }
}
