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



}
