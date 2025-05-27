package com.pluralsight.ui;

import com.pluralsight.core.MenuItem;
import java.util.List;
public class MenuItemsView {

    /*
     * CLI view for displaying menu items.
     *
     * Fields:
     * - controller: UIControl
     *
     * Methods:
     * - displayItems(items: List<MenuItem>): void
     */

    private UIControl controller;

    public MenuItemsView(UIControl controller) {
        this.controller = controller;
    }

    public void displayItems(List<MenuItem> items) {
        System.out.println("\n=== Available Menu Items ===");

        items.forEach(item -> {
            System.out.printf("%s (%s): $%.2f%n",
                    item.getClass().getSimpleName(),
                    item.getName(),
                    item.getPrice()
            );
        });
    }

}
