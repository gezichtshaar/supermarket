package org.nhl.supermarket;

import java.math.BigDecimal;

/**
 * Created by ruben on 02/10/14.
 */
public class CashRegister implements Task {
    private BigDecimal balance;

    public CashRegister() {
        this.balance = new BigDecimal(0);
    }

    @Override
    public void update(Supermarket supermarket) {
        Customer customer = supermarket.getCashRegisterQueue().poll();
        if (customer != null) {
            // Logic
        }
    }
}
