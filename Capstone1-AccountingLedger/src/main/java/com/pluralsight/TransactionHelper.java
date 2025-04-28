package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class TransactionHelper {
    static Scanner scanner = new Scanner(System.in);

public static String formatLocalTime(LocalDateTime _dateTime){
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    return _dateTime.format(timeFormat);
}

    public static String formatLocalDate(LocalDateTime _dateTime){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return _dateTime.format(dateFormat);
    }


    public static String covertToCSVstyle(String date, String time, String description, String vendor, double amount){
        return date + "|" + time + "|" + description + "|"
                + vendor + "|" + amount;

    }

public static void writeDepositIntoFIle(){

    try {
        FileWriter fileWriter = new FileWriter("transactions.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        System.out.println("Enter the following information of your deposit.\nDescription: ");
        String description = scanner.nextLine();
        System.out.println("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        LocalDateTime date = LocalDateTime.now();
        String formattedDate = formatLocalDate(date);
        String formattedTime = formatLocalTime(date);
        bufferedWriter.newLine();
        bufferedWriter.write(covertToCSVstyle(formattedDate,formattedTime,description,vendor,amount));

        System.out.println("Transaction Logged");
        bufferedWriter.close();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}


public static void writePaymentIntoFile(){
    try {
        FileWriter fileWriter = new FileWriter("transactions.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        System.out.println("Enter the following information of your payment.\nDescription: ");
        String description = scanner.nextLine();
        System.out.println("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        LocalDateTime date = LocalDateTime.now();
        String formattedDate = formatLocalDate(date);
        String formattedTime = formatLocalTime(date);
        bufferedWriter.newLine();
        bufferedWriter.write(covertToCSVstyle(formattedDate,formattedTime,description,vendor,-amount));

        System.out.println("Transaction Logged");
        bufferedWriter.close();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

public static List<Transaction> listOfTransactions(){
List<Transaction> transactions = new ArrayList<>();


    try {
        FileReader fileReader = new FileReader("transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        String line;
        String[] details;
        while ((line = bufferedReader.readLine()) != null){
            details = line.split("\\|");
            String date = details[0];
            String time = details[1];
            String description = details[2];
            String vendor = details[3];
            double amount = Double.parseDouble(details[4]);

            Transaction t = new Transaction(date,time,description,vendor,amount);

            transactions.add(t);
        }
        bufferedReader.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

return transactions;
}


public static void displayListOfTransaction(List<Transaction> _transactions){
    int transactionQuantityCounter = 1;
    for (Transaction transaction : _transactions){
        System.out.println("-------- " + transactionQuantityCounter + " --------");
        transaction.displayTransactionInfo();
        transactionQuantityCounter++;
    }
}

public static void depositsList(List<Transaction> _transactions){
    int transactionQuantityCounter = 1;
    for (Transaction transaction : _transactions){
        if (transaction.getAmount() > 0) {
            System.out.println("-------- " + transactionQuantityCounter + " --------");
            transaction.displayTransactionInfo();
            transactionQuantityCounter++;
            System.out.println();
        }
    }
    System.out.println();
}

    public static void paymentsList(List<Transaction> _transactions){
        int transactionQuantityCounter = 1;
        for (Transaction transaction : _transactions){
            if (transaction.getAmount() < 0) {
                System.out.println("-------- " + transactionQuantityCounter + " --------");
                transaction.displayTransactionInfo();
                transactionQuantityCounter++;
                System.out.println();
            }
        }
        System.out.println();
    }

public static void ledgerMenu(List<Transaction> _transactions){
    while (true){
        System.out.println("A) All Entries\nD)Only Deposits\nP)Only Payments\nR)View Reports\nH)Return to Home");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("a")){
            displayListOfTransaction(_transactions);
        } else if (userInput.equalsIgnoreCase("d")) {
            depositsList(_transactions);
        } else if (userInput.equalsIgnoreCase("p")) {
            paymentsList(_transactions);
        } else if (userInput.equalsIgnoreCase("r")) {
            System.out.println("Reports menu");
        } else if (userInput.equalsIgnoreCase("h")) {
            System.out.println("Returning to home menu.");
            break;
        }

    }
}


public static void reportsMenu(List<Transaction> _transactions){
   boolean reportMenu = true;
    while (reportMenu) {
       System.out.println("1) Month-to-Date\n2) Previous Month\n3) Year-to-Date\n" +
               "4) Previous Year\n5)Search by Vendor\n0) Back");
       int userChoice = scanner.nextInt();
       scanner.nextLine();
       LocalDate todaysDate = LocalDate.now();
       int year = todaysDate.getYear();
       int month = todaysDate.getMonthValue();

       switch (userChoice){
           case 1:
               for (Transaction transaction : _transactions){
                   System.out.println();
               }

           case 2:
           case 3:
           case 4:
           case 5:
           case 0:
               System.out.println("Returning");
               reportMenu = false;
           default:
               System.out.println("Invalid Option.");
       }
   }
}

}
