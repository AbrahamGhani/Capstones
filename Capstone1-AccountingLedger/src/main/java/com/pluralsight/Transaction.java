package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.*;
public class Transaction {

    String date;
    String time;
    String description;
    String vendor;
    double amount;


    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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


    public void CSVFormatDisplayTransactionInfo(){
        System.out.println(this.getDate() + "|" + this.getTime() + "|" + this.getDescription() + "|"
                + this.getVendor() + "|" + this.getAmount());

    }

    public void displayTransactionInfo(){
        System.out.println("Date: " + this.getDate());
        System.out.println("Time: " + this.getTime());
        System.out.println("Description: " + this.getDescription());
        System.out.println("Vendor: " + this.getVendor());
        System.out.println("Amount: $" + this.getAmount());
    }

}
