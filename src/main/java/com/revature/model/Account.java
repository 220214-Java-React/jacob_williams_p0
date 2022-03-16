package com.revature.model;

import java.util.List;

public class Account {
    private int acctID; //unique account ID, will be primary key in ACCOUNT table, and foreign key in other tables
    private String acctType; // [C]hecking, [S]avings, [JC] Joint Checking, [JS] Joint Savings
    private String acctName; //Name user gives to better ID their accounts
    private double acctBalance;//current balance of account
    private int mainUser; //collection of users that are attached to the account, From USER table.
    private int jointUser; //collection of transactions that are attached to the account, From TRANSACTION table.

    public Account(String name, String type,double bal, int mainUser, int jointUser){
        this.acctName = name;
        this.acctType=type;
        this.acctBalance = bal;
        this.mainUser = mainUser;
        this.jointUser=jointUser;


    }

    public Account(int id,String name, String type,double bal, int mainUser, int jointUser){
        this.acctID = id;
        this.acctName = name;
        this.acctType=type;
        this.acctBalance = bal;
        this.mainUser = mainUser;
        this.jointUser=jointUser;


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

    public int getMainUser() {
        return mainUser;
    }

    public int getJointUser() {
        return jointUser;
    }
}
