package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class TransactionHelper {
    static Scanner scanner = new Scanner(System.in);

public static String formatLocalTime(LocalTime _time){
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    return _time.format(timeFormat);
}

    public static String formatLocalDate(LocalDate _date){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return _date.format(dateFormat);
    }


    public static String convertToCSVstyle(String date, String time, String description, String vendor, double amount){
        return date + "|" + time + "|" + description + "|"
                + vendor + "|" + amount;

    }

    public static void sortListByDateDescending(List<Transaction> _transactions){
    _transactions.sort((t1,t2) -> {
       int dateComparison =  t2.getDate().compareTo(t1.getDate()); // compare dates

       if (dateComparison != 0){
           return dateComparison; // if dates are different use date comparison
       }
       else {
           return  t2.getTime().compareTo(t1.getTime()); // if the dates are the same compare the times instead
       }
    });
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
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String formattedDate = formatLocalDate(date);
        String formattedTime = formatLocalTime(time);
        bufferedWriter.newLine();
        bufferedWriter.write(convertToCSVstyle(formattedDate,formattedTime,description,vendor,amount));

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
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String formattedDate = formatLocalDate(date);
        String formattedTime = formatLocalTime(time);
        bufferedWriter.newLine();
        bufferedWriter.write(convertToCSVstyle(formattedDate,formattedTime,description,vendor,-amount));

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
            LocalDate date = LocalDate.parse(details[0]);
            LocalTime time = LocalTime.parse(details[1]);
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
        System.out.println();
    }
    System.out.println();
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
        System.out.println("A) All Entries\nD) Only Deposits\nP) Only Payments\nR) View Reports\nH) Return to Home");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("a")){
            displayListOfTransaction(_transactions);
        } else if (userInput.equalsIgnoreCase("d")) {
            depositsList(_transactions);
        } else if (userInput.equalsIgnoreCase("p")) {
            paymentsList(_transactions);
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


public static void reportsMenu(List<Transaction> _transactions){
   boolean reportMenu = true;
    while (reportMenu) {
       System.out.println("1) Month-to-Date\n2) Previous Month\n3) Year-to-Date\n" +
               "4) Previous Year\n5) Search by vendor\n6) Search by range of dates\n0) Back");
       int userChoice = scanner.nextInt();
       scanner.nextLine();
       LocalDate todaysDate = LocalDate.now();
       switch (userChoice){
           case 1:
               monthToDate(_transactions,todaysDate);
               break;
           case 2:
               previousMonth(_transactions,todaysDate);
               break;
           case 3:
                yearToDate(_transactions,todaysDate);
                break;
           case 4:
                previousYear(_transactions,todaysDate);
                break;
           case 5:
                searchByVendor(_transactions);
                continue;
           case 6:
               dateSearch(_transactions);
               continue;
           case 0:
               System.out.println("Returning");
               reportMenu = false;
           default:
               System.out.println("Invalid Option.");
       }

   }
}


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



    public static void dateSearch(List<Transaction> _transactions){
        int transactionQuantityCounter = 1;
        System.out.println("Enter start date(yyyy-MM-dd): ");
        String userDate1 = scanner.nextLine();
        System.out.println("Enter end date(yyyy-MM-dd): ");
        String userDate2 = scanner.nextLine();
        try {
            LocalDate userStartDate = LocalDate.parse(userDate1);
            LocalDate userEndDate = LocalDate.parse(userDate2);
            boolean transactionsFound = false;

            for (Transaction transaction : _transactions){
                LocalDate transactionDate = transaction.getDate();

                if (transactionDate.isEqual(userStartDate) || transactionDate.isAfter(userStartDate) && transactionDate.isEqual(userEndDate) || transactionDate.isBefore(userEndDate)){
                    System.out.println("-------- " + transactionQuantityCounter + " --------");
                    transaction.displayTransactionInfo();
                    transactionQuantityCounter++;
                    System.out.println();
                    transactionsFound = true;
                }

            }
            if (!transactionsFound){
                System.out.println("No transactions in the specified range!");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Invalid Format please use yyyy-MM-dd.");
        }







    }


}
