package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repository.AccountRepo;
import com.revature.repository.TransactionRepo;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Transactions {
    public static void transMenu(Scanner scanner, Account account, User user, AccountRepo aRepo) {
        String choice = "";
        TransactionRepo tRepo = new TransactionRepo();
        User loggedIn = user;
        Account activeAccount = account;
        while (!choice.equals("4")) {
            System.out.println("Please enter the number of your choice from the menu below");
            System.out.println("1 Deposit");
            System.out.println("2 Withdrawal");
            System.out.println("3 See all transactions on current account");
            System.out.println("4 Return to previous menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    BalanceUpdate(Deposit(activeAccount, scanner, tRepo), activeAccount, aRepo);

                    break;
                }
                case "2": {
                    BalanceUpdate(Withdrawal(activeAccount, scanner, tRepo), activeAccount, aRepo);
                    break;
                }
                case "3": {
                    if (tRepo.getByAccountNum(account.getAcctID()).isEmpty()) {
                        System.out.println("No transactions found for this account");
                    } else {
                        for (Transaction t : tRepo.getByAccountNum(account.getAcctID())) {
                            System.out.println(t.toString());

                        }

                    }
                    break;
                }

                case "4": {
                    System.out.println("Returning to previous menu");
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter the number of your choice.");
                }
            }
        }


    }
    //create deposit transaction object for addition to the transaction db
    public static Transaction Deposit(Account a, Scanner scanner, TransactionRepo tRepo) {
        double amount = -1.00;
        String description = "";
        String type = "Deposit";
        int accnum = a.getAcctID();
        while (amount < 1) {
            System.out.println("Enter amount for your deposit.");
            amount = parseDoubleOrNeg(scanner.nextLine());
            if (amount < 1) {
                System.out.println("Please enter a number one or greater for your deposit.");
            }
        }

        while (description.equals("")) {
            System.out.println("Please enter description for transaction.");
            description = scanner.nextLine();
            if (description.equals("")) {
                System.out.println("Please enter at least one character.");
            }

        }

        Transaction ta = new Transaction(amount, description, type, accnum);
        tRepo.create(ta);


        return ta;
    }
//create withdrawal transaction object for addition to the transaction db
    public static Transaction Withdrawal(Account a, Scanner scanner, TransactionRepo tRepo) {
        Transaction t = null;
        double amount = -1.00;
        String description = "";
        String type = "Withdrawal";
        int accnum = a.getAcctID();
        while (amount < 1) {
            System.out.println("Enter amount for your deposit.");
            amount = parseDoubleOrNeg(scanner.nextLine());
            if (amount < 1) {
                System.out.println("Please enter a number one or greater for your withdrawal.");
            }
            if (a.getAcctBalance() - amount < 0) {
                System.out.println("Cannot overdraw account, please enter another amount.");
                amount = -1.00;
            }
        }

        while (description == "") {
            System.out.println("Please enter description for transaction.");
            description = scanner.nextLine();
            if (description.equals("")) {
                System.out.println("Please enter at least one character.");
            }

        }

        t = new Transaction(amount, description, type, accnum);
        tRepo.create(t);


        return t;
    }
//change the balance in account records
    public static void BalanceUpdate(Transaction t, Account a, AccountRepo aRepo) {

        if (t.getTransType().equals("Deposit")) {
            double newbal = a.getAcctBalance() + t.getTransAmount();
            try (Connection connection = ConnectionFactory.getConnection()) {

                String sql = "update account set balance = ? where id= ?";

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setDouble(1, newbal);
                stmt.setInt(2, a.getAcctID());

                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Your connection is bad and you should feel bad.");
            }
        } else {
            double newbal = a.getAcctBalance() - t.getTransAmount();
            try (Connection connection = ConnectionFactory.getConnection()) {

                String sql = "update account set balance = ? where id= ?";

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setDouble(1, newbal);
                stmt.setInt(2, a.getAcctID());

                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Your connection is bad and you should feel bad.");
            }
        }
    }
//catch parsing errors when entering values for transactions
    public static Double parseDoubleOrNeg(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numerical value");
            return -1.00;
        }
    }
}
