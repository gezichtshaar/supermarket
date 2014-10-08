package org.nhl.supermarket.actors;

import java.math.BigDecimal;
import java.util.Random;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;

/**
 * Created by remy on 06/10/14.
 */
public class StudentCustomer extends Customer {
    public StudentCustomer(BuyZone[][] layout) {
		super(layout, new BigDecimal(new Random().nextInt(30)));
	}

	public void act(Supermarket supermarket){

    }
}
