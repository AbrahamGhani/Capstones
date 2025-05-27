package com.pluralsight.menu;

import com.pluralsight.core.MenuItem;
import com.pluralsight.toppings.Topping;
import java.util.ArrayList;
import java.util.List;


public class Sandwich extends MenuItem {

    /*
     * Subclass of MenuItem representing a customizable sandwich.
     *
     * Fields:
     * - breadType: String
     * - size: String
     * - toppings: List<Topping>
     * - toasted: boolean
     *
     * Methods:
     * - addTopping(t: Topping): void
     * - removeTopping(t: Topping): void
     * - isToasted(): boolean
     * - setToasted(t: boolean): void
     */

    private String breadType;
    private String size;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String name, double basePrice, String breadType, String size) {
        super(name, basePrice);
        this.breadType = breadType;
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(Topping t) {
        toppings.add(t);
    }

    public void removeTopping(Topping t) {
        toppings.remove(t);
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        // Base price + sum of all topping prices
        return getBasePrice() + toppings.stream()
                .mapToDouble(Topping::getPrice)
                .sum();
    }


}
