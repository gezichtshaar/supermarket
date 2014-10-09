package org.nhl.supermarket;

import java.util.Stack;

/**
 * Created by remy on 08/10/14.
 */
public class Department implements BuyZone, Task {

    private Products productID;
    private Stack<Product> products;

    public Department(Products productID) {
        this.productID = productID;
        this.products = new Stack<Product>();
    }

    public boolean addProduct(Product product) {
        if (product.getId() == productID) {
            products.push(product);
            return true;
        }
        return false;
    }

    @Override
    public Product takeProduct(Products productID) {
        return products.pop();
    }

    @Override
    public void update(Supermarket supermarket) {
        //logic
    }
}
