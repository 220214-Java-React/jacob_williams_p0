package com.revature.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaction {

    private int transID; //unique transaction ID, will be primary key in TRANSACTION table
    private double transAmount;//Amount of transaction.
    private String transDescription;//Description of transfer to allow user more details.
    private String transType; //transaction type, [D]eposit, [W]ithdrawal
    private int account; //Account ID of the account effected by the transaction, From ACCOUNT table.

    public Transaction(double amount, String description, String type, int acctNum) {

        this.transAmount = amount;
        this.transDescription = description;
        this.transType = type;
        this.account = acctNum;
    }
    public Transaction(int id,double amount, String description, String type, int acctNum) {
        this.transID = id;
        this.transAmount = amount;
        this.transDescription = description;
        this.transType = type;
        this.account = acctNum;
    }


    public int getTransID() {
        return transID;
    }


    public double getTransAmount() {
        return transAmount;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public String getTransType() {
        return transType;
    }

    public int getAccount() {
        return account;
    }

    @Override
    public String toString(){
        String str = " Transaction description: " + this.getTransDescription() + " Transaction type: "+ this.getTransType()+
                "Transaction amount: " + NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.getTransAmount()) ;

        return str;}
}
