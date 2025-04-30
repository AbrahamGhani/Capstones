package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.*;
public class Transaction {


// transaction fields
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

// constructor with all fields
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

// default constructor
    public Transaction() {
    }


    // getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    // displays the info of a transaction in a formatted way
    public void displayTransactionInfo(){
        System.out.println("Date: " + TransactionHelper.formatLocalDate(this.getDate()));
        System.out.println("Time: " + TransactionHelper.formatLocalTime(this.getTime()));
        System.out.println("Description: " + this.getDescription());
        System.out.println("Vendor: " + this.getVendor());
        System.out.println("Amount: $" + this.getAmount());
    }

}
