package org.nhl.supermarket.interfaces;

import org.nhl.supermarket.Products;
import org.nhl.supermarket.models.Product;

/**
 * Created by ruben on 02/10/14.
 */
public interface BuyZone {
    public Product takeProduct(Products productID);
}
