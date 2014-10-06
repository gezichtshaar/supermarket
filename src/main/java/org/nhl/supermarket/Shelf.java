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
}
