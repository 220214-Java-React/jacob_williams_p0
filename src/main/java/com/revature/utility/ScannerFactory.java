package com.revature.utility;

import java.util.Scanner;

public class ScannerFactory {
    private static Scanner scan;

    public static Scanner getScanner(){
        if(scan == null){
            scan = new Scanner(System.in);
        }

        return scan;
    }

    private ScannerFactory(){}
}
