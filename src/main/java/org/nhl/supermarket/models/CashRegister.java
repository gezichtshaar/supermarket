package org.nhl.supermarket.models;

import java.math.BigDecimal;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.actors.Customer;
import org.nhl.supermarket.interfaces.Task;

/**
 * Created by ruben on 02/10/14.
 */
public class CashRegister implements Task {
    private BigDecimal balance;

    public CashRegister() {
        this.balance = new BigDecimal(0);
    }

    public void update(Supermarket supermarket) {
        Customer customer = supermarket.getCashRegisterQueue().poll();
        if (customer != null) {
            // Logic
        }
    }
}
