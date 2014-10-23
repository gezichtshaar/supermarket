package org.nhl.supermarket.models;


import java.math.BigDecimal;

/**
 * Created by remy on 06/10/14.
 */
public class DiscountAisle extends Aisle {
    public DiscountAisle(int[] productIds) {
        super(productIds);
    }

    @Override
    public void addProduct(Product product) {
        BigDecimal discount = new BigDecimal("0.75");
        product.setDiscount(discount);
        super.addProduct(product);
    }

}
