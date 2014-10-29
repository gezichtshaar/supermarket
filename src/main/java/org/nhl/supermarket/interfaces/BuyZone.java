package org.nhl.supermarket.interfaces;

import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.models.Product;

import java.util.Queue;

/**
 * Created by ruben on 02/10/14.
 */
public interface BuyZone extends Task {
    public void addProduct(Product product);

    public boolean hasProduct(int productId);
    public Product takeProduct(int productID);

    public boolean hasQueue();
    public void registerToQueue(Customer customer);
    public boolean inQueue(Customer customer);
    public boolean queueIsEmpty();
}
