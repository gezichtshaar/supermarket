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
            boolean containsProduct = false;
            for (Product product : shoppingCart) {
                if (product.getId() == id)
                {
                    containsProduct = true;
                }
            }
            if (!containsProduct) {
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

    private void goToNextBuyZone(BuyZone[] buyZones, List<Integer> missingProducts) {
        boolean isBreak = false;

        outerLoop:
        for (int i = 0; i < buyZones.length; i++) {
            for (int id : missingProducts) {
                if (buyZones[i].hasProduct(missingProducts.get(id))) {
                    indexPosition = i;
                    isBreak = true;
                    break outerLoop;
                }
            }
        }
        if (!isBreak) {
            // No product found from missingProducts in entire Supermarket. Handle this somehow.
        }
    }

    private void step(Supermarket supermarket) {
        List<Integer> missingProducts = findMissingProducts();

        if (!missingProducts.isEmpty()) {
            goToNextBuyZone(supermarket.getBuyZones(), missingProducts);
        } else {
            // Go to cash register.
        }
    }

    public void act(Supermarket supermarket) {
        BuyZone currentBuyZone = supermarket.getBuyZones()[indexPosition];

        takeProductsFromBuyZone(currentBuyZone);
    }
}
