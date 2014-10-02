package org.nhl.supermarket;

import java.math.BigDecimal;

/**
 * Created by ruben on 02/10/14.
 */
public class Aisle implements BuyZone {
    private String name;
    private Product[] products;
    private String[] productNames;

    public Product takeProduct(String productName) {
        // dummy return value
        return new Product(0, "hello", new BigDecimal("3.50"));  // tree fiddy
    }
}
