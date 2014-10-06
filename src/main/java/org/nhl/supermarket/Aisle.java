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
        this(new Products[]{});
    }

    public Aisle(Products[] productIDs) {
        this.shelves = new ArrayList<Shelf>();

        for (Products productID : productIDs) {
            addShelf(productID);
        }
    }

    public Product takeProduct(Products productID) {
        for (Shelf shelf : shelves) {
            if (shelf.hasProduct(productID)) {
                return shelf.takeProduct();
            }
        }
        return null;
    }

    public void addShelf(Products productID) {
        shelves.add(new Shelf(productID));
    }

    @Override
    public void update(Supermarket supermarket) {

    }

}
