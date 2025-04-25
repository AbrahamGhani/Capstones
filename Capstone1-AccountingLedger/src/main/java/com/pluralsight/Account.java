package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.*;
public class Account {

    double balance;
    String owner;
    String bank;
    String accountID;


    public Account(double balance, String owner, String bank, String accountID) {
        this.balance = balance;
        this.owner = owner;
        this.bank = bank;
        this.accountID = accountID;
    }

    public Account() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
