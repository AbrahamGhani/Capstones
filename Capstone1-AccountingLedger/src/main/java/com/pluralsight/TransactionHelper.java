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

public static void writeDepositIntoFIle(String _fileName){

    try {
        FileWriter fileWriter = new FileWriter(_fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String line;
        System.out.println("Enter the following information of your deposit.\nDescription: ");
        String description = scanner.nextLine();
        System.out.println("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Amount: ");
        double amount = scanner.nextDouble();
        LocalDateTime date = LocalDateTime.now();
        String formattedDate = formatLocalDate(date);
        String formattedTime = formatLocalTime(date);
        bufferedWriter.newLine();
        bufferedWriter.write(covertToCSVstyle(formattedDate,formattedTime,description,vendor,amount));


    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}




}
