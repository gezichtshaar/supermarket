package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruben on 02/10/14.
 */
public abstract class Customer implements Person {
    protected int indexPosition = 0;
    protected List<Product> shoppingCart;
    protected Map<Integer, Integer> desiredProductIds;
    protected BigDecimal balance;

    public Customer(BigDecimal balance) {
        this.balance = balance;
        this.shoppingCart = new ArrayList<Product>();
        // Needs implementation.
        this.desiredProductIds = new HashMap<Integer, Integer>();
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Integer> findMissingProducts() {
        List<Integer> missingProducts = new ArrayList<Integer>();
        for (int id : desiredProductIds.keySet()) {
            if (!shoppingCart.contains(id)) {
                missingProducts.add(id);
            }
        }
        return missingProducts;
    }

    public void subtractFromBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void act(Supermarket supermarket) {
        BuyZone currentBuyZone = supermarket.getBuyZones()[indexPosition];

        for (int productId : desiredProductIds.keySet()) {
            if (currentBuyZone.hasProduct(productId)) {
                shoppingCart.add(currentBuyZone.takeProduct(productId));
            }
        }
    }
}
