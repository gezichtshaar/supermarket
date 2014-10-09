package org.nhl.supermarket.models;

import java.math.BigDecimal;

/**
 * Created by ruben on 02/10/14.
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private float discount;

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
