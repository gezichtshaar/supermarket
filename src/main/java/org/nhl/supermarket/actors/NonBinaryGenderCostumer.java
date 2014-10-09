package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by remy on 06/10/14.
 */
public class NonBinaryGenderCostumer extends Customer {
    public NonBinaryGenderCostumer() {
        super(new BigDecimal(new Random().nextInt(20)));
    }

    public void act(Supermarket supermarket) {

    }
}
