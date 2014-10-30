package org.nhl.supermarket.models;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.interfaces.BuyZone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 02/10/14.
 */
public class Aisle implements BuyZone {
    private List<Shelf> shelves;
    private int lastShelfFilled = 0;
    private int MIN_REFILL_SIZE = 3;

    public Aisle(int[] productIds) {
        this.shelves = new ArrayList<Shelf>();
        for (int productId : productIds) {
            addShelf(productId);
        }
    }

    public void addShelf(int productId) {
        shelves.add(new Shelf(productId));
    }

    @Override
    public boolean hasProduct(int productId) {
        for (Shelf shelf : shelves) {
            if (shelf.hasProduct(productId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product takeProduct(int productId) {
        for (Shelf shelf : shelves) {
            if (shelf.getProductId() == productId) {
                return shelf.takeProduct();
            }
        }
        throw new IllegalArgumentException("This Aisle does not have that Product");
    }

    @Override
    public void addProduct(Product product) {
        boolean isBreak = false;

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

    @Override
    public boolean hasQueue() {
        return false;
    }

    @Override
    public void registerToQueue(Customer customer) {
    }

    @Override
    public boolean inQueue(Customer customer) {
        return false;
    }

    @Override
    public boolean queueIsEmpty() {
        return true;
    }

    @Override
    public int sizeOfSmallestShelf() {
        int smallestSize = -1;
        for (Shelf shelf : shelves) {
            if (shelf.productCount() < smallestSize || smallestSize == -1) {
                smallestSize = shelf.productCount();
            }
        }
        return smallestSize;
    }

    @Override
    public void update(Supermarket supermarket) {
        int moduloIndex;
        for (int i = 0; i < shelves.size(); i++) {
            // Loop through all shelf indices.
            // Make a new index that is the looping index PLUS the index of the last filled shelf PLUS one.
            // In case the last filled shelf was 3, `moduloIndex` will start out as 4. If a fourth shelf doesn't exist,
            // apply modulo operation to make it 0.
            moduloIndex = (i + lastShelfFilled + 1) % shelves.size();
            if (shelves.get(moduloIndex).productCount() <= MIN_REFILL_SIZE) {
                shelves.get(moduloIndex).fill(supermarket.getStorage());
                lastShelfFilled = moduloIndex;
                return;
            }
        }
    }
}
