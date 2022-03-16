package com.revature.model;

import java.util.List;

public class Account {
    private int acctID; //unique account ID, will be primary key in ACCOUNT table, and foreign key in other tables
    private String acctType; // [C]hecking, [S]avings, [JC] Joint Checking, [JS] Joint Savings
    private String acctName; //Name user gives to better ID their accounts
    private double acctBalance;//current balance of account
    private User mainUser; //collection of users that are attached to the account, From USER table.
    private User jointUser; //collection of transactions that are attached to the account, From TRANSACTION table.

    public Account(String type, String name,double bal, User mainUser, User jointUser){}


}
