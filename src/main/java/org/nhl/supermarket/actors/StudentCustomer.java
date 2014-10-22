package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by remy on 06/10/14.
 */
public class StudentCustomer extends Customer {
    public StudentCustomer() {
        super(new BigDecimal(new Random().nextInt(30)));
    }

    @Override
    public void act(Supermarket supermarket) {
        super.act(supermarket);
    }
}
