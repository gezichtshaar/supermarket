package org.nhl.supermarket.models;

import java.math.BigDecimal;

import org.nhl.supermarket.Products;

/**
 * Created by ruben on 02/10/14.
 */
public class Product {
    private Products id;
    private String name;
    private BigDecimal price;

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
