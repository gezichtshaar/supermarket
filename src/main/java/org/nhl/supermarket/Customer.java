package org.nhl.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

/**
 * Created by ruben on 02/10/14.
 */
public abstract class Customer implements Person {
    protected int x;
    protected int y;
    protected List<Product> shoppingCart;
    protected BigDecimal balance;

    public Customer() {
        this.x = 0;
        this.y = 0;
    }

    public Customer(BigDecimal balance) {
        this();
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

    public final void act(Supermarket supermarket) {
        // Logic
    }
}
