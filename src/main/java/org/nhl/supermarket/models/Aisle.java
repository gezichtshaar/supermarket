package org.nhl.supermarket.models;

import org.nhl.supermarket.Products;
import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Task;

import java.util.ArrayList;
import java.util.List;

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
        throw new IllegalArgumentException("This aisle does not have that product");
    }

    public Product addProduct(Product product) {
        for (Shelf shelf : shelves) {
            if (shelf.hasProduct(product.getId())) {
                shelf.addProduct(product);
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
