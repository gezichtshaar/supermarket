package org.nhl.supermarket.models;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by remy on 06/10/14.
 */
public class Shelf {
    private static final int MAX_PRODUCTS = 10;

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

    public Stack<Product> getProducts() {
        return products;
    }

    public Product takeProduct() {
        return products.pop();
    }

    public boolean hasProduct(int productId) {
        return this.productId == productId && !products.isEmpty();
    }

    public void fill(Storage storage) {
        for (int i = 0; i < MAX_PRODUCTS - productCount(); i++) {
            try {
                addProduct(storage.takeProduct(productId));
            } catch (EmptyStackException e) {
                break;
            }
        }
    }

    public int productCount() {
        return products.size();
    }
}
