package org.nhl.supermarket.models;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Task;
import org.nhl.supermarket.models.Product;

import java.util.Stack;

/**
 * Created by remy on 08/10/14.
 */
public class Department implements BuyZone, Task {

    private int productId;
    private Stack<Product> products;

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

    @Override
    public Product takeProduct(int productID) {
        return products.pop();
    }

    @Override
    public void update(Supermarket supermarket) {
        //logic
    }
}
