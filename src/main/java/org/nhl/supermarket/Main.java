package org.nhl.supermarket;

import org.nhl.supermarket.interfaces.BuyZone;

import Controllers.MainController;
import Graphics.MainWindow;
import Supermarket.Supermarket;

/**
 * Supermarket
 */
public class Main {
    private static final BuyZone[] SUPERMARKET_MAP = new BuyZone[] {};

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket(SUPERMARKET_MAP);
		MainController controller = new MainController(supermarket);
		MainWindow mainWindow = new MainWindow(controller);
		controller.addView(mainWindow);
		
		new Thread(mainWindow).start();
		new Thread(supermarket).start();
    }
}
