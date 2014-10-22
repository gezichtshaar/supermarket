package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.models.Product;

import java.math.BigDecimal;
import java.util.*;

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

    private List<Integer> findMissingProducts() {
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

    /**
     * Take Products from `buyZone` and put them in `shoppingCart`.
     * <p>
     * If `desiredProductIds` desires more than one Product, try to take as many as desired. In case `buyZone` has run
     * out of Products, simply stop taking more Products.
     *
     * @param productId
     * @param buyZone
     */
    private void putInShoppingCart(int productId, BuyZone buyZone) {
        for (int i = 0; i < desiredProductIds.get(productId); i++) {
            try {
                shoppingCart.add(buyZone.takeProduct(productId));
            } catch (EmptyStackException e) {
                break;
            }
        }
    }

    /**
     * Put all desired Products that are readily available in `buyZone` in `shoppingCart`.
     *
     * @param buyZone
     */
    private void takeProductsFromBuyZone(BuyZone buyZone) {
        for (int productId : desiredProductIds.keySet()) {
            if (buyZone.hasProduct(productId)) {
                putInShoppingCart(productId, buyZone);
            }
        }
    }

    public void act(Supermarket supermarket) {
        BuyZone currentBuyZone = supermarket.getBuyZones()[indexPosition];

        takeProductsFromBuyZone(currentBuyZone);
    }
}
