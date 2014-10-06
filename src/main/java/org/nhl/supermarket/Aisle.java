package org.nhl.supermarket;

import java.math.BigDecimal;
import java.util.*;

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
        throw new IllegalArgumentException();
    }

    @Override
    public void update(Supermarket supermarket) {

    }

}
