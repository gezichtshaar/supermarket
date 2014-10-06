package org.nhl.supermarket;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void update(Supermarket supermarket) {

    }

}
