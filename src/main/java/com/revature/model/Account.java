package com.revature.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private int acctID; //unique account ID, will be primary key in ACCOUNT table, and foreign key in other tables
    private String acctType; // [C]hecking, [S]avings
    private String acctName; //Name user gives to better ID their accounts
    private double acctBalance;//current balance of account
    private int user; //User that is attached to the account, From USER table.


    public Account(String name, String type,double bal, int mainUser){
        this.acctName = name;
        this.acctType=type;
        this.acctBalance = bal;
        this.user = mainUser;



    }

    public Account(int id,String name, String type,double bal, int mainUser){
        this.acctID = id;
        this.acctName = name;
        this.acctType=type;
        this.acctBalance = bal;
        this.user = mainUser;



    }

    public int getAcctID() {
        return acctID;
    }

    public String getAcctType() {
        return acctType;
    }

    public String getAcctName() {
        return acctName;
    }

    public double getAcctBalance() {
        return acctBalance;
    }

    public int getUser() {
        return user;
    }

    public void setAcctBalance(double acctBalance) {
        this.acctBalance = acctBalance;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }
    @Override
    public String toString(){

        String str = "Account name: " + this.acctName + " Current balance: "+ NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.acctBalance);
        return str;}
}
