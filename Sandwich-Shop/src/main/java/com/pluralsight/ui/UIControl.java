package com.pluralsight.ui;

import com.pluralsight.authentication.*;
import com.pluralsight.core.*;
import com.pluralsight.order.*;
import java.util.*;

public class UIControl {



    /*
 * Controller class for coordinating UI events and logic.

 * Fields:
 * - manager: Manager
 * - currentOrder: Order<Priceable>
 * - loginView: LoginView
 * - mainMenu: MainMenu
 * - menuItemsView: MenuItemsView
 * - orderView: OrderView
 * - transactionView: TransactionView

*
 * Methods:
 * - startApp(): void
 * - onLogin(user: String, pass: String): void
 * - showMenu(): void
 * - showItems(): void
 * - showOrder(): void
 * - showTransactions(): void
 */

    private Manager manager;
    private Order<Priceable> currentOrder;
    private LoginView loginView;
    private MainMenu mainMenu;
    private MenuItemsView menuItemsView;
    private OrderView orderView;
    private TransactionView transactionView;

    public UIControl() {
        this.loginView = new LoginView(this);
        this.mainMenu = new MainMenu(this);
        this.menuItemsView = new MenuItemsView(this);
        this.orderView = new OrderView(this);
        this.transactionView = new TransactionView(this);
    }

    public void startApp() {
        Map<String, String> creds = loginView.promptCredentials();
        onLogin(creds.get("username"), creds.get("password"));
    }

    public void onLogin(String username, String password) {
        Manager m = LoginManager.authenticate(username, password);
        if (m != null) {
            this.manager = m;
            System.out.println("Login successful. Welcome, " + m.getUsername() + "!");
            showMenu();
        } else {
            System.out.println("Login failed. Please try again.");
            startApp();
        }
    }

    public void showMenu() {
        mainMenu.displayOptions();
    }

    public void showMenuItemsView() {
        // Placeholder example items
        List<MenuItem> items = new ArrayList<>();
        menuItemsView.displayItems(items);
    }

    public void showOrderView() {
        if (currentOrder == null) {
            currentOrder = new Order<>("001");
        }
        orderView.displayOrder(currentOrder.getItems(), currentOrder.getTotalPrice());
    }

    public void showTransactionView() {
        List<Order<?>> dummyHistory = new ArrayList<>();
        transactionView.displayTransactions(dummyHistory);
    }

}
