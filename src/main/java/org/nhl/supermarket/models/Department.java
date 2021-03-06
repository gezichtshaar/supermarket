package org.nhl.supermarket.models;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.interfaces.BuyZone;

import java.util.Queue;

/**
 * Created by remy on 08/10/14.
 */
public class Department implements BuyZone {

    private Shelf shelf;
    private Queue<Customer> customerQueue;

    public Department(int productId) {
        shelf = new Shelf(productId);
    }

    @Override
    public void addProduct(Product product) {
        if (product.getId() == shelf.getProductId()) {
            shelf.addProduct(product);
        } else {
            throw new IllegalArgumentException("Provided Product doesn't match Department's Product");
        }
    }

    @Override
    public boolean hasProduct(int productId) {
        return shelf.hasProduct(productId);
    }

    @Override
    public Product takeProduct(int productId) {
        if (productId == shelf.getProductId()) {
            return shelf.takeProduct();
        } else {
            throw new IllegalArgumentException("This department doesn't have that productId.");
        }
    }

    @Override
    public boolean hasQueue() {
        return true;
    }

    @Override
    public void registerToQueue(Customer customer) {
        if(!customerQueue.contains(customer)) {
           customerQueue.add(customer);
        }
    }

    @Override
    public boolean inQueue(Customer customer) {
        return customerQueue.contains(customer);
    }

    @Override
    public boolean queueIsEmpty() {
        return customerQueue.isEmpty();
    }

    @Override
    public int sizeOfSmallestShelf() {
        return shelf.productCount();
    }

    @Override
    public void update(Supermarket supermarket) {
        if (shelf.getProducts().isEmpty()) {
            // Fill
        } else if (!customerQueue.isEmpty()) {
            // Help customer
            Customer customer = customerQueue.poll();

            int amount = customer.wantsProductAmount(shelf.getProductId());
            customer.addProducts(shelf.takeProducts(amount));
        } else {
            // Fill
        }


    }
}
