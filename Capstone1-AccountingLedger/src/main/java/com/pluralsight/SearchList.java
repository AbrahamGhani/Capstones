package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.*;
public class SearchList {
    static Scanner scanner = new Scanner(System.in);


    /**
     * Displays either deposits or payments based on the showDeposits parameter
     * parameter transactions List of transactions to filter
     * parameter showDeposits If true, shows deposits (amount > 0). If false, shows payments (amount < 0)
     */

    public static void showTransactionsByType(List<Transaction> transactions, boolean showDeposits) {
        int counter = 1;
        for (Transaction t : transactions) {
            boolean isDeposit = t.getAmount() > 0;
            if ((showDeposits && isDeposit) || (!showDeposits && !isDeposit)) {
                System.out.println("-------- " + counter + " --------");
                t.displayTransactionInfo();
                counter++;
                System.out.println();
            }
        }
        System.out.println();
    }




    /**
     * Shows transactions from current month
     * parameter _transactions List of transactions to filter
     * parameter todaysDate Current date for reference
     */

    public static void monthToDate(List<Transaction> _transactions, LocalDate todaysDate){
        int year = todaysDate.getYear();
        int transactionQuantityCounter = 1;
        for (Transaction transaction : _transactions){
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.getYear() == year && transactionDate.getMonthValue() == todaysDate.getMonthValue()){
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Shows transactions from previous month
     * parameter _transactions List of transactions to filter
     * parameter todaysDate Current date for reference
     */

    public static void previousMonth(List<Transaction> _transactions, LocalDate todaysDate){
        int year = todaysDate.getYear();
        int transactionQuantityCounter = 1;
        LocalDate previousMonthDate = todaysDate.minusMonths(1);
        for (Transaction transaction : _transactions){
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == previousMonthDate.getYear() && transactionDate.getMonthValue() == previousMonthDate.getMonthValue()){
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
            }

        }
        System.out.println();
    }


    /**
     * Shows transactions from current year
     * parameter _transactions List of transactions to filter
     * parameter todaysDate Current date for reference
     */
    public static void yearToDate(List<Transaction> _transactions, LocalDate todaysDate){
        int year = todaysDate.getYear();
        int transactionQuantityCounter = 1;
        for (Transaction transaction : _transactions){
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == todaysDate.getYear()){
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
            }

        }
    }

    /**
     * Shows transactions from previous year
     * parameter _transactions List of transactions to filter
     * parameter todaysDate Current date for reference
     */

