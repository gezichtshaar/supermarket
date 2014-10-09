package org.nhl.supermarket.models;

import java.util.Stack;

/**
 * Created by remy on 06/10/14.
 */
public class Shelf {
    private int productId;
    private Stack<Product> products;

    public Shelf(int productId) {
        this.productId = productId;
        this.products = new Stack<Product>();
    }

    public void addProduct(Product product) {
        if (product.getId() == productId) {
            products.push(product);
        } else {
            throw new IllegalArgumentException("Shelf cannot hold provided Product");
        }
    }

    public int getProductId() {
        return productId;
    }

    public Product takeProduct() {
        return products.pop();
    }

    public boolean hasProduct(int productId) {
        return this.productId == productId && !products.isEmpty();
    }
}
