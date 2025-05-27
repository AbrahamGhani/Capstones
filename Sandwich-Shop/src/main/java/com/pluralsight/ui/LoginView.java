package com.pluralsight.ui;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class LoginView {

    /*
     * CLI screen for logging in.
     *
     * Fields:
     * - scanner: Scanner
     * - controller: UIControl
     *
     * Methods:
     * - promptCredentials(): Map<String,String>
     */

    private Scanner scanner;
    private UIControl controller;

    public LoginView(UIControl controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public Map<String, String> promptCredentials() {
        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Map<String, String> creds = new HashMap<>();
        creds.put("username", username);
        creds.put("password", password);
        return creds;
    }



}
