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

    private int productId;
    private Stack<Product> products;
    private Queue<Customer> costumers;

    public Department(int productId) {
        this.productId = productId;
        this.products = new Stack<Product>();
    }

    public void addProduct(Product product) {
        if (product.getId() == productId) {
            products.push(product);
        } else {
            throw new IllegalArgumentException("Provided Product doesn't match Department's Product.");
        }
    }

    public boolean hasProduct(int productId) {
        return this.productId == productId && !products.isEmpty();
    }

    public Product takeProduct(int productID) {
        return products.pop();
    }

    public Queue getQueue() {
        return costumers;
    }

    @Override
    public void update(Supermarket supermarket) {
        Customer customer = costumers.poll();
        if (supermarket.getBuyZones()[customer.getLocation()] == this ) {
            customer.addProduct(products.pop());
            customer.setIsInQueue(false);
        }
    }
}
