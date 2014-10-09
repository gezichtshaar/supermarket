package org.nhl.supermarket.models;

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

    public Aisle(int[] productIds) {
        this.shelves = new ArrayList<Shelf>();

        for (int productId : productIds) {
            addShelf(productId);
        }
    }

    public boolean hasProduct(int productId) {
        for (Shelf shelf : shelves) {
            if (shelf.hasProduct(productId)) {
                return true;
            }
        }
        return false;
    }

    public Product takeProduct(int productId) {
        for (Shelf shelf : shelves) {
            if (shelf.getProductId() == productId) {
                return shelf.takeProduct();
            }
        }
        throw new IllegalArgumentException("This Aisle does not have that Product");
    }

    public void addProduct(Product product) {
        Boolean isBreak = false;

        for (Shelf shelf : shelves) {
            if (shelf.getProductId() == product.getId()) {
                shelf.addProduct(product);
                isBreak = true;
                break;
            }
        }
        if (!isBreak) {
            throw new IllegalArgumentException("This Aisle cannot hold the provided Product.");
        }
    }

    public void addShelf(int productId) {
        shelves.add(new Shelf(productId));
    }

    @Override
    public void update(Supermarket supermarket) {

    }
}
