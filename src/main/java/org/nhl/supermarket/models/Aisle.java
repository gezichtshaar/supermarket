package org.nhl.supermarket.models;

import java.math.BigDecimal;
import java.util.*;

import org.nhl.supermarket.Products;
import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Task;

/**
 * Created by ruben on 02/10/14.
 */
public class Aisle implements BuyZone, Task {
    private List<Shelf> shelves;

    public Aisle() {
        this.shelves = new ArrayList<Shelf>();
    }

    public Product takeProduct(Products productID) {
        for (Shelf shelf : shelves) {
            if (shelf.hasProduct(productID)) {
                return shelf.takeProduct();
            }
        }
        return null;//There is no invalid argument
    }

    public void update(Supermarket supermarket) {

    }

}