    public static void previousYear(List<Transaction> _transactions, LocalDate todaysDate){
        int year = todaysDate.getYear();
        int transactionQuantityCounter = 1;
        for (Transaction transaction : _transactions){
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == todaysDate.getYear() - 1){
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
            }

        }
    }

    /**
     * Searches transactions by vendor name (case-insensitive partial match)
     * parameter _transactions List of transactions to search
     * Uses .contains() for partial matching
     * Provides feedback when vendor not found
     */

    public static void searchByVendor(List<Transaction> _transactions){
        int transactionQuantityCounter = 1;
        System.out.println("Enter Vendor Name: ");
        String userVendor = scanner.nextLine();
        userVendor = userVendor.toLowerCase();
        boolean vendorFound = false;

        for (Transaction transaction : _transactions){
            if (transaction.getVendor().toLowerCase().contains(userVendor)) {
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
                vendorFound = true;
            }
        }
        if (!vendorFound){
            System.out.println("Vendor Not Found!");
        }
        System.out.println();


    }

    /**
     * Advanced search with multiple filter options
     * parameter transactions List of transactions to search
     * Notes:
     * - Most comprehensive search method
     * - Handles partial input (blank fields)
     * - Provides good error feedback
     * - Defaults end date to today if start date provided
     * - a bit messy and most certainly not entirely done off my own experience had to use some google and AI for figure this one out.
     * - as a result from me getting outside help i made sure to include detailed notes within the method for referencing
     */

    public static void customSearch(List<Transaction> transactions) {
        /********************************************************************
         * PURPOSE:
         * Lets users search transactions using multiple filters like date range,
         * amount range, and vendor name.
         ********************************************************************/

        // ==================================================================
        // STEP 1: GET USER INPUT FOR EACH POSSIBLE FILTER
        // ==================================================================
        // We ask for each filter value separately. User can leave some blank.

        System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
        String startDateInput = scanner.nextLine(); // Read entire line of input

        System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
        String endDateInput = scanner.nextLine();

        System.out.print("Enter min price or leave blank: ");
        String minPriceInput = scanner.nextLine();

        System.out.print("Enter max price or leave blank: ");
        String maxPriceInput = scanner.nextLine();

        System.out.print("Enter vendor name or leave blank: ");
        String vendorInput = scanner.nextLine();

        // ==================================================================
        // STEP 2: PREPARE VARIABLES TO HOLD OUR FILTER VALUES
        // ==================================================================
        // We use null to mean "no filter applied" for each field

        LocalDate startDate = null;  // Will hold parsed start date or remain null
        LocalDate endDate = null;    // Same for end date
        Double minPrice = null;      // Using Double instead of double to allow null
        Double maxPrice = null;
        String vendor = null;

        // ==================================================================
        // STEP 3: PARSE AND VALIDATE EACH INPUT
        // ==================================================================
        // For each input, we:
        // 1. Check if it's not blank
        // 2. Try to convert it to the right type
        // 3. Handle errors if conversion fails

        // ----- DATE PARSING -----
        if (!startDateInput.isBlank()) {  // If user entered something for start date
            try {
                // Convert string to LocalDate (throws exception if format is wrong)
                startDate = LocalDate.parse(startDateInput);
            } catch (Exception e) {
                System.out.println("⚠️ Invalid start date format. Use yyyy-MM-dd.");
                return; // Exit the whole method if invalid
            }
        }

        if (!endDateInput.isBlank()) {
            try {
                endDate = LocalDate.parse(endDateInput);
            } catch (Exception e) {
                System.out.println("⚠️ Invalid end date format. Use yyyy-MM-dd.");
                return;
            }
        }
        // Special case: If only start date provided, assume end date is today
        else if (startDate != null) {
            endDate = LocalDate.now();
            System.out.println("Using today as end date: " + endDate);
        }

        // ----- AMOUNT PARSING -----
        if (!minPriceInput.isBlank()) {
            try {
                // Convert string to double (throws exception if not a number)
                minPrice = Double.parseDouble(minPriceInput);
            } catch (Exception e) {
                System.out.println("⚠️ Invalid min price. Please enter a number.");
                return;
            }
        }

        if (!maxPriceInput.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceInput);
            } catch (Exception e) {
                System.out.println("⚠️ Invalid max price. Please enter a number.");
                return;
            }
        }

        // ----- VENDOR NAME -----
        // No parsing needed for text, just check if not empty
        if (!vendorInput.isBlank()) {
            vendor = vendorInput; // Store as-is (we'll do case-insensitive compare later)
        }

        // ==================================================================
        // STEP 4: FILTER AND DISPLAY RESULTS
        // ==================================================================
        boolean found = false;               // Track if we found any matches
        int transactionQuantityCounter = 1;   // Number the results

        // Check every transaction in the list
        for (Transaction t : transactions) {
            // Start by assuming this transaction matches (we'll check each condition)
            boolean match = true;

            // ----- CHECK EACH FILTER CONDITION -----
            // Only apply filter if user provided a value (!= null)

            // DATE CHECK: Is transaction between start and end dates?
            if (startDate != null && t.getDate().isBefore(startDate)) {
                match = false; // Too early
            }
            if (endDate != null && t.getDate().isAfter(endDate)) {
                match = false; // Too late
            }

            // AMOUNT CHECK: Is transaction amount in range?
            if (minPrice != null && t.getAmount() < minPrice) {
                match = false; // Amount too small
            }
            if (maxPrice != null && t.getAmount() > maxPrice) {
                match = false; // Amount too large
            }

            // VENDOR CHECK: Does vendor name match? (case-insensitive)
            if (vendor != null && !t.getVendor().equalsIgnoreCase(vendor)) {
                match = false; // Vendor doesn't match
            }

            // ----- DISPLAY IF ALL CONDITIONS MET -----
            if (match) {
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                t.displayTransactionInfo();  // Show transaction details
                transactionQuantityCounter++;
                System.out.println();        // Blank line for spacing
                found = true;               // Mark that we found at least one
            }
        }

        // Final blank line for better formatting
        System.out.println();

        // If no transactions matched all filters
        if (!found) {
            System.out.println("No transactions matched your search.");
        }
    }

/* ==============FLOW VISUALIZATION================
- I asked AI for a visual representation of this method

START
  │
  ▼
Ask user for each filter (dates, amounts, vendor)
  │
  ▼
For each input:              ◄────────┐
   │                             │
   ├─ If blank → skip            │
   ├─ If not blank → try to      │
   │   convert to proper type    │
   └─ If error → show message    │
         and exit search         │
  │                              │
  ▼                              │
Set default end date if needed   │
  │                              │
  ▼                              │
Check each transaction:          │
   │                             │
   ├─ Does it match ALL active   │
   │   filters?                  │
   │                             │
   ├─ YES → Display it           │
   └─ NO → Skip it               │
  │                              │
  ▼                              │
If no matches found →            │
   tell user                     │
  │                              │
  └──────────────────────────────┘

 */



}
