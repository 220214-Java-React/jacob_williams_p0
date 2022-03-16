package com.revature.model;

public class Transaction {

    private int transID; //unique transaction ID, will be primary key in TRANSACTION table
    private int TransNumber;//the number of the transaction unique to the accountid, eg: each account will have a transaction 1, 2 ,3 ect
    private double transAmount;//Amount of transaction.
    private String transDescription;//Description of transfer to allow user more details.
    private String transType; //transaction type, [D]eposit, [W]ithdrawal, [T]ransfer
    private Account account; //Account ID of the account effected by the transaction, From ACCOUNT table.

    public Transaction(double amount, String description, String type, int acctNum) {
    }



}
