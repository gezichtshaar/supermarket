package org.nhl.supermarket.models;

import java.util.*;

/**
 * Created by ruben on 02/10/14.
 */
public class Storage {
    private Map<Integer, Stack<Product>> products;

    public Storage() {
       this.products = new HashMap<Integer, Stack<Product>>();
    }

    public Product takeProduct(int productId) {
        return products.get(productId).pop();
    }
}
