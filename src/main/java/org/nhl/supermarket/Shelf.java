package org.nhl.supermarket;

import java.util.Stack;

/**
 * Created by remy on 06/10/14.
 */
public class Shelf {
    private Products productID;
    private Stack<Product> products;

    private Shelf(Products productID) {
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

    public Products getProductID() {
        return productID;
    }

    public Product takeProduct() {
        return products.pop();
    }

    public boolean hasProduct(Products productID) {
        return this.productID == productID && !products.isEmpty();
    }
}
