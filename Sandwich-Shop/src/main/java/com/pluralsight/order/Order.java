package com.pluralsight.order;

import com.pluralsight.core.Priceable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order<T extends Priceable> {

    /*
     * Generic class representing an order of Priceable items.
     *
     * Fields:
     * - id: String
     * - items: List<T>
     * - timestamp: String or LocalDateTime depends on how i want to store and utilize it
     *
     * Methods:
     * - addItem(item: T): void
     * - void removeItem(T item)
     * - getTotalPrice(): double
     * - List<T> getItems()
     *
     * T is typically MenuItem or subclass of it
     */



}
