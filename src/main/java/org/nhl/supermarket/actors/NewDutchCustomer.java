package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by remy on 06/10/14.
 */
public class NewDutchCustomer extends Customer {
    public NewDutchCustomer() {
        super(new BigDecimal(new Random().nextInt(10)));
    }

    @Override
    public void act(Supermarket supermarket) {
        super.act(supermarket);
    }
}
