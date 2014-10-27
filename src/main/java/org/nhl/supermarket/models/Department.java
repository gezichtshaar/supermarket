package org.nhl.supermarket.models;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Task;
import org.nhl.supermarket.models.Product;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by remy on 08/10/14.
 */
public class Department implements BuyZone, Task {

    private Shelf shelf;
    private Queue<Customer> customerQueue;

    public Department(int productId) {
        shelf = new Shelf(productId);
    }

    public void addProduct(Product product) {
        shelf.addProduct(product);
    }

    public boolean hasProduct(int productId) {
        return shelf.hasProduct(productId);
    }

    public Product takeProduct(int productId) {
        if (productId == shelf.getProductId()) {
            return shelf.takeProduct();
        } else {
            throw new IllegalArgumentException("This department doesn't have that productId.");
        }
    }

    public boolean hasQueue() {
        return true;
    }

    public void registerToQueue(Customer customer) {
        if(!customerQueue.contains(customer)) {
           customerQueue.add(customer);
        }
    }

    public boolean inQueue(Customer customer) {
        return customerQueue.contains(customer);
    }

    public void update(Supermarket supermarket) {
        Customer customer = customerQueue.poll();

        int amount = customer.wantsProductAmount(shelf.getProductId());
        customer.addProducts(shelf.takeProducts(amount));
    }
}
