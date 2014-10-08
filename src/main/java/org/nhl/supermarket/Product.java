package org.nhl.supermarket;

import java.math.BigDecimal;

/**
 * Created by ruben on 02/10/14.
 */
public class Product {
    private Products id;
    private String name;
    private BigDecimal price;
    private float discount;

    public Product(Products id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Products getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
