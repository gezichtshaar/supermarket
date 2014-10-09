package org.nhl.supermarket;

import org.nhl.supermarket.Supermarket;
import org.nhl.supermarket.interfaces.BuyZone;

/**
 * Hello world!
 */
public class Main {
    private static final BuyZone[][] SUPERMARKET_MAP = new BuyZone[][] {{}};

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket(SUPERMARKET_MAP);
        supermarket.simulate();
    }
}
