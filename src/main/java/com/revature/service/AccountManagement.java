package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.AccountRepo;
import com.revature.repository.UserRepo;

import java.util.Scanner;

public class AccountManagement {
    public static void accMenu(Scanner scanner, User user) {
        String choice = "0";
        AccountRepo aRepo = new AccountRepo();

        while (!choice.equals("3")) {


            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Create new account");
            System.out.println("2 View existing account");
            System.out.println("3 Return to previous menu and log out");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                   aRepo.create( gatherCreationInfo(scanner, aRepo, user));
                    System.out.println("new account creation complete!");
                    break;
                }
                case "2": {
                  Account verifiedaccount = null;
                  int useid = user.getUserID();

                    if (aRepo.getByUserid(user.getUserID()).isEmpty()){
                        System.out.println("No accounts found for this user");
                    }
                    else{
                        while (verifiedaccount == null) {
                            for (Account a : aRepo.getByUserid(user.getUserID())) {
                                System.out.println(a.toString());
                            }

                            System.out.println("Please enter the account name you wish to transact from");
                            String searchName = scanner.nextLine();
                            if (aRepo.getByAccountNameAndUserid(searchName, useid) != null){
                               verifiedaccount = aRepo.getByAccountNameAndUserid(searchName, useid);
                                Transactions.transMenu(scanner,verifiedaccount, user, aRepo);
                            }
                            else{
                                System.out.println("No account found by that name, please try again.");
                            }
                        }

                    }


                }
                case "3": {
                    System.out.println("Ending session and returning to previous menu. Have a nice day");
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }


    }
    private static Account gatherCreationInfo(Scanner scanner, AccountRepo aRepo, User user){
        Account builtaccount = null;
        boolean uniqueName = false;
        String accountName ="";
        while (!uniqueName) {
            System.out.println("Enter account name: ");
            accountName = scanner.nextLine();
            if (aRepo.getByAccountNameAndUserid(accountName, user.getUserID()) == null){
                uniqueName = true;
            }
            else{
                System.out.println("Account name already taken, please try again");

            }
        }
        String type = "";
        String choice = "";
        while(type.equals("")){
            System.out.println("Please enter the number that matches your desired account type.");
            System.out.println("1: New checking account");
            System.out.println("2: New savings account");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    type = "Checking";
                    break;
                }
                case "2": {
                    type = "Savings";
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }

        }

        double accbal = -1.00;
        while(accbal < 0){
            System.out.println("Enter starting balance:");
            accbal= Double.parseDouble(scanner.nextLine());
        }


        builtaccount = new Account(accountName, type, (float)accbal, user.getUserID());

        return builtaccount;}
}
