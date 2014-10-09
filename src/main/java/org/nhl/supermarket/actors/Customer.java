package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.models.Location;
import org.nhl.supermarket.models.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ruben on 02/10/14.
 */
public abstract class Customer implements Person {
    protected Location location;
    protected List<Product> shoppingCart;
    protected BigDecimal balance;

    public Customer(BuyZone[][] layout, BigDecimal balance) {
        this.location = new Location(layout);
        this.balance = balance;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void subtractFromBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public abstract void act(Supermarket supermarket);
}
