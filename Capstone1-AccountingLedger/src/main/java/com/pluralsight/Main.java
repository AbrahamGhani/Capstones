package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

       /*
       0 - tools -- Scanner, filewrite(buffwrite), fileread(buffread),arraylist
       1 - start by creating the loop for the main menu
       2 - in the main menu loop create additional loops for the submenus like the ledger menu
       3 - use scanner to grab user inputs for the menu
       4 - log transactions into the csv file. for this make a method in the TransactionHelper class to grab user input
                and format it and create a transaction object to add to a list. write items from list into the file
       5 - log account balance and time stamps of transactions
       6 - for now create a rough code in main. split it after
        */



        /*
         * Program Entry Point:
         * - Starts the accounting ledger application
         * - All functionality is delegated to Menus class
         * - Consider adding splash screen or initialization checks here
         */

Menus.startAccountingLedger();



    }
}