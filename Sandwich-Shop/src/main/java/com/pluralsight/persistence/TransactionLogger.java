package com.pluralsight.persistence;

import com.pluralsight.core.Priceable;
import com.pluralsight.order.Order;

import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;


public class TransactionLogger {

    /*
     * Logs completed transactions to a file.
     *
     * Fields:
     * - logFilename: String
     *
     * Methods:
     * - logTransaction(order: Order<? extends Priceable>): void
     * - readRawLogs(): List<String>
     */

    private String logFilename;

    public TransactionLogger(String logFilename) {
        this.logFilename = logFilename;
    }

    public void logTransaction(Order<? extends Priceable> order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append(" @ ").append(order.getTimestamp()).append("\n");
        order.getItems().forEach(item ->
                sb.append("- ").append(item.getClass().getSimpleName())
                        .append(" - $").append(item.getPrice()).append("\n")
        );
        sb.append("Total: $").append(order.getTotalPrice()).append("\n\n");

        try {
            Files.writeString(Paths.get(logFilename), sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to log transaction: " + e.getMessage());
        }
    }

    public List<String> readRawLogs() {
        try {
            return Files.readAllLines(Paths.get(logFilename));
        } catch (IOException e) {
            System.err.println("Failed to read transaction log: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
