package org.nhl.supermarket.actors;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;
import org.nhl.supermarket.interfaces.Person;
import org.nhl.supermarket.models.Department;
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
    protected boolean isInQueue;

    public Customer(BigDecimal balance) {
        this.balance = balance;
        this.shoppingCart = new ArrayList<Product>();
        // Needs implementation.
        this.desiredProductIds = new HashMap<Integer, Integer>();
        this.isInQueue = false;
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
                if (product.getId() == id) {
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
     * @param productId unique identifier for product
     * @param buyZone   instance of BuyZone that contains Product instances with the above identifier
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
     * @param buyZone instance of BuyZone
     */
    private void takeProductsFromBuyZone(BuyZone buyZone) {
        for (int productId : desiredProductIds.keySet()) {
            if (buyZone.hasProduct(productId)) {
                putInShoppingCart(productId, buyZone);
            }
        }
    }

    /**
     * Advance through the supermarket.
     * <p>
     * Step to a BuyZone that contains a Product from `findMissingProducts()`.
     * If `findMissingProducts()` returns an empty list, or if the supermarket doesn't have all products in
     * `findMissingProducts()` readily available; go to the cash register.
     *
     * @param supermarket instance of Supermarket
     */
    private void step(Supermarket supermarket) {
        if(!isInQueue){
            List<Integer> missingProducts = findMissingProducts();
            boolean isBreak = false;

            outerLoop:
            for (int i = 0; i < supermarket.getBuyZones().length; i++) {
                for (int id : missingProducts) {
                    if (supermarket.getBuyZones()[i].hasProduct(missingProducts.get(id))) {
                        indexPosition = i;

                        isBreak = true;
                        break outerLoop;
                    }
                }
            }
            if (!isBreak) {
                // No product found from missingProducts in entire Supermarket, or `missingProducts` empty.
                // Go to cash register.
                indexPosition = -1;
                supermarket.getCashRegisterQueue().add(this);
            }
        }
    }

    public void addProduct(Product product) {

    }

    public void goToQueue (Department department) {
        department.getQueue().add(this);
        isInQueue = true;
    }

    public int getLocation() {
        return indexPosition;
    }

    public void setIsInQueue(boolean isInQueue) {
        this.isInQueue = isInQueue;
    }

    public void act(Supermarket supermarket) {
        BuyZone currentBuyZone = supermarket.getBuyZones()[indexPosition];

        takeProductsFromBuyZone(currentBuyZone);
    }
}
