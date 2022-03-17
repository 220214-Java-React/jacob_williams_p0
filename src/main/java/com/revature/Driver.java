package com.revature;

import com.revature.model.User;
import com.revature.service.AccountManagement;
import com.revature.service.RegisterLogin;
import com.revature.utility.ScannerFactory;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        System.out.println("Welcome to Williams banking!");
        boolean running = true;
        User loggedUser =null;
        Scanner scanner = ScannerFactory.getScanner();

        while (running){

            loggedUser = RegisterLogin.menuDisplay(scanner);
            if (!loggedUser.equals(null)){
            AccountManagement.accMenu(scanner,loggedUser);
            loggedUser = null;
            }
        }

    }

    }

