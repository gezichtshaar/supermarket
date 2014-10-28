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

    public Customer(BigDecimal balance, HashMap<Integer, Integer> desiredProductIds) {
        this(balance);
        this.desiredProductIds = desiredProductIds;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Map<Integer, Integer> getDesiredProductIds() {
        return desiredProductIds;
    }

    public void setDesiredProductIds(Map<Integer, Integer> desiredProductIds) {
        this.desiredProductIds = desiredProductIds;
    }

    /**
     * Get a list of product IDs that the customer desires, but doesn't hold in their shopping cart yet.
     *
     * @return list of product IDs that are desired, but not yet in the customer's shopping cart
     */
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

    /**
     * Check whether a BuyZone holds products that the customer desires.
     *
     * @param buyZone BuyZone that must be inspected for products that the customer desired
     * @return        boolean value for whether the BuyZone contains any desired products
     */
    public boolean desiresProductFromBuyZone(BuyZone buyZone) {
        List<Integer> missingProducts = findMissingProducts();
        for (int id : missingProducts) {
            if (buyZone.hasProduct(missingProducts.get(id))) {
                return true;
            }
        }
        return false;
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
     * @param productId unique identifier for product type
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

    /**
     * Get the amount of products the customer desires from a certain product.
     *
     * @param productID unique identifier for product type
     * @return          amount of products desired of a certain type
     */
    public int wantsProductAmount(int productID) {
        return desiredProductIds.get(productID);
    }

    /**
     * Add products to the customer's shopping cart.
     *
     * @param products list of products that must be added to the shopping cart
     */
    public void addProducts(List<Product> products) {
        shoppingCart.addAll(products);
    }

    public void act(Supermarket supermarket) {
        BuyZone currentBuyZone = supermarket.getBuyZones()[indexPosition];

        if (currentBuyZone.inQueue(this)) {
            // If the customer is currently in a queue; do not do anything.
            return;
        }

        if (currentBuyZone.hasQueue() && desiresProductFromBuyZone(currentBuyZone)) {
            // Department
            currentBuyZone.registerToQueue(this);
        } else if (desiresProductFromBuyZone(currentBuyZone)) {
            // Aisle
            takeProductsFromBuyZone(currentBuyZone);
        } else {
            step(supermarket);
        }
    }
}
