package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.io.*;
import java.time.*;
import java.util.*;
public class Menus {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Main application menu loop
     * Handles:
     * - Deposits (D)
     * - Payments (P)
     * - Ledger access (L)
     * - Exit (X)
     */

    public static void startAccountingLedger() {
        System.out.println("============================");
        System.out.println("   ACCOUNTING LEDGER APP    ");
        System.out.println("         v1.2              ");
        System.out.println("============================");
        System.out.println("Loading...");
        // Simulate loading delay (optional)
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        System.out.println("Ready!\n");


        boolean homeMenu = true;
        while (homeMenu) {
            List<Transaction> transactions = TransactionHelper.listOfTransactions();

            TransactionHelper.sortListByDateDescending(transactions);

            System.out.println("======Home Menu======");
            System.out.println("D) Add Deposit\nP) Make Payment\nL) Ledger\nX) Exit");
            String userMenuInput = scanner.nextLine();

            if (userMenuInput.equalsIgnoreCase("d")) {
                TransactionHelper.writeDepositIntoFIle();

            } else if (userMenuInput.equalsIgnoreCase("p")) {
                TransactionHelper.writePaymentIntoFile();

            } else if (userMenuInput.equalsIgnoreCase("l")) {
                ledgerMenu(transactions);
            } else if (userMenuInput.equalsIgnoreCase("x")) {
                System.out.println("Bye!");
                homeMenu = false;
            } else {
                System.out.println("Invalid Input");
            }


        }//while loop end
    }

    /**
     * Ledger submenu
     * parameter _transactions the list List of transactions to work with
     * Options:
     * - All entries (A)
     * - Deposits only (D)
     * - Payments only (P)
     * - Reports (R)
     * - Home (H)
     */
    public static void ledgerMenu(List<Transaction> _transactions){
        while (true){
            System.out.println("A) All Entries\nD) Only Deposits\nP) Only Payments\nR) View Reports\nH) Return to Home");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("a")){
                TransactionHelper.displayListOfTransaction(_transactions);
            } else if (userInput.equalsIgnoreCase("d")) {
                SearchList.showTransactionsByType(_transactions,true);
            } else if (userInput.equalsIgnoreCase("p")) {
                SearchList.showTransactionsByType(_transactions,false);
            } else if (userInput.equalsIgnoreCase("r")) {
                reportsMenu(_transactions);
            } else if (userInput.equalsIgnoreCase("h")) {
                System.out.println("Returning to home menu.");
                break;
            }else {
                System.out.println("Invalid option.");
            }

        }
    }

    /**
     * Reports submenu
     * parameter _transactions List of transactions to search through
     * Options:
     * 1) Month-to-date
     * 2) Previous month
     * 3) Year-to-date
     * 4) Previous year
     * 5) Vendor search
     * 6) Custom search
     * 0) Back
     */

    public static void reportsMenu(List<Transaction> _transactions){
            boolean reportMenu = true;

            while (reportMenu) {
                try {
                    System.out.println("1) Month-to-Date\n2) Previous Month\n3) Year-to-Date\n" +
                            "4) Previous Year\n5) Search by vendor\n6) Custom Search\n0) Back");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate todaysDate = LocalDate.now();
                    switch (userChoice) {
                        case 1:
                            SearchList.monthToDate(_transactions, todaysDate);
                            break;
                        case 2:
                            SearchList.previousMonth(_transactions, todaysDate);
                            break;
                        case 3:
                            SearchList.yearToDate(_transactions, todaysDate);
                            break;
                        case 4:
                            SearchList.previousYear(_transactions, todaysDate);
                            break;
                        case 5:
                            SearchList.searchByVendor(_transactions);
                            continue;
                        case 6:
                            SearchList.customSearch(_transactions);
                            continue;
                        case 0:
                            System.out.println("Returning");
                            reportMenu = false;
                            break;
                        default:
                            System.out.println("Invalid Option.");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }
    }
}
